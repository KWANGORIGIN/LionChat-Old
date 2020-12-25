package psu.lionchat.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import psu.lionchat.dao.LionChatDAO;
import psu.lionchat.model.IntentRatingsModel;

@Controller
public class AnalyticsController {
	/**
	 * Get the home page of LionChat.
	 * @return the model with the data for the charts (dataPoints1,2,3,4) 
	 * and the view (Analytics/MultipleChartsInAPage) to display.
	 * */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getHomePage(ModelMap model) {
		ModelAndView modelAndView = new ModelAndView("Analytics/MultipleChartsInAPage");
		LionChatDAO dao;
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
			dao = (LionChatDAO) context.getBean("LionChatDAOImpl");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("Analytics/Error");
		}
		
		List<Object> averageIntentRatings = this.getAverageRatingsMap(dao);
		List<Object> overallRatings = this.getOverallRatingsMap(dao);
		List<Object> intentFrequencies = this.getFrequenciesMap(dao);
		List<Object> commonlyMisclassifiedIntents = getCommonlyMisclassifiedIntentsMap(dao);

		Gson gsonObj = new Gson();
		modelAndView.addObject("title", "Home");
		modelAndView.addObject("dataPoints1", gsonObj.toJson(averageIntentRatings));
		modelAndView.addObject("dataPoints2", gsonObj.toJson(overallRatings));
		modelAndView.addObject("dataPoints3", gsonObj.toJson(intentFrequencies));
		modelAndView.addObject("dataPoints4", gsonObj.toJson(commonlyMisclassifiedIntents));

		return modelAndView;
	}

	/**
	 * Get the overall ratings of LionChat. This is a pie chart with 
	 * the percentage of 1/2/3/4/5 star ratings out of the total for each slice.
	 * @return the model with the data for the pie chart (dataPoints)
	 * and the view (Analytics/OverallRatings) to display.
	 * */
	@RequestMapping(value = "/view-analytics/overall-ratings", method = RequestMethod.GET)
	public ModelAndView getOverallRatings() {
		ModelAndView modelAndView = new ModelAndView("Analytics/OverallRatings");
		LionChatDAO dao;
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
			dao = (LionChatDAO) context.getBean("LionChatDAOImpl");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("Analytics/Error");
		}

		AtomicInteger total = new AtomicInteger();
		List<Object> chart = getOverallRatingsMap(dao, total);

		Gson gsonObj = new Gson();

		modelAndView.addObject("title", "Average Overall Ratings");
		modelAndView.addObject("total", total);
		modelAndView.addObject("dataPoints", gsonObj.toJson(chart));

		return modelAndView;
	}

	/**
	 * Get each intents average rating (out of 5 stars) for LionChat.
	 * @return the model with the data for the column chart (dataPoints)
	 * and the view (Analytics/IntentAverageRatings) to display.
	 * */
	@RequestMapping(value = "/view-analytics/intent-average-ratings", method = RequestMethod.GET)
	public ModelAndView getAverageRatings() {
		ModelAndView modelAndView = new ModelAndView("Analytics/IntentAverageRatings");
		LionChatDAO dao;
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
			dao = (LionChatDAO) context.getBean("LionChatDAOImpl");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("Analytics/Error");
		}

		List<Object> chart = getAverageRatingsMap(dao);

		Gson gsonObj = new Gson();
		modelAndView.addObject("title", "Intent Average Ratings");
		modelAndView.addObject("dataPoints", gsonObj.toJson(chart));

		return modelAndView;
	}

	/**
	 * Get the frequently asked questions for LionChat. This is a column chart
	 * measuring the frequency of each intent.
	 * @return the model with the data for the column chart (dataPoints)
	 * and the view (Analytics/FrequentlyAskedQuestions) to display.
	 * */
	@RequestMapping(value = "/view-analytics/frequently-asked-questions")
	public ModelAndView getFrequentlyAskedQuestions() {
		ModelAndView modelAndView = new ModelAndView("Analytics/FrequentlyAskedQuestions");
		LionChatDAO dao;
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
			dao = (LionChatDAO) context.getBean("LionChatDAOImpl");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("Analytics/Error");
		}

		List<Object> chart = getFrequenciesMap(dao);

		Gson gsonObj = new Gson();
		modelAndView.addObject("title", "Frequently Asked Questions");
		modelAndView.addObject("dataPoints", gsonObj.toJson(chart));

		return modelAndView;
	}

	/**
	 * Gets the intents which are commonly misclassified by displaying 
	 * their number of 1 star ratings.
	 * @return the model with the data for the column chart (dataPoints)
	 * and the view (Analytics/CommonlyMisclassifiedIntents) to display.
	 * */
	@RequestMapping(value = "/view-analytics/commonly-misclassified-intents")
	public ModelAndView getCommonlyMisclassifiedIntents() {
		ModelAndView modelAndView = new ModelAndView("Analytics/CommonlyMisclassifiedIntents");
		LionChatDAO dao;
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
			dao = (LionChatDAO) context.getBean("LionChatDAOImpl");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("Analytics/Error");
		}

		List<Object> chart = getCommonlyMisclassifiedIntentsMap(dao);

		Gson gsonObj = new Gson();
		modelAndView.addObject("title", "Commonly Misclassified Intents");
		modelAndView.addObject("dataPoints", gsonObj.toJson(chart));

		return modelAndView;
	}

	/**
	 * Gets the data for the overall ratings chart.
	 * @param dao - the database access object to use to obtain the data.
	 * */
	private List<Object> getOverallRatingsMap(LionChatDAO dao) {
		return this.getOverallRatingsMap(dao, null);
	}

	/**
	 * Gets the data for the overall ratings chart.
	 * @param dao - the database access object to use to obtain the data.
	 * @param passedTotal - the variable to store the total number of ratings to.
	 * */
	private List<Object> getOverallRatingsMap(LionChatDAO dao, AtomicInteger passedTotal) {
		int total = 0;
		Map<Integer, AtomicInteger> map = new HashMap<>();
		for (IntentRatingsModel point : dao.getRatings()) {
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
	
	/**
	 * Gets the data for the average ratings chart.
	 * @param dao - the database access object to use to obtain the data.
	 * */
	private List<Object> getAverageRatingsMap(LionChatDAO dao) {
		Map<String, AtomicInteger> totalStars = new HashMap<String, AtomicInteger>();
		Map<String, AtomicInteger> totalRatings = new HashMap<String, AtomicInteger>();
		for (IntentRatingsModel point : dao.getRatings()) {
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

	/**
	 * Gets the data for the frequencies chart (for frequently asked questions).
	 * @param dao - the database access object to use to obtain the data.
	 * */
	private List<Object> getFrequenciesMap(LionChatDAO dao) {
		Map<String, AtomicInteger> frequencies = new HashMap<>();
		for (IntentRatingsModel point : dao.getRatings()) {
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
	
	/**
	 * Gets the data for the commonly misclassified intents chart.
	 * @param dao - the database access object to use to obtain the data.
	 * */
	private List<Object> getCommonlyMisclassifiedIntentsMap(LionChatDAO dao) {
		Map<String, AtomicInteger> frequencies = new HashMap<>();
		for (IntentRatingsModel point : dao.getRatings()) {
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
