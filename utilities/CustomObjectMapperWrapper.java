package com.utilities;

import java.text.SimpleDateFormat;
import java.util.List;

import com.contract.ObjectMapperContract;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomObjectMapperWrapper implements ObjectMapperContract {

	private final ObjectMapper objectMapper;

	public CustomObjectMapperWrapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public String writeValueAsString(Object value) throws JsonProcessingException {
		return objectMapper.writeValueAsString(value);
	}

	@Override
	public <T> T readValue(String content, Class<T> valueType) throws JsonProcessingException {
		return objectMapper.readValue(content, valueType);
	}

	@Override
	public <T> List<T> readValue(String content, TypeReference<List<T>> valueTypeRef) throws JsonProcessingException {
		return objectMapper.readValue(content, valueTypeRef);
	}

	@Override
	public void setDateFormat(SimpleDateFormat dateFormat) {
		objectMapper.setDateFormat(dateFormat);
	}
}