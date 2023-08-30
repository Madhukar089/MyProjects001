package com.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.models.DTO.MissingRFPResponseDTO;

public class MissingRFPResponseMapper implements RowMapper<MissingRFPResponseDTO> {
	@Override
	public MissingRFPResponseDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		MissingRFPResponseDTO enq = new MissingRFPResponseDTO(resultSet.getInt(1), resultSet.getInt(2),
				resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5), resultSet.getString(6),
				resultSet.getInt(7), resultSet.getString(8), resultSet.getDate(9));
		return enq;
	}
}
