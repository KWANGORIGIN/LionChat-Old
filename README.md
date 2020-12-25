# LionChat: A Chatbot for all things Penn State

## Team Members

- **William Hemminger (wzh44@psu.edu)
  - Software Engineering, Computer Engineering Minor
  - Junior @ Penn State Behrend
- **Jacob Karabin (jxk5861@psu.edu)
  - Software Engineering, Mathematics
  - Junior @ Penn State Behrend
- **Kevin Wang (kvw5720@psu.edu)
  - Software Engineering, Mathematics
  - Junior @ Penn State Behrend
  
## Introduction

### Product Context:

Students at Penn State Behrend often have questions on topics such as connecting to the campus Wi-Fi network and which events they should attend to add to their college experience. This software will provide to students a resource with which they can quickly receive the answers to these queries, reducing the workload for IT help desk employees and Welcome Week staff.

This chatbot system, LionChat, will be accessed by users over the Facebook Messenger application, and will provide users with information relating to IT help, things to do in Erie, and Behrend events. Administrators for the system can access the chatbot’s analytics information over a webpage.

### Statement of System Scope:

The software will determine the user’s intent and retrieve the relevant information from an SQL database. The answer response time per asker query should be a maximum of 10 seconds.  Relevant analytics information such as user ratings, frequently asked questions, and incorrectly classified questions will be retrieved from the database and displayed on a webpage for the administrator users.

![Use Case Diagram](https://github.com/KWANGORIGIN/LionChat/blob/main/imgs/UML%20Case%20Diagram.png)

## Architectural Design

### System Architecture:

This software system uses the MVC architectural style.  The model for the LionChat system is the Database Access Object, or LionChatDAO.  The DAO handles all the requests that pertain to accessing data stored in the MySQL Database.  The controller for the system includes the LionChat module itself, along with a ClassifierIF subsystem and PreProcessor subsystem.  The LionChat module is responsible for coordinating operations within the system among the view and model components, receiving the user’s request from Facebook Messenger as a string and sending it to the ClassifierIF subsystem to be processed and classified.  The ClassifierIF processes the user’s input through the PreProcessor module to remove stop-words and stem any verbs, and then the input is passed back to the ClassifierIF and sent to the Intent Classifier module, which determines the intent of the user’s question.  This intent is then sent to the LionChat module which then prompts the user for the necessary details to answer their question.  The LionChat module then sends the relevant information to the DAO to retrieve the corresponding answer, which is returned to LionChat, and then to the user via Facebook Messenger.  After each question/answer cycle, the LionChat Module sends data to the DAO to update the information pertaining to average user rating, pie chart of ratings, most common intents, and frequency of intents being classified incorrectly.  Lastly, the LionChat module takes in this information through the DAO and updates the corresponding graphics in the Administrative Website subsystem.

![Package Diagram](https://github.com/KWANGORIGIN/LionChat/blob/main/imgs/Package%20Diagram.png)

### Design Rationale:

This software uses the MVC, or Model View Controller software architecture.  The MVC software pattern for our system was chosen for several reasons. First, MVC is designed around “separation of concerns” where the system is structured into 3 logical components: the model, view, and controller components. Structuring the system in this way has the benefit of encapsulating specific portions of the system so that development on a particular component can be done without necessarily understanding how another component operates. Another benefit of MVC is that the system avoids high coupling between the data processing logic in the model and the logic that displays that data in the view component.  Having low coupling in the system allows for easier maintenance and replacement of components. This is ideal for LionChat since the team may decide to replace the classifier in the model component in the future if a better classifier can be implemented.  Additionally, for the view component, LionChat provides two different views depending on who is using the system. Asker users who interact with LionChat by sending it questions will receive answers via Facebook Messenger. On the other hand, administrative users will interact with the analytics webpage from a browser.  Furthermore, due to the low coupling between software components, MVC can be used when future requirements for interaction and presentation of data are unknown.  Finally, by using MVC, the changes to the data from one view will change the data being displayed in all other views without modifying the views themselves. This way, when a user submits feedback on the answer, the analytics data on the analytics administrative webpage will be updated. Considering all the benefits MVC can bring to our system, it seems clear that it is the best architecture for our system. 

## Key Functionality Design

### Ask Question Processing Sequence Diagram

The user will ask the system a question through the messenger interface. The classifier determines the intent for the question with the classifier running on the python server through the REST interface. Then, the system asks the user questions for each entity associated with the intent. The user answers the questions, and the system responds with a document from the database representing the answer.

![Ask Question Sequence Diagram](https://github.com/KWANGORIGIN/LionChat/blob/main/imgs/Asking%20Question%20Sequence%20Diagram.png)

### Ask Question Activity Diagram

![Ask Question Activity Diagram](https://github.com/KWANGORIGIN/LionChat/blob/main/imgs/Ask%20Question%20Activity%20Diagram.png)

### Structural/Class Diagram

![Class Diagram](https://github.com/KWANGORIGIN/LionChat/blob/main/imgs/Class%20Diagram.png)

## User Interface Design

### Administrative Website Home Page

The home page allows a user to navigate to either the view analytics or settings pages. By default, it will display all the charts which can be shown on the view analytics page. One chart will show the average rating out of 5 stars. Another chart will display the most frequently asked questions by displaying the number of questions asked for each intent. The third chart will display the intents which are commonly misclassified. The fourth chart will be a pie chart which shows the average overall rating. 

![Home Page](https://github.com/KWANGORIGIN/LionChat/blob/main/imgs/Main%20menu.png)

### Expanded Data Page

Clicking on a chart will show the expanded data page for the chart, where the chart is enlarged and easier to read. The sidebar can be used to navigate to any page on the website. The view analytics page contains graphs which display analytics information. The charts displayed will be enlarged versions of the four charts seen on the home page. The page will allow the user to switch which chart is displayed and can easily navigate home with the sidebar.

![Expanded Data Page](https://github.com/KWANGORIGIN/LionChat/blob/main/imgs/Expanded%20Data%20Page.png)
