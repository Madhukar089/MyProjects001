package com.contract;

import java.util.List;

import com.models.DTO.CustomerResponseDTO;
import com.models.DTO.MissingResponseDTO;

public interface CombinedCustomerResponseContract {

	void setCustomerResponseDTOs(List<CustomerResponseDTO> customerResponses);

	void setMissingResponseDTOs(List<MissingResponseDTO> missingResponses);

}
