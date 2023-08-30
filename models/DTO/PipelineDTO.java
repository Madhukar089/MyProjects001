package com.models.DTO;

public class PipelineDTO {

	private int enqrId;
	private String enqrStatus;
	private int rfprId;
	private String rfprStatus;

	public PipelineDTO() {

	}

	public PipelineDTO(int enqrId, String enqrStatus, int rfprId, String rfprStatus) {
		super();
		this.enqrId = enqrId;
		this.enqrStatus = enqrStatus;
		this.rfprId = rfprId;
		this.rfprStatus = rfprStatus;
	}

	public int getEnqrId() {
		return enqrId;
	}

	public void setEnqrId(int enqrId) {
		this.enqrId = enqrId;
	}

	public String getEnqrStatus() {
		return enqrStatus;
	}

	public void setEnqrStatus(String enqrStatus) {
		this.enqrStatus = enqrStatus;
	}

	public int getRfprId() {
		return rfprId;
	}

	public void setRfprId(int rfprId) {
		this.rfprId = rfprId;
	}

	public String getRfprStatus() {
		return rfprStatus;
	}

	public void setRfprStatus(String rfprStatus) {
		this.rfprStatus = rfprStatus;
	}
	
	

}
