package com.models.DTO;

public class UpdateDTO {
	private int enquiryId;
	private String status;

	public int getEnquiryId() {
		return enquiryId;
	}

	public void setEnquiryId(int enquiryId) {
		this.enquiryId = enquiryId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UpdateDTO(int enquiryId, String status) {
		super();
		this.enquiryId = enquiryId;
		this.status = status;
	}

	public UpdateDTO() {
	}

	@Override
	public String toString() {
		return "UpdateDTO [enquiryId=" + enquiryId + ", status=" + status + "]";
	}

}
