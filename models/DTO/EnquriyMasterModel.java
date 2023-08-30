package com.models.DTO;

import java.sql.Date;

public class EnquriyMasterModel {
	// master
	private Integer enqrCustId;
	private Integer enqrId;
	private Date enqrDate;
	private String enqrStatus;
	private String enqrAssignedTo;

	public EnquriyMasterModel() {
	}

	public EnquriyMasterModel(Integer enqrCustId, Integer enqrId, Date enqrDate, String enqrStatus,
			String enqrAssignedTo) {
		this.enqrCustId = enqrCustId;
		this.enqrId = enqrId;
		this.enqrDate = enqrDate;
		this.enqrStatus = enqrStatus;
		this.enqrAssignedTo = enqrAssignedTo;
	}

	@Override
	public String toString() {
		return "EnquriyMasterModel{" + "enqrCustId='" + enqrCustId + '\'' + ", enqrId='" + enqrId + '\''
				+ ", enqrDate='" + enqrDate + '\'' + ", enqrStatus='" + enqrStatus + '\'' + ", enqrAssignedTo='"
				+ enqrAssignedTo + '\'' + '}';
	}

	public Integer getEnqrCustId() {
		return enqrCustId;
	}

	public void setEnqrCustId(Integer enqrCustId) {
		this.enqrCustId = enqrCustId;
	}

	public Integer getEnqrId() {
		return enqrId;
	}

	public void setEnqrId(Integer enqrId) {
		this.enqrId = enqrId;
	}

	public Date getEnqrDate() {
		return enqrDate;
	}

	public void setEnqrDate(Date enqrDate) {
		this.enqrDate = enqrDate;
	}

	public String getEnqrStatus() {
		return enqrStatus;
	}

	public void setEnqrStatus(String enqrStatus) {
		this.enqrStatus = enqrStatus;
	}

	public String getEnqrAssignedTo() {
		return enqrAssignedTo;
	}

	public void setEnqrAssignedTo(String enqrAssignedTo) {
		this.enqrAssignedTo = enqrAssignedTo;
	}

}
