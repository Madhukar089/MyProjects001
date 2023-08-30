package com.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.models.DTO.MissingResponseDTO;

public class MissingResponseMapper implements RowMapper<MissingResponseDTO> {
	@Override
	public MissingResponseDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		MissingResponseDTO enq = new MissingResponseDTO(resultSet.getInt(1), resultSet.getInt(2),
				resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5), resultSet.getString(6),
				resultSet.getInt(7), resultSet.getString(8), resultSet.getDate(9));
		return enq;
	}

}