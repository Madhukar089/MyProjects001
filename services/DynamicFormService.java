package com.services;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.contract.CombinedCustomerRFPResponseContract;
import com.contract.CombinedCustomerResponseContract;
import com.contract.DateFormatContract;
import com.contract.DocumentContract;
import com.contract.DynamicContract;
import com.contract.DynamicFormDAO;
import com.contract.EmailContract;
import com.contract.ObjectMapperContract;
import com.customExceptions.BlankFormException;
import com.customExceptions.InsertException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.models.DTO.CustomerRFPResponseDTO;
import com.models.DTO.CustomerResponseDTO;
import com.models.DTO.EnqFieldsDTO;
import com.models.DTO.MissingRFPResponseDTO;
import com.models.DTO.MissingResponseDTO;
import com.models.DTO.RFPFieldsDTO;
import com.models.Input.RequestFormModel;
import com.models.Output.CommunicationData;
import com.models.Output.DynamicFormModel;
import com.models.Output.EnqFieldsModel;
import com.models.Output.RFPDynamicFormmModel;
import com.models.Output.RFPFieldsModel;
import com.models.Output.RFPRequestFormModel;

public class DynamicFormService implements DynamicContract {

	@Autowired
	private DynamicFormDAO dynamicFormDao;

	@Autowired
	private CombinedCustomerResponseContract combinedCustomerResponseContract;

	@Autowired
	private CombinedCustomerRFPResponseContract combinedCustomerRFPResponseContract;

	@Autowired
	private ObjectMapperContract customObjectMapperWrapper;

	@Autowired
	private DateFormatContract customDateFormatWrapper;

	@Autowired
	private EmailContract emailContract;

	@Autowired
	private DocumentContract documentContract;

	private static final Logger LOGGER = Logger.getLogger(DynamicFormService.class);

	public DynamicFormService() {

	}

	// ---------------------
	// Handles generation of Unique Id for the Dynamic form
	public String generateUUID() {
		return UUID.randomUUID().toString();
	}

	// Checks if the given input list is empty or null
	public <T> boolean checkIfIsNull(List<T> list) throws BlankFormException {
		if (list == null || list.isEmpty()) {
			LOGGER.error(new BlankFormException());
			throw new BlankFormException();
		} else {
			return true;
		}
	}

	public DynamicFormModel extractDynamicFormModel(MultipartHttpServletRequest request)
			throws JsonProcessingException {
		String dynamicFormModelJson = request.getParameter("dynamicFormModel");
		System.out.println(dynamicFormModelJson);
		DynamicFormModel dynamicFormModel = null;

		dynamicFormModel = customObjectMapperWrapper.readValue(dynamicFormModelJson, DynamicFormModel.class);

		return dynamicFormModel;
	}

	public RFPDynamicFormmModel extractRFPDynamicFormModel(MultipartHttpServletRequest request)
			throws JsonProcessingException {
		String dynamicFormModelJson = request.getParameter("dynamicFormModel");
		System.out.println(dynamicFormModelJson);
		RFPDynamicFormmModel dynamicFormModel = null;
		dynamicFormModel = customObjectMapperWrapper.readValue(dynamicFormModelJson, RFPDynamicFormmModel.class);

		return dynamicFormModel;
	}

	// ----------------------------------------Enquires---------------------------------------------

	// Get the response from the customers and also their missing responses which are not yet filled
	@Override
	public String getCustomerResponseAsJson(Integer enquiryId) throws JsonProcessingException {

		// Retrieve customer responses based on enquiryId
		List<CustomerResponseDTO> customerResponses = dynamicFormDao.getCustomerResponseByNo(enquiryId);
		// Retrieve missing responses based on enquiryId
		List<MissingResponseDTO> missingResponses = dynamicFormDao.getMissingResponses(enquiryId);

		// Assign customerResponses and missingResponses to combinedCustomerResponseContract
		combinedCustomerResponseContract.setCustomerResponseDTOs(customerResponses);
		combinedCustomerResponseContract.setMissingResponseDTOs(missingResponses);

		// Configure the date format
		SimpleDateFormat dateFormat = customDateFormatWrapper.getDateFormat();
		// Set the date format on the customObjectMapperWrapper
		customObjectMapperWrapper.setDateFormat(dateFormat);

		String json = "";
		// Converting the combinedCustomerResponseContract to JSON string
		json = customObjectMapperWrapper.writeValueAsString(combinedCustomerResponseContract);

		return json;

	}

