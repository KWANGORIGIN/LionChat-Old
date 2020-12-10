package psu.lionchat.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import psu.lionchat.dao.LionChatDAO;
import psu.lionchat.intent.intents.CampusEventsIntent;
import psu.lionchat.model.IntentRatingsModel;

@Controller
public class AnalyticsController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(ModelMap model) {
		ModelAndView modelAndView = new ModelAndView("Analytics/MultipleChartsInAPage");
		LionChatDAO dataPoints;
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
			dataPoints = (LionChatDAO) context.getBean("LionChatDAOImpl");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("Analytics/Error");
		}
		
		List<Object> averageIntentRatings = this.getAverageRatingsMap(dataPoints);
		List<Object> overallRatings = this.getOverallRatingsMap(dataPoints);
		List<Object> intentFrequencies = this.getFrequenciesMap(dataPoints);
		List<Object> commonlyMisclassifiedIntents = getCommonlyMisclassifiedIntentsMap(dataPoints);
//		List<Object> chart = getCommonlyMisclassifiedIntentsMap(dataPoints);
		
		Gson gsonObj = new Gson();
		modelAndView.addObject("title", "Home");
		modelAndView.addObject("dataPoints1", gsonObj.toJson(averageIntentRatings));
		modelAndView.addObject("dataPoints2", gsonObj.toJson(overallRatings));
		modelAndView.addObject("dataPoints3", gsonObj.toJson(intentFrequencies));
		modelAndView.addObject("dataPoints4", gsonObj.toJson(commonlyMisclassifiedIntents));
//		System.out.println(modelAndView.getModelMap());
		//modelAndView = new ModelAndView("Analytics/MultipleChartsInAPage");
//		modelAndView.addObject("dataPoints", gsonObj.toJson(chart));
		
		return modelAndView;
	}
//	@RequestMapping(value = "/data-from-database", method = RequestMethod.GET)
//	public ModelAndView dataFromDatabase(ModelMap model) {
//		ModelAndView modelAndView;
//		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
//			DataPointsDAOImpl dataPoints = (DataPointsDAOImpl) context.getBean("DataPointsDAOImpl");
//			Gson gsonObj = new Gson();
//			modelAndView = new ModelAndView("Analytics/DataFromDatabase");
//			modelAndView.addObject("title", "Render Data From Database");
//
//			modelAndView.addObject("dataPoints", gsonObj.toJson(dataPoints.getDataPoints()));
//		} catch (Exception e) {
//			e.printStackTrace();
//			modelAndView = new ModelAndView("Analytics/Error");
//			modelAndView.addObject("title", "Render Data From Database");
//		}
//		return modelAndView;
//	}

