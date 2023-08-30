package com.models.Input;

import java.util.List;

import com.contract.CombinedCustomerResponseContract;
import com.models.DTO.CustomerResponseDTO;
import com.models.DTO.MissingResponseDTO;

public class CombinedCustomerResponse implements CombinedCustomerResponseContract {
	private List<CustomerResponseDTO> customerResponseDTOs;
	private List<MissingResponseDTO> missingResponseDTOs;

	public List<CustomerResponseDTO> getCustomerResponseDTOs() {
		return customerResponseDTOs;
	}

	public void setCustomerResponseDTOs(List<CustomerResponseDTO> customerResponseDTOs) {
		this.customerResponseDTOs = customerResponseDTOs;
	}

	public List<MissingResponseDTO> getMissingResponseDTOs() {
		return missingResponseDTOs;
	}

	public void setMissingResponseDTOs(List<MissingResponseDTO> missingResponseDTOs) {
		this.missingResponseDTOs = missingResponseDTOs;
	}

	public CombinedCustomerResponse(List<CustomerResponseDTO> customerResponseDTOs,
			List<MissingResponseDTO> missingResponseDTOs) {
		super();
		this.customerResponseDTOs = customerResponseDTOs;
		this.missingResponseDTOs = missingResponseDTOs;
	}

	public CombinedCustomerResponse() {

	}

	@Override
	public String toString() {
		return "CombinedCustomerResponse [customerResponseDTOs=" + customerResponseDTOs + ", missingResponseDTOs="
				+ missingResponseDTOs + "]";
	}
}
