package com.contract;

import java.util.List;

import com.models.DTO.AdminUsersDTO;
import com.models.DTO.EnquiryAnalyticsStatusDTO;

public interface AnalyticsDao {
	// ana
	List<EnquiryAnalyticsStatusDTO> getEnquiresDataByStatus();

	List<AdminUsersDTO> getAdminUsers();
}