	// Updates data and send email to the customer along with the dynamicForm's link
	@Override
	public boolean UpdateAndSendMail(RequestFormModel requestFormModel, String enquiryData)
			throws InsertException, BlankFormException, JsonProcessingException {
		// Get the customer email for sending the email
		String customerEmail = dynamicFormDao.getCustomerEmail(requestFormModel);
		// Generate a unique form identifier
		String formIdentifier = generateUUID();

		// DeSerialize the enquiryData into a list of EnqFieldsDTO objects
		List<EnqFieldsDTO> enqFields = customObjectMapperWrapper.readValue(enquiryData,
				new TypeReference<List<EnqFieldsDTO>>() {
				});
		checkIfIsNull(enqFields);

		// Create a new iteration for the request form
		int iterationIndex = dynamicFormDao.createEnquiryIterations(requestFormModel);
		// Update the EnquiryFields with the dynamicForm attributes
		dynamicFormDao.createtrackerFormDetails(enqFields, formIdentifier, iterationIndex);
		// Send the request email
		String to = customerEmail;
		String subject = "Action needed";
		emailContract.requestEmail(to, subject, formIdentifier);
		return true;
	}

	@Override
	// Retrieve EnquiryFields data based on the formIdentifier generated when sending the dynamicForm
	public List<EnqFieldsModel> getFieldsData(String formIdentifier) {
		return dynamicFormDao.getTrackerFieldData(formIdentifier);
	}

	@Override
	public boolean extractAndUpdateFormDetails(MultipartHttpServletRequest request, HttpServletRequest req)
			throws BlankFormException, IOException {
		MultipartFile file = documentContract.extractFile(request);
		documentContract.uploadDocument(file, req);
		DynamicFormModel dynamicFormModel = documentContract.extractModel(request, DynamicFormModel.class);

		// Update customer submitted dynamicForm details
		dynamicFormDao.createCustomerFormDetails(dynamicFormModel);
		return true;
	}

	// -------------------------------------------RFP--------------------------------------------------------
	// Get the response from the customers and also their missing responses which are not yet filled
	@Override
	public String getRFPCustomerResponseAsJson(Integer rfpId) throws JsonProcessingException {
		// Retrieve customer responses based on rfpId
		List<CustomerRFPResponseDTO> customerResponses = dynamicFormDao.getRFPCustomerResponseByNo(rfpId);
		// Retrieve missing responses based on rfpId
		List<MissingRFPResponseDTO> missingResponses = dynamicFormDao.getRFPMissingResponses(rfpId);

		// Assign customerResponses and missingResponses to combinedCustomerResponseContract
		combinedCustomerRFPResponseContract.setCustomerRFPResponseDTOs(customerResponses);
		combinedCustomerRFPResponseContract.setMissingRFPResponseDTOs(missingResponses);

		// Configure the date format
		SimpleDateFormat dateFormat = customDateFormatWrapper.getDateFormat();
		// Set the date format on the customObjectMapperWrapper
		customObjectMapperWrapper.setDateFormat(dateFormat);

		String json = "";
		// Convert the combinedCustomerRFPResponseContract to JSON string
		json = customObjectMapperWrapper.writeValueAsString(combinedCustomerRFPResponseContract);

		return json;
	}

	// Updates data and send email to the customer along with the dynamicForm's link
	@Override
	public boolean UpdateRFPAndSendMail(RFPRequestFormModel requestFormModel, String rfpData)
			throws InsertException, BlankFormException, JsonProcessingException {
		// Get the customer email for sending the email
		String customerEmail = dynamicFormDao.getRfpCustomerEmail(requestFormModel);
		// Generate a unique form identifier
		String RfpFormIdentifier = generateUUID();

		// DeSerialize the rfpData into a list of RFPFieldsDTO objects
		List<RFPFieldsDTO> rfpFields = customObjectMapperWrapper.readValue(rfpData,
				new TypeReference<List<RFPFieldsDTO>>() {
				});
		checkIfIsNull(rfpFields);
		// Create a new iteration for the request form
		int iterId = dynamicFormDao.CreateRfpIterations(requestFormModel);
		dynamicFormDao.createRfpFormDetails(rfpFields, RfpFormIdentifier, iterId);

		// Send the request email
		String to = customerEmail;
		String subject = "Action Required";
		emailContract.rfpRequestEmail(to, subject, RfpFormIdentifier);
		return true;
	}

	// Update customer submitted dynamicForm details
	@Override
	public boolean extractAndUpdateRFPFormDetails(MultipartHttpServletRequest request, HttpServletRequest req)
			throws BlankFormException, IOException, InsertException {
		MultipartFile file = documentContract.extractFile(request);
		documentContract.uploadDocument(file, req);

		RFPDynamicFormmModel dynamicFormModel = documentContract.extractModel(request, RFPDynamicFormmModel.class);

		// Update customer submitted dynamicForm details
		dynamicFormDao.createRfpCustomerFormDetails(dynamicFormModel);
		return true;
	}

	// Retrieve RFPFields data based on the formIdentifier generated when sending the dynamicForm
	@Override
	public List<RFPFieldsModel> getRFPFieldsData(String formIdentifier) {
		return dynamicFormDao.getRfpFieldData(formIdentifier);
	}

	// ----------------------------ApprovalForm---------------------------
	public boolean updateApprovalStatus(CommunicationData communicationData) {
		dynamicFormDao.updateApprovalStatus(communicationData);
		return true;
	}

}
