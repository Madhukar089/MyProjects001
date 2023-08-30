package com.mappers;

import java.sql.ResultSet;

import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.models.Output.EnqIterationModel;


public class EnqIterationMapper implements RowMapper<EnqIterationModel> {

	@Override
	public EnqIterationModel mapRow(ResultSet resultSet, int i) throws SQLException {
		EnqIterationModel enq = new EnqIterationModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getDate(3),
				resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
		return enq;
	}

}