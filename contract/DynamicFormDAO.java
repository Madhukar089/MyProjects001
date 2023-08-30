package com.contract;

import java.util.List;

import com.customExceptions.BlankFormException;
import com.customExceptions.InsertException;
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

public interface DynamicFormDAO {

	int createEnquiryIterations(RequestFormModel requestFormModel) throws InsertException;

	String getCustomerEmail(RequestFormModel requestFormModel);

	boolean createtrackerFormDetails(List<EnqFieldsDTO> enqFields, String formIdentifier, int iterId)
			throws BlankFormException;

	List<CustomerResponseDTO> getCustomerResponseByNo(Integer eno);

	List<MissingResponseDTO> getMissingResponses(Integer enquiryId);

	List<EnqFieldsModel> getTrackerFieldData(String formIdentifier);

	void createCustomerFormDetails(DynamicFormModel dynamicFormModel) throws BlankFormException;

	// -----------------------------RFP------------------------------------

	int CreateRfpIterations(RFPRequestFormModel requestFormModel) throws InsertException;

	String getRfpCustomerEmail(RFPRequestFormModel requestFormModel);

	void createRfpFormDetails(List<RFPFieldsDTO> rfpFields, String uuid, int iterId) throws BlankFormException;

	List<RFPFieldsModel> getRfpFieldData(String formIdentifier);

	void createRfpCustomerFormDetails(RFPDynamicFormmModel dynamicFormModel) throws InsertException;

	List<CustomerRFPResponseDTO> getRFPCustomerResponseByNo(Integer eno);

	List<MissingRFPResponseDTO> getRFPMissingResponses(Integer eno);

	// -----------------------------FormForCommunication-------------------------------

	void updateApprovalStatus(CommunicationData communicationData);
}
