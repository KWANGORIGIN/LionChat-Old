package psu.lionchat;

import java.time.Instant;
import java.util.Optional;

import com.github.messenger4j.Messenger;
import com.github.messenger4j.exception.MessengerVerificationException;
import com.github.messenger4j.webhook.event.TextMessageEvent;

import psu.lionchat.classifier.ClassifierIF;
import psu.lionchat.classifier.MyNaiveBayesClassifier;

public class LionChat {
	private final Messenger messenger = Messenger.create("PAGE_ACCESS_TOKEN", "APP_SECRET", "VERIFY_TOKEN");
	private static LionChat lionChat = new LionChat();
	private final ClassifierIF classifier;
	
	public LionChat() {
		classifier = new MyNaiveBayesClassifier();
		// Setup Facebook messenger.
//		try {
//			messenger.verifyWebhook("subscribe", "VERIFY_TOKEN");
//			// ???
//			final String payload = "{\"object\":\"page\",\"entry\":[{\"id\":\"1717527131834678\",\"time\":1475942721780,"
//					+ "\"messaging\":[{\"sender\":{\"id\":\"1256217357730577\"},\"recipient\":{\"id\":\"1717527131834678\"},"
//					+ "\"timestamp\":1475942721741,\"message\":{\"mid\":\"mid.1475942721728:3b9e3646712f9bed52\","
//					+ "\"seq\":123,\"text\":\"34wrr3wr\"}}]}]}";
//			final String signature = "sha1=3daa41999293ff66c3eb313e04bcf77861bb0276";
//
//			messenger.onReceiveEvents(payload, Optional.of(signature), event -> {
//				final String senderId = event.senderId();
//				final Instant timestamp = event.timestamp();
//
//				if (event.isTextMessageEvent()) {
//					final TextMessageEvent textMessageEvent = event.asTextMessageEvent();
//					final String messageId = textMessageEvent.messageId();
//					final String text = textMessageEvent.text();
//			        System.out.printf(
//			            "Received text message from '%s' at '%s' with content: %s (mid: %s)\n",
//			            senderId,
//			            timestamp,
//			            text,
//			            messageId);
//			}
//			});
//		} catch (MessengerVerificationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public static LionChat getInstance() {
		return lionChat;
	}

	public ClassifierIF getClassifier() {
		return classifier;
	}
}
