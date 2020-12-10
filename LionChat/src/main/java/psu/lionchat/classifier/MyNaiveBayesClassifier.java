package psu.lionchat.classifier;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import psu.lionchat.intent.Intent;
import psu.lionchat.intent.intents.CampusEventsIntent;
import psu.lionchat.intent.intents.ErieInfoIntent;
import psu.lionchat.intent.intents.WifiAssistanceIntent;

public class MyNaiveBayesClassifier implements ClassifierIF {

	private PreProcessor preProcessor; //will preprocess the user's input, stemming & punctuation removal

	public MyNaiveBayesClassifier()
	{
		preProcessor = new PreProcessor();
	}

	@Override
	public String getIntentString(String utterance) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		Gson gson = new Gson();
		Map<String, String> map = new HashMap<>();

		map.put("utterance", utterance);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(gson.toJson(map), headers);

		//TODO: make this into a bean.xml / web.xml data source if thats a thing.
		String response = restTemplate.postForObject("http://localhost:8000/intent", entity, String.class);
		Map<String, String> responseMap = gson.fromJson(response, Map.class);
		return responseMap.get("intent");
	}

	@Override
	public Intent classifyUserIntent(String utterance) {
//		utterance = this.preProcessor.process(utterance);
		String intentString = this.getIntentString(utterance);

		// TODO: These are not the correct labels.
		if(intentString.equals("WifiAssistanceIntent")) {
			return new WifiAssistanceIntent();
		}else if(intentString.equals("CampusEventsIntent")) {
			return new CampusEventsIntent();
		}else if(intentString.equals("ErieInfoIntent")) {
			return new ErieInfoIntent();
		}
		
		return null;
	}

}
