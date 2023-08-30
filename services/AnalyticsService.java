package com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.contract.AnalyticsDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.DTO.EnquiryAnalyticsStatusDTO;

public class AnalyticsService {
	// amalytics
	private AnalyticsDao analyticsDao;

	@Autowired
	public AnalyticsService(AnalyticsDao analyticsDao) {
		this.analyticsDao = analyticsDao;

	}

	public String buildAnalyticsJSONObject() {

		List<EnquiryAnalyticsStatusDTO> enquriesStatusModel = analyticsDao.getEnquiresDataByStatus();

		ObjectMapper mapper = new ObjectMapper();
		String jsondata = null;

		try {
			jsondata = mapper.writeValueAsString(enquriesStatusModel);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsondata;

	}

}
