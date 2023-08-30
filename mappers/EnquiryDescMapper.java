package com.mappers;

import java.sql.ResultSet;


import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.models.Input.EnquiryDocumentModel;


public class EnquiryDescMapper implements RowMapper<EnquiryDocumentModel> {

	@Override
	public EnquiryDocumentModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		EnquiryDocumentModel enquiryDocumentModel = new EnquiryDocumentModel();

		enquiryDocumentModel.setDesc(rs.getString("enqr_subject"));
		enquiryDocumentModel.setCustId(rs.getInt("enqr_id"));

		return enquiryDocumentModel;
	}

}
