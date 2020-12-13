package com.canvasjs.controllers;

import com.google.gson.Gson;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.canvasjs.dao.DataPointsDAOImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping(value = "/how-to", method = RequestMethod.GET)
public class HowToController {

	@RequestMapping(value = "/data-from-database", method = RequestMethod.GET)
	public ModelAndView dataFromDatabase(ModelMap model) {
		ModelAndView modelAndView;
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
			DataPointsDAOImpl dataPoints = (DataPointsDAOImpl) context.getBean("DataPointsDAOImpl");
			Gson gsonObj = new Gson();
			modelAndView = new ModelAndView("HowTo/DataFromDatabase");
			modelAndView.addObject("title", "Render Data From Database");

			modelAndView.addObject("dataPoints", gsonObj.toJson(dataPoints.getDataPoints()));
		} catch (Exception e) {
			modelAndView = new ModelAndView("HowTo/Error");
			modelAndView.addObject("title", "Render Data From Database");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/enable-disable-ds", method = RequestMethod.GET)
	public ModelAndView enableDisableDS(ModelMap model) {
		ModelAndView modelAndView = new ModelAndView("HowTo/EnableDisableDS");
		modelAndView.addObject("title", "Hide / Show DataSeries on Legend Click");
		return modelAndView;
	}

	@RequestMapping(value = "/live-chart", method = RequestMethod.GET)
	public ModelAndView liveChart(ModelMap model) {
		ModelAndView modelAndView = new ModelAndView("HowTo/LiveChart");
		modelAndView.addObject("title", "Dynamic / Live Chart");
		return modelAndView;
	}

	@RequestMapping(value = "/sync-multiple-charts", method = RequestMethod.GET)
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
		
		ModelAndView modelAndView = new ModelAndView("HowTo/SyncMultipleCharts");
		modelAndView.addObject("title", "Sync Multiple Charts");
		modelAndView.addObject("dataPoints1", dataPoints1);
		modelAndView.addObject("dataPoints2", dataPoints2);
		return modelAndView;
	}

	@RequestMapping(value = "/datapoints-from-user-input", method = RequestMethod.GET)
	public ModelAndView addDataPointsFromUserInput(ModelMap model) {
		ModelAndView modelAndView = new ModelAndView("HowTo/AddDataPointsFromUserInput");
		modelAndView.addObject("title", "Add Data-Points from User Input");
		return modelAndView;
	}

	@RequestMapping(value = "/multiple-charts-in-a-page", method = RequestMethod.GET)
	public ModelAndView MultipleChartsInAPage() {
		ModelAndView modelAndView = new ModelAndView("HowTo/MultipleChartsInAPage");
		modelAndView.addObject("title", "Multiple Charts in A Page");
		return modelAndView;
	}

	@RequestMapping(value = "/play-stop-live-chart", method = RequestMethod.GET)
	public ModelAndView PlayStopLiveChart() {
		ModelAndView modelAndView = new ModelAndView("HowTo/PlayStopLiveChart");
		modelAndView.addObject("title", "Play / Stop Live Chart");
		return modelAndView;
	}
	
	@RequestMapping(value = "/create-chart-from-json", method = RequestMethod.GET)
	public ModelAndView createChartFromJSON() {
		ModelAndView modelAndView = new ModelAndView("HowTo/CreateChartFromJSON");
		modelAndView.addObject("title", "Render Chart from JSON");
		return modelAndView;
	}
	
	@RequestMapping(value = "/create-chart-from-csv", method = RequestMethod.GET)
	public ModelAndView createChartFromCSV() {
		ModelAndView modelAndView = new ModelAndView("HowTo/CreateChartFromCSV");
		modelAndView.addObject("title", "Render Chart from CSV");
		return modelAndView;
	}
	
	@RequestMapping(value = "/create-chart-from-xml", method = RequestMethod.GET)
	public ModelAndView createChartFromXML() {
		ModelAndView modelAndView = new ModelAndView("HowTo/CreateChartFromXML");
		modelAndView.addObject("title", "Render Chart from XML");
		return modelAndView;
	}

}
