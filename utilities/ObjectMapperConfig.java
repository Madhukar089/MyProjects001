package com.utilities;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.contract.DateFormatContract;
import com.contract.ObjectMapperContract;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class ObjectMapperConfig {
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	@Bean
	public ObjectMapperContract customObjectMapperWrapper(ObjectMapper objectMapper) {
		return new CustomObjectMapperWrapper(objectMapper);
	}

	@Bean
	public DateFormatContract customDateFormatWrapper() {
		return new CustomDateFormatWrapper();
	}
}