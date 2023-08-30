package com.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.contract.ProfileDAO;
import com.mappers.ProfileMapper;
import com.models.Input.ProfileInput;
import com.models.Output.ProfileOutput;

public class ProfileDaoImpl implements ProfileDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static Logger LOGGER = Logger.getLogger(ProfileDaoImpl.class);

	public void updateProfile(ProfileInput user) {
		try {
			// Update the profile information of a user in the tracker_auth_users table
			String sql = "UPDATE tracker_auth_users SET fullname=?, ausr_mobileno=?,ausr_offemail=? WHERE ausr_id=?";
			jdbcTemplate.update(sql, user.getFullName(), user.getAusrMobileNo(), user.getAusrOffEmail(),
					user.getAusrId());
			LOGGER.info("Profile information updated for user with ausr_id: " + user.getAusrId());
		} catch (Exception e) {
			LOGGER.error("Error occurred while updating profile information for user with ausr_id: " + user.getAusrId(),
					e);
		}
	}

	@Override
	public ProfileOutput getProfileByUser(String loginuser) {
		try {
			// Retrieve the profile information of a user from the tracker_auth_users table
			String sql = "SELECT * FROM tracker_auth_users WHERE ausr_id=?";
			ProfileOutput profile = jdbcTemplate.queryForObject(sql, new Object[] { loginuser }, new ProfileMapper());
			LOGGER.info("Profile information retrieved for user with ausr_id: " + loginuser);
			return profile;
		} catch (Exception e) {
			LOGGER.error("Error occurred while retrieving profile information for user with ausr_id: " + loginuser, e);
			return null; // or handle the error case appropriately
		}
	}

}
