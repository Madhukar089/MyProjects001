package com.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mappers.TrackerEnquiryRowMapper;
import com.models.Output.TrackerEnquiry;

public class EnquiryFilter {

	@Autowired
	JdbcTemplate jdbcTemplate;;

	public List<TrackerEnquiry> filterEnquiryData(String enqrId, String enqrStatus, String enqrCreatedBy, String user) {
		StringBuilder sqlBuilder = new StringBuilder(
				"SELECT * FROM tracker_enquiries where enqr_assigned_to=? and enqr_id NOT IN (SELECT e_id FROM rejectedenquiries)");
		List<Object> parameters = new ArrayList<>();
		parameters.add(user);
		if (enqrId != null && !enqrId.isEmpty()) {
			sqlBuilder.append(" AND enqr_id = ? ");
			;
			parameters.add(Integer.parseInt(enqrId));
		}

		if (enqrStatus != null && !enqrStatus.isEmpty()) {
			sqlBuilder.append(" AND enqr_status = ? ");
			parameters.add(enqrStatus);
		}

		if (enqrCreatedBy != null && !enqrCreatedBy.isEmpty()) {
			sqlBuilder.append(" AND enqr_createdby LIKE ? ");
			parameters.add("%" + enqrCreatedBy + "%");
		}

		String sql = sqlBuilder.toString();

		return jdbcTemplate.query(sql, parameters.toArray(), new TrackerEnquiryRowMapper());
	}
}