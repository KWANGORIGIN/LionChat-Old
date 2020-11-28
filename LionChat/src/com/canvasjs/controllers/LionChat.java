package com.canvasjs.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

public class LionChat {
	public static String getIntentString(String utterance){
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		Gson gson = new Gson();
		Map<String, String> map = new HashMap<>();
		
		map.put("utterance", utterance);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(gson.toJson(map), headers);

		String response = restTemplate.postForObject("http://localhost:8000/intent", entity, String.class);
		Map<String, String> responseMap = gson.fromJson(response, Map.class);
		return responseMap.get("intent");
	}
}