//	@RequestMapping(value = "/analytics", method = RequestMethod.GET)
//	public ModelAndView syncMultipleCharts(ModelMap model) {
//		Gson gsonObj = new Gson();
//		String dataPoints1 = "";
//		String dataPoints2 = "";
//
//		Map<Object, Object> map = new HashMap<Object, Object>();
//		java.util.List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
//		int yVal = 20;
//
//		for (int i = 0; i <= 1000; i++) {
//			yVal = (int) (yVal + Math.round(5 + Math.random() * (-5 - 5)));
//			map = new HashMap<Object, Object>();
//			map.put("x", i++);
//			map.put("y", yVal);
//			list.add(map);
//		}
//		dataPoints1 = gsonObj.toJson(list);
//
//		list = new ArrayList<Map<Object, Object>>();
//		for (int i = 0; i <= 1000; i++) {
//			yVal = (int) (yVal + Math.round(5 + Math.random() * (-5 - 5)));
//			map = new HashMap<Object, Object>();
//			map.put("x", i++);
//			map.put("y", yVal);
//			list.add(map);
//		}
//		dataPoints2 = gsonObj.toJson(list);
//
//		ModelAndView modelAndView = new ModelAndView("Analytics/SyncMultipleCharts");
//		modelAndView.addObject("title", "Sync Multiple Charts");
//		modelAndView.addObject("dataPoints1", dataPoints1);
//		modelAndView.addObject("dataPoints2", dataPoints2);
//		return modelAndView;
//	}

	// TODO: Remove this once testing is done.
	@RequestMapping(value = "/view-analytics/get-intent", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void getIntent(@RequestBody String utterance) {
		System.out.println(utterance);
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		LionChatDAO dataPoints = (LionChatDAO) context.getBean("LionChatDAOImpl");
		dataPoints.addUserRating(new CampusEventsIntent(), Integer.parseInt(utterance));
//		System.out.println(LionChat.getInstance().getClassifier().getIntentString(utterance));
	}

//	@RequestMapping(value = "/test-database", method = RequestMethod.POST)
//	@ResponseStatus(value = HttpStatus.OK)
//	public void getData() {
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//		LionChatDAOImpl dataPoints = (LionChatDAOImpl) context.getBean("LionChatDAOImpl");
//
//		// Average ratings.
//		HashMap<String, AtomicInteger> map = new HashMap<>();
//		for (DataPointsModel point : dataPoints.getRatings()) {
//			if (map.containsKey(point.getIntent())) {
//				map.get(point.getIntent()).addAndGet(point.getRating());
//			} else {
//				map.put(point.getIntent(), new AtomicInteger(point.getRating()));
//			}
//		}
//
//		Gson gsonObj = new Gson();
//		ModelAndView modelAndView = new ModelAndView("Analytics/AverageRatings");
//		modelAndView.addObject("title", "Average Ratings");
//		modelAndView.addObject("dataPoints", gsonObj.toJson(map));
//		context.close();
////		return modelAndView;
//	}

	@RequestMapping(value = "/view-analytics/overall-ratings", method = RequestMethod.GET)
	public ModelAndView getOverallRatings() {
		ModelAndView modelAndView = new ModelAndView("Analytics/OverallRatings");
		LionChatDAO dataPoints;
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
			dataPoints = (LionChatDAO) context.getBean("LionChatDAOImpl");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("Analytics/Error");
		}

		AtomicInteger total = new AtomicInteger();
		List<Object> chart = getOverallRatingsMap(dataPoints, total);

		Gson gsonObj = new Gson();

		modelAndView.addObject("title", "Average Overall Ratings");
		modelAndView.addObject("total", total);
		modelAndView.addObject("dataPoints", gsonObj.toJson(chart));

		return modelAndView;
	}

	@RequestMapping(value = "/view-analytics/intent-average-ratings", method = RequestMethod.GET)
	public ModelAndView getAverageRatings() {
		System.out.println("SUCCESS");
		ModelAndView modelAndView = new ModelAndView("Analytics/IntentAverageRatings");
		LionChatDAO dataPoints;
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
			dataPoints = (LionChatDAO) context.getBean("LionChatDAOImpl");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("Analytics/Error");
		}

		// A mapping from intent to its ratings information.
		List<Object> chart = getAverageRatingsMap(dataPoints);

		Gson gsonObj = new Gson();
		modelAndView.addObject("title", "Intent Average Ratings");
		modelAndView.addObject("dataPoints", gsonObj.toJson(chart));
