package com.canvasjs.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.canvasjs.services.RestfullDataPointsService;

@Controller
@RequestMapping(value = "/restfull-service", method = RequestMethod.GET)
public class RestController extends HttpServlet {
	@RequestMapping(value = "/datapoints", method = RequestMethod.GET)
	public void getDataPoints(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(value = "length") Integer length, @RequestParam(value = "xstart") Float xStart,
			@RequestParam(value = "ystart") Float yStart, @RequestParam(required = false, value = "noofy") Float noOfY,
			@RequestParam(value = "type") String type) throws Exception {
		RestfullDataPointsService dpService = new RestfullDataPointsService();
		if (type.equals("xml")) {
			response.setContentType("text/xml");
			response.setHeader("Content-type: text/xml;", " charset=utf-8");
			ArrayList<String> rows = dpService.outputXML(xStart, yStart, length);
			Iterator<String> iter = rows.iterator();
			while (iter.hasNext()) {
				String outputString = (String) iter.next();
				response.getOutputStream().print(outputString);
			}
			response.getOutputStream().flush();
		}
		if (type.equals("json")) {
			PrintWriter out = response.getWriter();
			out.println(dpService.outputJson(xStart, yStart, length));
		}
		if(type.equals("csv")){
			PrintWriter out = response.getWriter();
			String dataPoints[]=dpService.outputCSV(xStart, yStart, length);
			for(int i=0;i<dataPoints.length;i++)
			out.println(dataPoints[i]);
		}
	}
}
