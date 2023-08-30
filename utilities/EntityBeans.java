package com.utilities;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import com.models.Entity.TrackerCustomer;
import com.models.Entity.TrackerEnquiryModel;


@Configuration
public class EntityBeans {

	@Bean
	TrackerCustomer trackerCustomer() {

		return new TrackerCustomer();
	}

	@Bean
	TrackerEnquiryModel trackerEnquiry() {

		return new TrackerEnquiryModel();

	}
}
