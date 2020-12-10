package psu.lionchat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.github.messenger4j.exception.MessengerApiException;
import com.github.messenger4j.exception.MessengerIOException;
import com.github.messenger4j.exception.MessengerVerificationException;
import com.github.messenger4j.send.MessagePayload;
import com.github.messenger4j.send.MessagingType;
import com.github.messenger4j.send.NotificationType;
import com.github.messenger4j.send.SenderActionPayload;
import com.github.messenger4j.send.message.TextMessage;
import com.github.messenger4j.send.recipient.IdRecipient;
import com.github.messenger4j.send.senderaction.SenderAction;
import org.springframework.web.bind.annotation.*;

import com.github.messenger4j.Messenger;
import com.github.messenger4j.webhook.event.TextMessageEvent;

import psu.lionchat.classifier.MyNaiveBayesClassifier;
//import psu.lionchat.dao.LionChatDAO;
//import psu.lionchat.dao.LionChatDAOImpl;
import psu.lionchat.entity.Entity;
import psu.lionchat.intent.Intent;

import static com.github.messenger4j.Messenger.*;
import static java.util.Optional.empty;
import static java.util.Optional.of;

@RestController
@RequestMapping("/callback")
public class LionChat {

    private final Messenger messenger;
    private final psu.lionchat.classifier.ClassifierIF classifier;
//    private final LionChatDAO lionDAO;
    private psu.lionchat.intent.Intent userIntent;
    private ConversationState convState;
    private String document;
    private boolean entitiesComplete;
    private String currentUserId;

    public LionChat(Messenger messenger){
        System.out.println("YEET");

        classifier = new MyNaiveBayesClassifier();
//        lionDAO = new LionChatDAOImpl();
        convState = ConversationState.INTENTSTATE;
        document = "";

        this.messenger = messenger;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> verifyWebhook(@RequestParam(MODE_REQUEST_PARAM_NAME) final String mode,
                                                @RequestParam(VERIFY_TOKEN_REQUEST_PARAM_NAME) final String verifyToken,
                                                @RequestParam(CHALLENGE_REQUEST_PARAM_NAME) final String challenge){
        try {
            this.messenger.verifyWebhook(mode, verifyToken);
            return ResponseEntity.ok(challenge);
        } catch (MessengerVerificationException e) {
            System.out.println("Webhook FAILED to verify");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> receiveMessage(@RequestBody final String payload, @RequestHeader(SIGNATURE_HEADER_NAME) final String signature) {
        try {
            this.messenger.onReceiveEvents(payload, of(signature), event -> {
                if (event.isTextMessageEvent()) {
                    TextMessageEvent message = event.asTextMessageEvent();
                    this.currentUserId = message.senderId();

                    try {
                        sendTypingOn(currentUserId);
                    } catch (MessengerApiException e) {
                        e.printStackTrace();
                    } catch (MessengerIOException e) {
                        e.printStackTrace();
                    }

                    getResponse(message.text());//Calls our method to do processing
//                    sendResponse(this.userIntent.toString());
                }
            });
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (MessengerVerificationException e) {
            System.out.println("Callback payload failed");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    public void sendResponse(String message) {
        try {
            final IdRecipient recipient = IdRecipient.create(currentUserId);
            final NotificationType notificationType = NotificationType.REGULAR;
            final String metadata = "DEVELOPER_DEFINED_METADATA";

            final TextMessage textMessage = TextMessage.create(message, empty(), of(metadata));
            final MessagePayload messagePayload = MessagePayload.create(recipient, MessagingType.RESPONSE, textMessage, of(notificationType), empty());
            this.messenger.send(messagePayload);

        } catch (MessengerApiException | MessengerIOException e) {
            System.out.println("Error sending message to user");
        }
    }

    private void sendTypingOn(String recipientId) throws MessengerApiException, MessengerIOException {
        this.messenger.send(SenderActionPayload.create(recipientId, SenderAction.TYPING_ON));
    }

    private void sendTypingOff(String recipientId) throws MessengerApiException, MessengerIOException {
        this.messenger.send(SenderActionPayload.create(recipientId, SenderAction.TYPING_OFF));
    }

    public void getResponse(String message)
    {
        //code runs depending on current conversation state
        if(this.convState == ConversationState.INTENTSTATE)
        {
            this.userIntent = this.classifier.classifyUserIntent(message);

            this.convState = ConversationState.ENTITYSTATE;
            message = null; //set message to null for use in getEntityInfo()
        }

        if(this.convState == ConversationState.ENTITYSTATE)
        {
            message = this.getEntityInfoFromUser(message);
        }

        if(this.convState == ConversationState.SUCCESSSTATE)
        {

            if(message == null)
            {
                sendResponse("Did this answer your question, yes or no?");
                if(true)
                {
                    return;
                    //get user input from messenger
                }
            }

            if(message.equals("no"))
            {
                this.convState = ConversationState.INTENTSTATE;
                message = null;
                sendResponse("I'm sorry I wasn't able to answer your question! Please try rephrasing or asking a new question");

                return;
                //wait for response
            }
            else if(message.equals("yes"))
            {
                this.convState = ConversationState.RATINGSTATE;
                message = null;
            }
            else
            {
                sendResponse("Please enter yes or no");
                return;
            }

        }

        if(this.convState == ConversationState.RATINGSTATE)
        {
            if(message == null)
            {
                sendResponse("How would you rate your LionChat experience, 1 to 5 stars?");

                if (true) {
                    return;
                }
            }

            //wait for rating

            int rating = Integer.valueOf(message);

            if(rating < 1 || rating > 5)
            {
                sendResponse("Please enter a value between 1 and 5:");
                //wait for response
                return;
            }

            storeRating(this.userIntent, rating);
            this.convState = ConversationState.INTENTSTATE;

            try {
                sendTypingOff(currentUserId);
            } catch (MessengerApiException e) {
                e.printStackTrace();
            } catch (MessengerIOException e) {
                e.printStackTrace();
            }

            //maybe an goodbye message?
        }

    }

    public String getEntityInfoFromUser(String message)
    {
        //loop through each entity in userIntent
        for(int i = 0; i < this.userIntent.getEntities().size(); i++)
        {
            Entity e = this.userIntent.getEntities().get(i);
            if(!(e.getHasInfo())) //if info not filled
            {
                //loop through entities, set info for those whose hasInfo is false

                if(message == null)
                {
                    sendResponse(e.getPrompt());
                    return null;
                    //user will input info
                }

                //if setEntityInformation fails
                if(!(e.setEntityInformation(message))) //if info fails to set
                {
                    i--; //decrement i
                }
                message = null;
            }
        }


        this.convState = ConversationState.SUCCESSSTATE;
//        this.document = this.lionDAO.getDocumentFromIntent(this.userIntent);
        this.document = "Big Yeet";
        sendResponse(this.document);
        message = null;

        return message;
    }

    public void storeRating(Intent intent, int rating)
    {

    }

}
enum ConversationState
{
    INTENTSTATE,
    ENTITYSTATE,
    SUCCESSSTATE,
    RATINGSTATE;
}