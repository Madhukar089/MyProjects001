package com.services;

public class EnquiryService {

	public int getTotalPages(int totalEnquires) {

		int enqpageSize = 10; // Number of records to display per page
		int totalEnqPages = (int) Math.ceil(totalEnquires / (double) enqpageSize);

		return totalEnqPages;

	}

}
