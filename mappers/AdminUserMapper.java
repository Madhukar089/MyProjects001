package com.mappers;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.models.DTO.AdminUsersDTO;

public class AdminUserMapper implements RowMapper<AdminUsersDTO> {

	@Override
	public AdminUsersDTO mapRow(ResultSet resultSet, int i) throws SQLException {
		BigInteger mobile = resultSet.getBigDecimal(7).toBigInteger();
		AdminUsersDTO admin = new AdminUsersDTO(resultSet.getString(1), resultSet.getString(8), resultSet.getString(2),
				mobile, resultSet.getTimestamp(4), resultSet.getString(6), resultSet.getString(5));
		return admin;
	}
}
