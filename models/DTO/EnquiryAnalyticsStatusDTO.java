package com.models.DTO;

public class EnquiryAnalyticsStatusDTO {
	// DTO
	Integer count;
	String enqr_status;

	public EnquiryAnalyticsStatusDTO() {
	}

	public EnquiryAnalyticsStatusDTO(Integer count, String enqr_status) {
		super();
		this.count = count;
		this.enqr_status = enqr_status;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getEnqr_status() {
		return enqr_status;
	}

	public void setEnqr_status(String enqr_status) {
		this.enqr_status = enqr_status;
	}

	@Override
	public String toString() {
		return "EnquiryAnalyticsStatusDTO [count=" + count + ", enqr_status=" + enqr_status + "]";
	}

}
