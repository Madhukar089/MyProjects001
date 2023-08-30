
package com.mappers;

import java.sql.ResultSet;

import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.models.Output.Auth_users;

public class Auth_usersRowMapper implements RowMapper<Auth_users> {

	@Override
	public Auth_users mapRow(ResultSet rs, int rowNum) throws SQLException {

		Auth_users user = new Auth_users();

		user.setAusrId(rs.getString("ausr_id"));
		user.setAusrOffEmail(rs.getString("ausr_offemail"));
		user.setAusrPwd(rs.getString("ausr_pwd"));
		user.setAusrLlDateTime(rs.getDate("ausr_lldatetime"));
		user.setAusrStatus(rs.getString("ausr_status"));
		user.setDepartment(rs.getString("department"));
		user.setAusrMobileNo(rs.getLong("ausr_mobileno"));
		user.setAusrProfile(rs.getBytes("ausr_profile"));
		user.setFullName(rs.getString("fullname"));
		return user;

	}

}