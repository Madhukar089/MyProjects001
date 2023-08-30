package com.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.models.Input.RejectedEnquiry;

public class RejecetdEnquiryMapper implements RowMapper<RejectedEnquiry> {
	@Override
	public RejectedEnquiry mapRow(ResultSet rs, int i) throws SQLException {
		RejectedEnquiry rejectedEnq = new RejectedEnquiry();
		rejectedEnq.setEnqrId(rs.getInt("e_id"));
		rejectedEnq.setEnqrDescription(rs.getString("rej_desc"));

		return rejectedEnq;
	}

}
