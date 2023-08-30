package com.models.DTO;

public class RejectionMailDTO {

	private String CustomerEmail;
	private int customerId;

	public RejectionMailDTO(String customerEmail, int customerId) {
		super();
		CustomerEmail = customerEmail;
		this.customerId = customerId;
	}

	public RejectionMailDTO() {

	}

	public String getCustomerEmail() {
		return CustomerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		CustomerEmail = customerEmail;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "RejectionMailDTO [CustomerEmail=" + CustomerEmail + ", customerId=" + customerId + "]";
	}

}
