package com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mappers.EnquiryMasterRowMapper;
import com.models.DTO.EnquriyMasterModel;

public class MasterRFPprocessTracker {

	// process
	@Autowired
	JdbcTemplate jdbcTemplate;

	public boolean insertCustomerStagedData(Integer custId) {

		String updateMasterCustId = "insert into tracker_masterrfp (cust_id) values (?)";

		return jdbcTemplate.update(updateMasterCustId, custId) > 0;

	}

	public boolean insertEnquiryIdStagedData(Integer enqrId, Integer custId) {
		String updateMasterEnqrId = "insert into tracker_masterrfp (cust_id,enqr_id) values (?,?)";

		return jdbcTemplate.update(updateMasterEnqrId, custId, enqrId) > 0;
	}

	public EnquriyMasterModel getEnquiryData(Integer enqrId) {
		String getMasterData = "select enqr_cust_id,enqr_id,enqr_date,enqr_status,enqr_assigned_to from tracker_enquiries where enqr_id=?";

		return jdbcTemplate.queryForObject(getMasterData, new Object[] { enqrId }, new EnquiryMasterRowMapper());
	}

	public boolean insertSalesTeamData(EnquriyMasterModel masterModelData) {

		String insertSalesDataIntoMaster = "insert into tracker_masterrfp (cust_id,enqr_id,sales_user_id,request_date,status) values(?,?,?,?,?)";

		Object[] params = { masterModelData.getEnqrCustId(), masterModelData.getEnqrId(),
				masterModelData.getEnqrAssignedTo(), masterModelData.getEnqrDate(), masterModelData.getEnqrStatus() };

		return jdbcTemplate.update(insertSalesDataIntoMaster, params) > 0;

	}

	public boolean udpatePresalesUserStatusBySalesUser(String preSalesUser, Integer enqrId) {
		preSalesUser = preSalesUser.trim();
		String updatePresalesUser = "update  tracker_masterrfp  set presales_user_id=?,assigned_to_presales_date=current_date where enqr_id=? and sales_user_id is not null";

		return jdbcTemplate.update(updatePresalesUser, preSalesUser, enqrId) > 0;

	}

	public boolean updateTechTeamUserByPresales(Integer rfpId, String preSalesUser, Integer enqrId) {
		preSalesUser = preSalesUser.trim();
		String updatePresalesUser = "update  tracker_masterrfp  set rfp_id = ?, technical_team_user_id=?,assigned_to_technical_team_date=current_date where enqr_id=? and sales_user_id is not null and presales_user_id is not null";

		return jdbcTemplate.update(updatePresalesUser, rfpId, preSalesUser, enqrId) > 0;
	}

	public boolean updateRfprDetailsMaster(Integer rfprId, String user, String rfpstatus) {

		String updateMasterRfpStatus = "update  tracker_masterrfp  set  review_manager_user_id=?,assigned_to_review_manager_date=current_date, rfp_status =? where rfp_id=? and sales_user_id is not null and presales_user_id is not null and technical_team_user_id is not null";

		return jdbcTemplate.update(updateMasterRfpStatus, user, rfpstatus, rfprId) > 0;

	}

}
