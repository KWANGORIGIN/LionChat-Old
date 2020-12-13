package com.canvasjs.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javax.xml.bind.annotation.XmlElement;

public class RestfullDataPointsService {
	public List<Map<Object, Object>> getRandomData(float xStart, float yStart, int length) {

		float y1 = yStart;
		float x = xStart;
		List<Map<Object, Object>> dataPoints = new ArrayList<Map<Object, Object>>();

		for (int i = 0; i < length; i++) {
			Random r = new Random();
			int Low = 0;
			int High = 10;
			int random = r.nextInt(High - Low) + Low;
			y1 += random - 5;
			x = xStart + i;

			Map<Object, Object> dataPoint = new HashMap<Object, Object>();
			dataPoint.put("x", x);
			dataPoint.put("y", y1);
			dataPoints.add(dataPoint);
		}
		return dataPoints;
	}

	public String outputJson(float xStart,float yStart,int length) {
		List<Map<Object, Object>> dataPoints = this.getRandomData(xStart, yStart, length);
		Gson gsonObj = new Gson();
		return gsonObj.toJson(dataPoints);
	}
	
	public ArrayList<String> outputXML(float xStart,float yStart,int length){
		List<Map<Object, Object>> dataPoints = this.getRandomData(xStart, yStart, length);
		ArrayList<String> rows = new ArrayList<String>();
		rows.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		rows.add("<data>");
		for (int i = 0; i < dataPoints.size(); i++) {
			
			rows.add("<point>");
			rows.add("<x>" + (dataPoints.get(i).get("x")) + "</x>");
			rows.add("<y>" + (dataPoints.get(i).get("y")) + "</y>");
			rows.add("</point>");
		}
		rows.add("</data>");
		return rows;
	}
	
	public String[] outputCSV(float xStart,float yStart,int length){
		List<Map<Object, Object>> dataPoints = this.getRandomData(xStart, yStart, length);
		String dpString[]=new String[dataPoints.size()]; 
		for(int i=0;i<dataPoints.size();i++){
			dpString[i]=dataPoints.get(i).get("x")+","+dataPoints.get(i).get("y");
		}
		return dpString;
	}
}
