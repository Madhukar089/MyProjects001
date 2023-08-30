package com.models.DTO;

import java.util.List;

public class EnquiryDTO {

	private int enqrId;
	private String enqrCreatedBy;
	private int enqrCustId;
	private String enqrSubject;
	private String enqrDesc;
	private String enqrStatus;
	private String enqrAssignedTo;
	private String custEmail;
	private String custType;
	private String custWebsite;
	private List<String> documentPath;

	public List<String> getDocumnets() {
		return documentPath;
	}

	public void setDocuments(List<String> documentPath) {
		this.documentPath = documentPath;
	}

	public int getEnqrId() {
		return enqrId;
	}

	public void setEnqrId(int enqrId) {
		this.enqrId = enqrId;
	}

	public String getEnqrCreatedBy() {
		return enqrCreatedBy;
	}

	public void setEnqrCreatedBy(String enqrCreatedBy) {
		this.enqrCreatedBy = enqrCreatedBy;
	}

	public int getEnqrCustId() {
		return enqrCustId;
	}

	public void setEnqrCustId(int enqrCustId) {
		this.enqrCustId = enqrCustId;
	}

	public String getEnqrSubject() {
		return enqrSubject;
	}

	public void setEnqrSubject(String enqrSubject) {
		this.enqrSubject = enqrSubject;
	}

	public String getEnqrDesc() {
		return enqrDesc;
	}

	public void setEnqrDesc(String enqrDesc) {
		this.enqrDesc = enqrDesc;
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

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getCustWebsite() {
		return custWebsite;
	}

	public void setCustWebsite(String custWebsite) {
		this.custWebsite = custWebsite;
	}

	@Override
	public String toString() {
		return "EnquiryDTO [enqrId=" + enqrId + ", enqrCreatedBy=" + enqrCreatedBy + ", enqrCustId=" + enqrCustId
				+ ", enqrSubject=" + enqrSubject + ", enqrDesc=" + enqrDesc + ", enqrStatus=" + enqrStatus
				+ ", enqrAssignedTo=" + enqrAssignedTo + ", custEmail=" + custEmail + ", custType=" + custType
				+ ", custWebsite=" + custWebsite + "]";
	}

}