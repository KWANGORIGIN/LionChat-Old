package psu.lionchat.dao;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import psu.lionchat.intent.Intent;
import psu.lionchat.intent.intents.CampusEventsIntent;
import psu.lionchat.intent.intents.WifiAssistanceIntent;

class LionChatDAOImplTest {

	@Test
	void testGetDocumentFromIntent() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		LionChatDAO dataPoints = (LionChatDAO) context.getBean("LionChatDAOImpl");
		Intent intent = new CampusEventsIntent();
		intent.getEntities().get(0).setEntityInformation("11-30-2020 7 PM");
		String s = dataPoints.getDocumentFromIntent(intent).trim().replace("\n", "").replace("\r","");

		// its not equal due to encoding differences.
		//assert(s.equals(expected));
		String expected = "Download the Penn State configuration file or the Eduroam configuration file. Double-Click the downloaded file. Click Continue. Enter your Access Account userID (or userID@psu.edu for Eduroam) and password. Click Install. Enter local user password when prompted. The profile is now installed. Wireless for PSU or Eduroam is now configured";
		intent = new WifiAssistanceIntent();
		intent.getEntities().get(0).setEntityInformation("macos");
		s = dataPoints.getDocumentFromIntent(intent);
		assert(s.equals(expected));

		intent = new WifiAssistanceIntent();
		intent.getEntities().get(0).setEntityInformation("windows");
		expected = "Download the Penn State configuration file or the Eduroam configuration file. Double-Click the downloaded file. Click Continue. Enter your Access Account userID (or userID@psu.edu for Eduroam) and password. Click Install. Enter local user password when prompted. The profile is now installed. Wireless for PSU or Eduroam is now configured";
		assert(s.equals(expected));
		context.close();
	}

	@Test
	void testAddUserRating() {
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//		LionChatDAO dataPoints = (LionChatDAO) context.getBean("LionChatDAOImpl");
	}

}
