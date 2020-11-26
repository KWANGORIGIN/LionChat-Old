package com.canvasjs.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/chart-types", method = RequestMethod.GET)
public class ChartTypesController {
	
	@RequestMapping(value = "/area", method = RequestMethod.GET)
	public ModelAndView Area() {
		ModelAndView modelAndView = new ModelAndView("ChartTypes/Area");
		modelAndView.addObject("title", "Area Chart");
		return modelAndView;
	}
	
	@RequestMapping(value = "/column", method = RequestMethod.GET)
	public ModelAndView Column() {
		ModelAndView modelAndView = new ModelAndView("ChartTypes/Column");
		modelAndView.addObject("title", "Column Chart");
		return modelAndView;
	}
	
	@RequestMapping(value = "/bar", method = RequestMethod.GET)
	public ModelAndView Bar() {
		ModelAndView modelAndView = new ModelAndView("ChartTypes/Bar");
		modelAndView.addObject("title", "Bar Chart");
		return modelAndView;
	}
	
	@RequestMapping(value = "/bubble", method = RequestMethod.GET)
	public ModelAndView Bubble() {
		ModelAndView modelAndView = new ModelAndView("ChartTypes/Bubble");
		modelAndView.addObject("title", "Bubble Chart");
		return modelAndView;
	}
	
	@RequestMapping(value = "/doughnut", method = RequestMethod.GET)
	public ModelAndView Doughnut() {
		ModelAndView modelAndView = new ModelAndView("ChartTypes/Doughnut");
		modelAndView.addObject("title", "Doughnut Chart");
		return modelAndView;
	}
	
	@RequestMapping(value = "/line", method = RequestMethod.GET)
	public ModelAndView Line() {
		ModelAndView modelAndView = new ModelAndView("ChartTypes/Line");
		modelAndView.addObject("title", "Line Chart");
		return modelAndView;
	}
	
	@RequestMapping(value = "/pie", method = RequestMethod.GET)
	public ModelAndView Pie() {
		ModelAndView modelAndView = new ModelAndView("ChartTypes/Pie");
		modelAndView.addObject("title", "Pie Chart");
		return modelAndView;
	}
	
	@RequestMapping(value = "/scatter", method = RequestMethod.GET)
	public ModelAndView Scatter() {
		ModelAndView modelAndView = new ModelAndView("ChartTypes/Scatter");
		modelAndView.addObject("title", "Scatter Chart");
		return modelAndView;
	}
	
	@RequestMapping(value = "/spline", method = RequestMethod.GET)
	public ModelAndView Spline() {
		ModelAndView modelAndView = new ModelAndView("ChartTypes/Spline");
		modelAndView.addObject("title", "Spline Chart");
		return modelAndView;
	}
	
	@RequestMapping(value = "/spline-area", method = RequestMethod.GET)
	public ModelAndView SplineArea() {
		ModelAndView modelAndView = new ModelAndView("ChartTypes/SplineArea");
		modelAndView.addObject("title", "Spline Area Chart");
		return modelAndView;
	}
	
	@RequestMapping(value = "/stacked-area", method = RequestMethod.GET)
	public ModelAndView StackedArea() {
		ModelAndView modelAndView = new ModelAndView("ChartTypes/StackedArea");
		modelAndView.addObject("title", "Stacked Area Chart");
		return modelAndView;
	}
	
	@RequestMapping(value = "/stacked-area100", method = RequestMethod.GET)
	public ModelAndView StackedArea100() {
		ModelAndView modelAndView = new ModelAndView("ChartTypes/StackedArea100");
		modelAndView.addObject("title", "Stacked Area 100% Chart");
		return modelAndView;
	}
	
	@RequestMapping(value = "/stacked-bar", method = RequestMethod.GET)
	public ModelAndView StackedBar() {
		ModelAndView modelAndView = new ModelAndView("ChartTypes/StackedBar");
		modelAndView.addObject("title", "Stacked Bar Chart");
		return modelAndView;
	}
	
	@RequestMapping(value = "/stacked-bar100", method = RequestMethod.GET)
	public ModelAndView StackedBar100() {
		ModelAndView modelAndView = new ModelAndView("ChartTypes/StackedBar100");
		modelAndView.addObject("title", "Stacked Bar 100% Chart");
		return modelAndView;
	}
	
	@RequestMapping(value = "/stacked-column", method = RequestMethod.GET)
	public ModelAndView StackedColumn() {
		ModelAndView modelAndView = new ModelAndView("ChartTypes/StackedColumn");
		modelAndView.addObject("title", "Stacked Column  Chart");
		return modelAndView;
	}
	
	@RequestMapping(value = "/stacked-column100", method = RequestMethod.GET)
	public ModelAndView StackedColumn100() {
		ModelAndView modelAndView = new ModelAndView("ChartTypes/StackedColumn100");
		modelAndView.addObject("title", "Stacked Column 100% Chart");
		return modelAndView;
	}
	
	@RequestMapping(value = "/step-area", method = RequestMethod.GET)
	public ModelAndView StepArea() {
		ModelAndView modelAndView = new ModelAndView("ChartTypes/StepArea");
		modelAndView.addObject("title", "Step Area Chart");
		return modelAndView;
	}
	
	@RequestMapping(value = "/step-line", method = RequestMethod.GET)
	public ModelAndView StepLine() {
		ModelAndView modelAndView = new ModelAndView("ChartTypes/StepLine");
		modelAndView.addObject("title", "Step Line Chart");
		return modelAndView;
	}

}