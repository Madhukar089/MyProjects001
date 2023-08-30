package com.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.models.Output.ClosedAccountsModel;

public class ClosedAccountMapper implements RowMapper<ClosedAccountsModel> {
	@Override
	public ClosedAccountsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		ClosedAccountsModel enquiry = new ClosedAccountsModel(rs.getInt(1), rs.getDate(2), rs.getString(3),
				rs.getInt(4), rs.getString(5), rs.getString(6));
		return enquiry;

	}
}
