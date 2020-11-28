package com.canvasjs.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.canvasjs.dao.DataPointsDAOImpl;
import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/view-analytics", method = RequestMethod.GET)
public class AnalyticsController {

	@RequestMapping(value = "/data-from-database", method = RequestMethod.GET)
	public ModelAndView dataFromDatabase(ModelMap model) {
		ModelAndView modelAndView;
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
			DataPointsDAOImpl dataPoints = (DataPointsDAOImpl) context.getBean("DataPointsDAOImpl");
			Gson gsonObj = new Gson();
			modelAndView = new ModelAndView("Analytics/DataFromDatabase");
			modelAndView.addObject("title", "Render Data From Database");

			modelAndView.addObject("dataPoints", gsonObj.toJson(dataPoints.getDataPoints()));
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView = new ModelAndView("Analytics/Error");
			modelAndView.addObject("title", "Render Data From Database");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/analytics", method = RequestMethod.GET)
	public ModelAndView syncMultipleCharts(ModelMap model) {
		Gson gsonObj = new Gson();
		String dataPoints1 = "";
		String dataPoints2 = "";

		Map<Object, Object> map = new HashMap<Object, Object>();
		java.util.List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
		int yVal = 20;

		for (int i = 0; i <= 1000; i++) {
			yVal = (int) (yVal + Math.round(5 + Math.random() * (-5 - 5)));
			map = new HashMap<Object, Object>();
			map.put("x", i++);
			map.put("y", yVal);
			list.add(map);
		}
		dataPoints1 = gsonObj.toJson(list);

		list = new ArrayList<Map<Object, Object>>();
		for (int i = 0; i <= 1000; i++) {
			yVal = (int) (yVal + Math.round(5 + Math.random() * (-5 - 5)));
			map = new HashMap<Object, Object>();
			map.put("x", i++);
			map.put("y", yVal);
			list.add(map);
		}
		dataPoints2 = gsonObj.toJson(list);

		ModelAndView modelAndView = new ModelAndView("Analytics/SyncMultipleCharts");
		modelAndView.addObject("title", "Sync Multiple Charts");
		modelAndView.addObject("dataPoints1", dataPoints1);
		modelAndView.addObject("dataPoints2", dataPoints2);
		return modelAndView;
	}

	@RequestMapping(value = "/get-intent", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void getIntent(@RequestBody String utterance) {
		System.out.println(LionChat.getIntentString(utterance));	
	}
}
