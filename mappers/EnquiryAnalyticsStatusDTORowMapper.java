package com.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.models.DTO.EnquiryAnalyticsStatusDTO;

public class EnquiryAnalyticsStatusDTORowMapper implements RowMapper<EnquiryAnalyticsStatusDTO> {
	// mapper
	@Override
	public EnquiryAnalyticsStatusDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		Integer count = rs.getInt("count");
		String enqr_status = rs.getString("enqr_status");

		return new EnquiryAnalyticsStatusDTO(count, enqr_status);
	}
}
