package com.utilities;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Value;

import com.contract.DateFormatContract;

public class CustomDateFormatWrapper implements DateFormatContract {

	@Value("${date.format:yyyy-MM-dd}")
	private String dateFormatPattern;

	@Override
	public SimpleDateFormat getDateFormat() {
		return new SimpleDateFormat(dateFormatPattern);
	}
}
