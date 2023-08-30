package com.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mappers.RFPMapper;
import com.models.Output.TrackerRFP;

public class RFPFilter {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<TrackerRFP> filterRFPData(String rfprId, String rfprStatus, String rfprCreatedAusrId, String user) {
		StringBuilder sqlBuilder = new StringBuilder(
				"SELECT * FROM tracker_rfp where rfpr_status = 'RFPR_INPROGRESS' and rfpr_assignedto=?");
		List<Object> parameters = new ArrayList<>();

		parameters.add(user);

		if (rfprId != null && !rfprId.isEmpty()) {
			sqlBuilder.append(" AND rfpr_id = ? ");
			parameters.add(Integer.parseInt(rfprId));
		}

		if (rfprStatus != null && !rfprStatus.isEmpty()) {
			sqlBuilder.append(" AND rfpr_status = ? ");
			parameters.add(rfprStatus);
		}

		if (rfprCreatedAusrId != null && !rfprCreatedAusrId.isEmpty()) {
			sqlBuilder.append(" AND rfpr_created_ausr_id LIKE ? ");
			parameters.add("%" + rfprCreatedAusrId + "%");
		}

		String sql = sqlBuilder.toString();

		return jdbcTemplate.query(sql, parameters.toArray(), new RFPMapper());
	}

}
