package com.mappers;

import java.sql.ResultSet;

import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.models.Output.ProfileOutput;


public class ProfileMapper implements RowMapper<ProfileOutput> {

	@Override
	public ProfileOutput mapRow(ResultSet rs, int rowNum) throws SQLException {

		ProfileOutput user = new ProfileOutput();
		user.setAusrId(rs.getString("ausr_id"));
		user.setAusrOffEmail(rs.getString("ausr_offemail"));
		user.setAusrMobileNo(rs.getLong("ausr_mobileno"));
		user.setFullName(rs.getString("fullname"));
		//user.setAusrProfile(rs.getBytes("ausr_profile"));
		return user;

	}

}
