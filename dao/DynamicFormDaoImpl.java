package com.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.contract.DynamicFormDAO;
import com.customExceptions.BlankFormException;
import com.customExceptions.InsertException;
import com.mappers.CustomerRFPResponseMapper;
import com.mappers.CustomerResponseMapper;
import com.mappers.MissingRFPResponseMapper;
import com.mappers.MissingResponseMapper;
import com.mappers.RFPFieldMapper;
import com.mappers.TrackerEnquiryFieldMapper;
import com.models.DTO.CustomerRFPResponseDTO;
import com.models.DTO.CustomerResponseDTO;
import com.models.DTO.EnqFieldsDTO;
import com.models.DTO.MissingRFPResponseDTO;
import com.models.DTO.MissingResponseDTO;
import com.models.DTO.RFPFieldsDTO;
import com.models.Input.RequestFormModel;
import com.models.Output.CommunicationData;
import com.models.Output.DynamicFormField;
import com.models.Output.DynamicFormModel;
import com.models.Output.EnqFieldsModel;
import com.models.Output.RFPDynamicFormField;
import com.models.Output.RFPDynamicFormmModel;
import com.models.Output.RFPFieldsModel;
import com.models.Output.RFPRequestFormModel;

public class DynamicFormDaoImpl implements DynamicFormDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	private static final Logger LOGGER = Logger.getLogger(DynamicFormDaoImpl.class);

	private final String getCustomerEmail = "select cust_email from tracker_customers where cust_id = ?";
	private final String selectMaxIterNo = "SELECT COALESCE(MAX(enqr_iteration_index), 0) AS maxIndex FROM tracker_EnquiryIterations WHERE enqr_id = ?";
	private final String insertIntoIterations = "INSERT INTO tracker_EnquiryIterations (enqr_id, enqr_iteration_index, enqi_cdate, enqi_createdby, enqi_purpose, enqi_status) VALUES (?, ?, CURRENT_DATE, ?, ?, ?)";
	private final String insertIntoFields = "INSERT INTO tracker_EnquiryFields (enqr_id, enqr_iteration_index, enqr_uuid, enqr_fieldid, enqr_fieldtype,enqr_defaultvalues, enqr_fieldorder, enqr_label) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private final String insertIntoFieldsData = "INSERT INTO tracker_EnquiryFieldsData (enqr_id, enqr_iteration_index, enqr_uuid, enqr_fieldid, enqr_label, enqr_value) VALUES (?, ?, ?, ?, ?, ?)";
	private final String getFieldData = "SELECT * FROM tracker_EnquiryFields WHERE enqr_uuid=?";
	private final String getResponseFromCustomer = "select * from enquiry_details_view where enqr_id = ?";
	private final String getMissingResponseFromCustomer = "select * from enquiryFieldsMissingData where enqr_id = ?";
	// ------------------------RFP----------------------------
	private final String selectMaxIterNoRfp = "SELECT COALESCE(MAX(rfpr_iteration_index), 0) AS maxIndex FROM tracker_RfprIterations WHERE rfpr_id = ?";
	private final String insertIntoIterationsRfp = "INSERT INTO tracker_RfprIterations (rfpr_id, rfpr_iteration_index, rfpr_cdate, rfpr_createdby, rfpr_purpose, rfpr_status) VALUES (?, ?, CURRENT_DATE, ?, ?, ?)";

	private final String getRfpCustomerEmail = "select cust_email from tracker_customers where cust_id = (select enqr_cust_id from tracker_enquiries where enqr_id = (select rfpr_enqr_id from tracker_rfp where rfpr_id = ?))";
	private final String insertIntoRfpFields = "INSERT INTO tracker_RfpFields (rfpr_id, rfpr_iteration_index, rfpr_uuid, rfpr_fieldid, rfpr_fieldtype,rfpr_defaultvalues, rfpr_fieldorder, rfpr_label) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private final String getRfpFieldData = "SELECT * FROM tracker_RfpFields WHERE rfpr_uuid=?";
	private final String insertIntoRfpFieldsData = "INSERT INTO tracker_RfpFieldsData (rfpr_id, rfpr_iteration_index, rfpr_uuid, rfpr_fieldid, rfpr_label, rfpr_value) VALUES (?, ?, ?, ?, ?, ?)";
	private final String selectMaxDocIndex = "select max(rfpd_docindex) from tracker_rfpr_documents where rfpr_id = ?";
	private final String updateApprovalStatus = "Update tracker_rfpr_documents set rpdf_status= ? where rfpr_id = ? and rfpd_docindex = ?";
	private final String getRFPResponseFromCustomer = "select * from rfp_details_view  where rfpr_id = ?";
	private final String getRFPMissingResponseFromCustomer = "select * from RfpFieldsMissingData where rfpr_id = ?";
	private final String updateEnqrStatus = "update tracker_enquiries set enqr_status = 'ENQR_COMPLETED' where enqr_id = (select rfpr_enqr_id from tracker_rfp where rfpr_id = (select rfpr_id from tracker_rfpr_documents where rfpd_id = ?))";

	@Override
	public int createEnquiryIterations(RequestFormModel requestFormModel) throws InsertException {
		// Retrieve the current maximum iteration index for the enqId
		Integer maxIndex = jdbcTemplate.queryForObject(selectMaxIterNo, Integer.class, requestFormModel.getEnqId());

		int iterationIndex = (maxIndex != null) ? maxIndex + 1 : 1;
		// Insert the new entry with the provided form data
		int result = jdbcTemplate.update(insertIntoIterations, requestFormModel.getEnqId(), iterationIndex,
				requestFormModel.getCreatedBy(), requestFormModel.getPurpose(), requestFormModel.getStatus());

		if (result > 0) {
			// Update operation performed
			LOGGER.info("Enquiry Iteration for the current enquiry has been successfully updated!");
			return iterationIndex;
		} else {
			// Update operation failed
			LOGGER.error("Failed to insert the iteration into the database: ");
			throw new InsertException();
		}
	}

	@Override
	public String getCustomerEmail(RequestFormModel requestFormModel) {
		LOGGER.info("Successfully Fetched the email of specific Customer");
		String email = jdbcTemplate.queryForObject(getCustomerEmail, String.class, requestFormModel.getCustId());
		return email;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<CustomerResponseDTO> getCustomerResponseByNo(Integer eno) {
		LOGGER.info("Successfully fetched the details of specific customer response");
		return jdbcTemplate.query(getResponseFromCustomer, new Object[] { eno }, new CustomerResponseMapper());
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<MissingResponseDTO> getMissingResponses(Integer eno) {
		LOGGER.info("Successfully fetched the details of specific customer's missing response");
		return jdbcTemplate.query(getMissingResponseFromCustomer, new Object[] { eno }, new MissingResponseMapper());
	}

	@Override
	public boolean createtrackerFormDetails(List<EnqFieldsDTO> enqFields, String uuid, int iterId)
			throws BlankFormException {
		int rowsAffected = 0;
		for (EnqFieldsDTO enquiryFields : enqFields) {
			rowsAffected = jdbcTemplate.update(insertIntoFields, enquiryFields.getEnqrId(), iterId, uuid,
					enquiryFields.getEnqrFieldId(), enquiryFields.getFieldType(), enquiryFields.getDefaultValues(),
					enquiryFields.getEnqrFieldOrder(), enquiryFields.getLabel());
		}
		if (rowsAffected > 0) {

			LOGGER.info("Successfully Updated the details of form in the database!");
			return true;
		} else {
			// Update operation failed
			System.out.println(rowsAffected);

			LOGGER.info("Failed to Updated the details of form into the database!");
			throw new BlankFormException();
		}

	}

	@SuppressWarnings("deprecation")
	@Override
	public List<EnqFieldsModel> getTrackerFieldData(String formIdentifier) {
		LOGGER.info("Successfully fetched the details of specific form from database");
		return jdbcTemplate.query(getFieldData, new Object[] { formIdentifier }, new TrackerEnquiryFieldMapper());
	}

	@Override
	public void createCustomerFormDetails(DynamicFormModel dm) throws BlankFormException {
		List<DynamicFormField> dff = dm.getFields();
		int result = 0;
		for (DynamicFormField field : dff) {
			result = jdbcTemplate.update(insertIntoFieldsData, dm.getEnqrId(), dm.getEnqrIterationIndex(),
					dm.getEnqrUuid(), field.getEnqrFieldId(), field.getEnqrLabel(), field.getEnqrValue());
			// Check the result of the update operation
		}
		if (result <= 0) {
			LOGGER.error("Failed to update the details of Customer's Response : ");
			throw new BlankFormException();
		}
		LOGGER.info("Successfully Updated the details of specific Customer's response");
	}

	// -----------------------------RFPs---------------------------------

	@Override
	@Transactional
	public int CreateRfpIterations(RFPRequestFormModel requestFormModel) throws InsertException {
		// Retrieve the current maximum iteration index for the enqId
		Integer maxIndex = jdbcTemplate.queryForObject(selectMaxIterNoRfp, Integer.class, requestFormModel.getRfpId());
		int iterationIndex = (maxIndex != null) ? maxIndex + 1 : 1;

		// Insert the new entry with the provided form data
		int result = jdbcTemplate.update(insertIntoIterationsRfp, requestFormModel.getRfpId(), iterationIndex,
				requestFormModel.getCreatedBy(), requestFormModel.getPurpose(), requestFormModel.getStatus());

		if (result > 0) {
			// Update operation performed
			LOGGER.info("RFP Iteration for the current enquiry has been successfully updated!");
			return iterationIndex;
		} else {
			// Update operation failed
			LOGGER.error("Failed to insert the iteration into the database: ");
			throw new InsertException();
		}
	}

	@Override
	public String getRfpCustomerEmail(RFPRequestFormModel requestFormModel) {
		LOGGER.info("Successfully Fetched the email of specific Customer");
		String email = jdbcTemplate.queryForObject(getRfpCustomerEmail, String.class, requestFormModel.getRfpId());
		return email;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<CustomerRFPResponseDTO> getRFPCustomerResponseByNo(Integer eno) {
		LOGGER.info("Successfully fetched the details of specific customer response");
		return jdbcTemplate.query(getRFPResponseFromCustomer, new Object[] { eno }, new CustomerRFPResponseMapper());
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<MissingRFPResponseDTO> getRFPMissingResponses(Integer eno) {
		LOGGER.info("Successfully fetched the details of specific customer's missing response");
		return jdbcTemplate.query(getRFPMissingResponseFromCustomer, new Object[] { eno },
				new MissingRFPResponseMapper());
	}

	@Override
	@Transactional
	public void createRfpFormDetails(List<RFPFieldsDTO> rfpFields, String uuid, int iterId) throws BlankFormException {
		int rowsAffected = 0;
		for (RFPFieldsDTO rfpField : rfpFields) {
			rowsAffected = jdbcTemplate.update(insertIntoRfpFields, rfpField.getRfprId(), iterId, uuid,
					rfpField.getRfprFieldId(), rfpField.getFieldType(), rfpField.getDefaultValues(),
					rfpField.getRfprFieldOrder(), rfpField.getLabel());
		}
		if (rowsAffected > 0) {
			// Update operation performed
			System.out.println(rowsAffected);
			LOGGER.info("Successfully Updated the details of form in the database!");
		} else {
			// Update operation failed
			System.out.println(rowsAffected);
			LOGGER.info("Failed to Updated the details of form into the database!");
			throw new BlankFormException();
		}

	}

	@SuppressWarnings("deprecation")
	@Override
	public List<RFPFieldsModel> getRfpFieldData(String formIdentifier) {
		LOGGER.info("Successfully fetched the details of specific form from database");
		return jdbcTemplate.query(getRfpFieldData, new Object[] { formIdentifier }, new RFPFieldMapper());
	}

	@Override
	public void createRfpCustomerFormDetails(RFPDynamicFormmModel dynamicFormModel) throws InsertException {
		List<RFPDynamicFormField> dff = dynamicFormModel.getFields();
		for (RFPDynamicFormField field : dff) {
			int result = jdbcTemplate.update(insertIntoRfpFieldsData, dynamicFormModel.getRfprId(),
					dynamicFormModel.getRfprIterationIndex(), dynamicFormModel.getRfprUuid(), field.getRfprFieldId(),
					field.getRfprLabel(), field.getRfprValue());
			// Check the result of the update operation
			if (result <= 0) {
				LOGGER.error("Failed to insert the details of form into database : ");
				throw new InsertException();
			}
		}
		LOGGER.info("Successfully Updated the details of specific Customer's response");
	}

	@Override
	public void updateApprovalStatus(CommunicationData communicationData) {
		Integer maxIndex = jdbcTemplate.queryForObject(selectMaxDocIndex, Integer.class,
				communicationData.getRfpr_id());
		int result = jdbcTemplate.update(updateApprovalStatus, communicationData.getStatus(),
				communicationData.getRfpr_id(), maxIndex);

		jdbcTemplate.update(updateEnqrStatus, communicationData.getRfpr_id());

		if (result <= 0) {
			LOGGER.error("Failed to insert the details of Approval form into database : ");
		}
		LOGGER.info("Successfully updated the appoval/reject status of specific Customer");
	}

}