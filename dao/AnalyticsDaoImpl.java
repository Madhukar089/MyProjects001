package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.contract.AnalyticsDao;
import com.mappers.AdminUserMapper;
import com.mappers.EnquiryAnalyticsStatusDTORowMapper;
import com.models.DTO.AdminUsersDTO;
import com.models.DTO.EnquiryAnalyticsStatusDTO;

public class AnalyticsDaoImpl implements AnalyticsDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<EnquiryAnalyticsStatusDTO> getEnquiresDataByStatus() {

		final String getDataByStatus = "select count(*),enqr_status  from tracker_enquiries group by enqr_status";

		return jdbcTemplate.query(getDataByStatus, new EnquiryAnalyticsStatusDTORowMapper());
	}

	@Override
	public List<AdminUsersDTO> getAdminUsers() {

		String getAdminData = "select * from tracker_auth_users";

		return jdbcTemplate.query(getAdminData, new AdminUserMapper());
	}

}
