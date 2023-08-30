package com.contract;

import java.util.List;

import com.models.DTO.CustomerRFPResponseDTO;
import com.models.DTO.MissingRFPResponseDTO;

public interface CombinedCustomerRFPResponseContract {

	void setMissingRFPResponseDTOs(List<MissingRFPResponseDTO> missingResponses);

	void setCustomerRFPResponseDTOs(List<CustomerRFPResponseDTO> customerResponses);

}
