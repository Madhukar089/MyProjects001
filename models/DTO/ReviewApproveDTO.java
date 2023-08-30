package com.models.DTO;

public class ReviewApproveDTO {
	private int rfpId;
	private double documentVersion;
	private String documentPath;

	public int getRfpId() {
		return rfpId;
	}

	public void setRfpId(int rfpId) {
		this.rfpId = rfpId;
	}

	public double getDocumentVersion() {
		return documentVersion;
	}

	public void setDocumentVersion(double documentVersion) {
		this.documentVersion = documentVersion;
	}

	public String getDocumentPath() {
		return documentPath;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

	public ReviewApproveDTO(int rfpId, double documentVersion, String documentPath) {
		super();
		this.rfpId = rfpId;
		this.documentVersion = documentVersion;
		this.documentPath = documentPath;
	}

	public ReviewApproveDTO() {

	}

	@Override
	public String toString() {
		return "ReviewApproveDTO [rfpId=" + rfpId + ", documentVersion=" + documentVersion + ", documentPath="
				+ documentPath + "]";
	}

}
