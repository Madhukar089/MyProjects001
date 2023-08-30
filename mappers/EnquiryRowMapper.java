
package com.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.models.Input.TrackerEnquiryConfig;

public class EnquiryRowMapper implements RowMapper<TrackerEnquiryConfig> {

	@Override
	public TrackerEnquiryConfig mapRow(ResultSet rs, int rowNum) throws SQLException {

		TrackerEnquiryConfig enquiry = new TrackerEnquiryConfig();

		enquiry.setEnqrid(rs.getInt("enqr_id"));
		enquiry.setEnqcustid(rs.getInt("enqr_cust_id"));
		enquiry.setEnqrcreatedby(rs.getString("enqr_createdby"));
		enquiry.setEnqrsubject(rs.getString("enqr_subject"));

		enquiry.setEnqrassignto(rs.getString("enqr_assigned_to"));
		enquiry.setEnqrluser(rs.getString("enqr_luser"));

		return enquiry;
	}
}