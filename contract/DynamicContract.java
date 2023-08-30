package com.contract;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.customExceptions.BlankFormException;
import com.customExceptions.InsertException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.models.Input.RequestFormModel;
import com.models.Output.CommunicationData;
import com.models.Output.DynamicFormModel;
import com.models.Output.EnqFieldsModel;
import com.models.Output.RFPDynamicFormmModel;
import com.models.Output.RFPFieldsModel;
import com.models.Output.RFPRequestFormModel;

public interface DynamicContract {

	public String generateUUID();

	public <T> boolean checkIfIsNull(List<T> list) throws BlankFormException;

	public DynamicFormModel extractDynamicFormModel(MultipartHttpServletRequest request) throws JsonProcessingException;

	public RFPDynamicFormmModel extractRFPDynamicFormModel(MultipartHttpServletRequest request)
			throws JsonProcessingException;

	public String getCustomerResponseAsJson(Integer enquiryId) throws JsonProcessingException, SQLException;

	public boolean UpdateAndSendMail(RequestFormModel requestFormModel, String enquiryData)
			throws InsertException, BlankFormException, JsonProcessingException, SQLException;

	public boolean extractAndUpdateFormDetails(MultipartHttpServletRequest request, HttpServletRequest req)
			throws BlankFormException, IOException;

	public List<EnqFieldsModel> getFieldsData(String formIdentifier) throws SQLException;

	// --------------------------------------RFP----------------------------------------------
	public String getRFPCustomerResponseAsJson(Integer enquiryId) throws JsonProcessingException, SQLException;

	public boolean UpdateRFPAndSendMail(RFPRequestFormModel requestFormModel, String rfpData)
			throws InsertException, BlankFormException, JsonProcessingException, SQLException;

	public boolean extractAndUpdateRFPFormDetails(MultipartHttpServletRequest request, HttpServletRequest req)
			throws BlankFormException, IOException, InsertException;

	public List<RFPFieldsModel> getRFPFieldsData(String formIdentifier) throws SQLException;

	// --------------------------------------ApprovalForm------------------------------------
	public boolean updateApprovalStatus(CommunicationData communicationData) throws SQLException;

}
