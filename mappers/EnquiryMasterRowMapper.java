package com.mappers;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.models.DTO.EnquriyMasterModel;

public class EnquiryMasterRowMapper implements RowMapper<EnquriyMasterModel> {
	// rowmapper
	@Override
	public EnquriyMasterModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		Integer enqrCustId = rs.getInt("enqr_cust_id");
		Integer enqrId = rs.getInt("enqr_id");
		Date enqrDate = rs.getDate("enqr_date");
		String enqrStatus = rs.getString("enqr_status");
		String enqrAssignedTo = rs.getString("enqr_assigned_to");

		return new EnquriyMasterModel(enqrCustId, enqrId, enqrDate, enqrStatus, enqrAssignedTo);
	}
}
