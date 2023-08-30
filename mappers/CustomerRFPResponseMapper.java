package com.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.models.DTO.CustomerRFPResponseDTO;

public class CustomerRFPResponseMapper implements RowMapper<CustomerRFPResponseDTO> {
	@Override
	public CustomerRFPResponseDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		CustomerRFPResponseDTO enq = new CustomerRFPResponseDTO(resultSet.getDate(1), resultSet.getInt(2),
				resultSet.getInt(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6),
				resultSet.getString(7));
		return enq;
	}
}
