package com.canvasjs.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/features", method = RequestMethod.GET)
public class FeaturesController {
	
	@RequestMapping(value = "/zooming-panning", method = RequestMethod.GET)
	public ModelAndView ZoomingPanning() {
		ModelAndView modelAndView = new ModelAndView("Features/ZoomingPanning");
		modelAndView.addObject("title", "Zooming &amp; Panning");
		return modelAndView;
	}
	
	@RequestMapping(value = "/export-chart", method = RequestMethod.GET)
	public ModelAndView ExportChart() {
		ModelAndView modelAndView = new ModelAndView("Features/ExportChart");
		modelAndView.addObject("title", "Export Chart as Image");
		return modelAndView;
	}
	
	@RequestMapping(value = "/events-handling", method = RequestMethod.GET)
	public ModelAndView EventsHandling() {
		ModelAndView modelAndView = new ModelAndView("Features/EventsHandling");
		modelAndView.addObject("title", "Event Handling");
		return modelAndView;
	}
	
	@RequestMapping(value = "/log-axis", method = RequestMethod.GET)
	public ModelAndView LogAxis() {
		ModelAndView modelAndView = new ModelAndView("Features/LogAxis");
		modelAndView.addObject("title", "Logarithmic Axis");
		return modelAndView;
	}
	
	@RequestMapping(value = "/date-time", method = RequestMethod.GET)
	public ModelAndView DateTime() {
		ModelAndView modelAndView = new ModelAndView("Features/DateTime");
		modelAndView.addObject("title", "Date-Time Axis");
		return modelAndView;
	}
	
	@RequestMapping(value = "/reverse-axis", method = RequestMethod.GET)
	public ModelAndView ReverseAxis() {
		ModelAndView modelAndView = new ModelAndView("Features/ReverseAxis");
		modelAndView.addObject("title", "Reverse Axis");
		return modelAndView;
	}
	
	@RequestMapping(value = "/striplines", method = RequestMethod.GET)
	public ModelAndView StripLines() {
		ModelAndView modelAndView = new ModelAndView("Features/StripLines");
		modelAndView.addObject("title", "StripLines");
		return modelAndView;
	}
	
	@RequestMapping(value = "/multiple-y-axis", method = RequestMethod.GET)
	public ModelAndView MultipleYAxis() {
		ModelAndView modelAndView = new ModelAndView("Features/MultipleYAxis");
		modelAndView.addObject("title", "Multiple Y Axis");
		return modelAndView;
	}
	
	@RequestMapping(value = "/multi-series-chart", method = RequestMethod.GET)
	public ModelAndView MultiSeriesChart() {
		ModelAndView modelAndView = new ModelAndView("Features/MultiseriesChart");
		modelAndView.addObject("title", "Multiseries Chart");
		return modelAndView;
	}

}