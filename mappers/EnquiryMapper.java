package com.mappers;

import java.sql.ResultSet;

import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.models.Output.Enquiries;


public class EnquiryMapper implements RowMapper<Enquiries> {
	@Override
	public Enquiries mapRow(ResultSet resultSet, int i) throws SQLException {
		Enquiries enq = new Enquiries(resultSet.getInt("enqr_id"), resultSet.getDate("enqr_date"),
				resultSet.getString("enqr_createdby"), resultSet.getInt(4), resultSet.getString(5),
				resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getDate(9),
				resultSet.getString(10));
		return enq;
	}

}