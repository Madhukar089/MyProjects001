package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.services.AnalyticsService;

@Controller
public class AnalyticsController {
	@Autowired
	AnalyticsService analyticsService;

	// ana
	@RequestMapping("analytics")
	public String getEnquriesByStatus(Model model) {
		String jsondata = analyticsService.buildAnalyticsJSONObject();
		model.addAttribute("enquirydata", jsondata);
		return "analytics";

	}

	@RequestMapping("statusanalytics")
	public String progressBar(Model model) {
		String jsondata = analyticsService.buildAnalyticsJSONObject();
		model.addAttribute("enquirydata", jsondata);
		return "statusAnalytics";

	}

}
