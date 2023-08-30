package com.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.models.DTO.EnquiryDTO;

public class EnquiryDTOMapper implements RowMapper<EnquiryDTO> {

	@Override
	public EnquiryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		EnquiryDTO enquiryDTO = new EnquiryDTO();
		enquiryDTO.setEnqrId(rs.getInt("enqr_id"));
		enquiryDTO.setEnqrCreatedBy(rs.getString("enqr_createdby"));
		enquiryDTO.setEnqrCustId(rs.getInt("enqr_cust_id"));
		enquiryDTO.setEnqrSubject(rs.getString("enqr_subject"));
		enquiryDTO.setEnqrDesc(rs.getString("enqr_desc"));
		enquiryDTO.setEnqrStatus(rs.getString("enqr_status"));
		enquiryDTO.setEnqrAssignedTo(rs.getString("enqr_assigned_to"));
		enquiryDTO.setCustEmail(rs.getString("cust_email"));
		enquiryDTO.setCustType(rs.getString("cust_type"));
		enquiryDTO.setCustWebsite(rs.getString("cust_website"));
		return enquiryDTO;
	}
}
