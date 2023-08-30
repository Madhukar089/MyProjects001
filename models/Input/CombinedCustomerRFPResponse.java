package com.models.Input;

import java.util.List;

import com.contract.CombinedCustomerRFPResponseContract;
import com.models.DTO.CustomerRFPResponseDTO;
import com.models.DTO.MissingRFPResponseDTO;

public class CombinedCustomerRFPResponse implements CombinedCustomerRFPResponseContract {
	private List<CustomerRFPResponseDTO> customerRFPResponseDTOs;
	private List<MissingRFPResponseDTO> missingRFPResponseDTOs;

	public List<CustomerRFPResponseDTO> getCustomerRFPResponseDTOs() {
		return customerRFPResponseDTOs;
	}

	public void setCustomerRFPResponseDTOs(List<CustomerRFPResponseDTO> customerRFPResponseDTOs) {
		this.customerRFPResponseDTOs = customerRFPResponseDTOs;
	}

	public List<MissingRFPResponseDTO> getMissingRFPResponseDTOs() {
		return missingRFPResponseDTOs;
	}

	public void setMissingRFPResponseDTOs(List<MissingRFPResponseDTO> missingRFPResponseDTOs) {
		this.missingRFPResponseDTOs = missingRFPResponseDTOs;
	}

	public CombinedCustomerRFPResponse(List<CustomerRFPResponseDTO> customerRFPResponseDTOs,
			List<MissingRFPResponseDTO> missingRFPResponseDTOs) {
		super();
		this.customerRFPResponseDTOs = customerRFPResponseDTOs;
		this.missingRFPResponseDTOs = missingRFPResponseDTOs;
	}

	public CombinedCustomerRFPResponse() {

	}

	@Override
	public String toString() {
		return "ResponseWrapperRFPUtilityClass [customerRFPResponseDTOs=" + customerRFPResponseDTOs
				+ ", missingRFPResponseDTOs=" + missingRFPResponseDTOs + "]";
	}

}