//		System.out.println(gsonObj.toJson(chart));
//		modelAndView.addObject("data", gsonObj.toJson(list));
		return modelAndView;
	}

	@RequestMapping(value = "/view-analytics/frequently-asked-questions")
	public ModelAndView getFrequentlyAskedQuestions() {
		ModelAndView modelAndView = new ModelAndView("Analytics/FrequentlyAskedQuestions");
		LionChatDAO dataPoints;
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
			dataPoints = (LionChatDAO) context.getBean("LionChatDAOImpl");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("Analytics/Error");
		}

		List<Object> chart = getFrequenciesMap(dataPoints);

		Gson gsonObj = new Gson();
		modelAndView.addObject("title", "Frequently Asked Questions");
		modelAndView.addObject("dataPoints", gsonObj.toJson(chart));

		return modelAndView;
	}

	@RequestMapping(value = "/view-analytics/commonly-misclassified-intents")
	public ModelAndView getCommonlyMisclassifiedIntent() {
		ModelAndView modelAndView = new ModelAndView("Analytics/CommonlyMisclassifiedIntents");
		LionChatDAO dataPoints;
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
			dataPoints = (LionChatDAO) context.getBean("LionChatDAOImpl");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("Analytics/Error");
		}

		List<Object> chart = getCommonlyMisclassifiedIntentsMap(dataPoints);

		Gson gsonObj = new Gson();
		modelAndView.addObject("title", "Commonly Misclassified Intents");
		modelAndView.addObject("dataPoints", gsonObj.toJson(chart));

		return modelAndView;
	}

	private List<Object> getOverallRatingsMap(LionChatDAO dataPoints) {
		return this.getOverallRatingsMap(dataPoints, null);
	}

	private List<Object> getOverallRatingsMap(LionChatDAO dataPoints, AtomicInteger passedTotal) {
		int total = 0;
		Map<Integer, AtomicInteger> map = new HashMap<>();
		for (IntentRatingsModel point : dataPoints.getRatings()) {
			if (map.containsKey(point.getRating())) {
				map.get(point.getRating()).addAndGet(point.getRating());
			} else {
				map.put(point.getRating(), new AtomicInteger(point.getRating()));
			}
			total += point.getRating();
		}
		if (passedTotal != null) {
			passedTotal.set(total);
		}

		List<Object> chart = new ArrayList<>();
		for (Entry<Integer, AtomicInteger> entry : map.entrySet()) {
			String intent = entry.getKey().toString();
			double percent = 100 * entry.getValue().get() / total;
			Map<Object, Object> pieSlice = new HashMap<Object, Object>();
			pieSlice.put("y", percent);
			pieSlice.put("rating", intent);
			pieSlice.put("label", intent);
			chart.add(pieSlice);
		}
		return chart;
	}
	
	private List<Object> getAverageRatingsMap(LionChatDAO dataPoints) {
		Map<String, AtomicInteger> totalStars = new HashMap<String, AtomicInteger>();
		Map<String, AtomicInteger> totalRatings = new HashMap<String, AtomicInteger>();
		for (IntentRatingsModel point : dataPoints.getRatings()) {
			String intent = point.getIntent();
			int rating = point.getRating();

			if (totalStars.containsKey(intent)) {
				totalStars.get(intent).addAndGet(rating);
				totalRatings.get(intent).incrementAndGet();
			} else {
				totalStars.put(intent, new AtomicInteger(rating));
				totalRatings.put(intent, new AtomicInteger(1));
			}
		}

		List<Object> chart = new ArrayList<>();
		for (Entry<String, AtomicInteger> stars : totalStars.entrySet()) {
			String intent = stars.getKey();
			Map<Object, Object> column = new HashMap<>();
			column.put("label", intent);
			double averageRating = stars.getValue().get() * 1.0 / totalRatings.get(intent).get();
			column.put("y", averageRating);
			chart.add(column);
		}
		return chart;
	}

	private List<Object> getFrequenciesMap(LionChatDAO dataPoints) {
		Map<String, AtomicInteger> frequencies = new HashMap<>();
		for (IntentRatingsModel point : dataPoints.getRatings()) {
			String intent = point.getIntent();
			if (frequencies.containsKey(intent)) {
				frequencies.get(intent).incrementAndGet();
			} else {
				frequencies.put(intent, new AtomicInteger(1));
			}
		}

		List<Object> chart = new ArrayList<>();
		for (Entry<String, AtomicInteger> frequency : frequencies.entrySet()) {
			Map<Object, Object> column = new HashMap<>();
			column.put("y", frequency.getValue().get());
			column.put("label", frequency.getKey());
			chart.add(column);
		}
		return chart;
	}
	
	private List<Object> getCommonlyMisclassifiedIntentsMap(LionChatDAO dataPoints) {
		Map<String, AtomicInteger> frequencies = new HashMap<>();
		for (IntentRatingsModel point : dataPoints.getRatings()) {
			if (point.getRating() != 1) {
				continue;
			}
			String intent = point.getIntent();

			if (frequencies.containsKey(intent)) {
				frequencies.get(intent).incrementAndGet();
			} else {
				frequencies.put(intent, new AtomicInteger(1));
			}
		}

		List<Object> chart = new ArrayList<>();
		for (Entry<String, AtomicInteger> frequency : frequencies.entrySet()) {
			Map<Object, Object> column = new HashMap<>();
			column.put("y", frequency.getValue().get());
			column.put("label", frequency.getKey());
			chart.add(column);
		}
		return chart;
	}

}
