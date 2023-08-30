package com.models.DTO;

public class RFPDocumentDTO {

	private int rfprId;
	private String rfpdType;
	private String rfprDocCategory;
	private String rfpdDocumentPath;
	private String rfpdPdfVersion;
	private String rfpdVersion;
	private String rfpdReviewedBy;
	private String rfpdDesc;
	private String rpdfStatus;

	public RFPDocumentDTO(int rfprId, String rfpdType, String rfprDocCategory, String rfpdDocumentPath,
			String rfpdPdfVersion, String rfpdVersion, String rfpdReviewedBy, String rfpdDesc, String rpdfStatus) {
		super();
		this.rfprId = rfprId;
		this.rfpdType = rfpdType;
		this.rfprDocCategory = rfprDocCategory;
		this.rfpdDocumentPath = rfpdDocumentPath;
		this.rfpdPdfVersion = rfpdPdfVersion;
		this.rfpdVersion = rfpdVersion;
		this.rfpdReviewedBy = rfpdReviewedBy;
		this.rfpdDesc = rfpdDesc;
		this.rpdfStatus = rpdfStatus;
	}

	public RFPDocumentDTO() {

	}

	public int getRfprId() {
		return rfprId;
	}

	public void setRfprId(int rfprId) {
		this.rfprId = rfprId;
	}

	public String getRfpdType() {
		return rfpdType;
	}

	public void setRfpdType(String rfpdType) {
		this.rfpdType = rfpdType;
	}

	public String getRfprDocCategory() {
		return rfprDocCategory;
	}

	public void setRfprDocCategory(String rfprDocCategory) {
		this.rfprDocCategory = rfprDocCategory;
	}

	public String getRfpdDocumentPath() {
		return rfpdDocumentPath;
	}

	public void setRfpdDocumentPath(String rfpdDocumentPath) {
		this.rfpdDocumentPath = rfpdDocumentPath;
	}

	public String getRfpdPdfVersion() {
		return rfpdPdfVersion;
	}

	public void setRfpdPdfVersion(String rfpdPdfVersion) {
		this.rfpdPdfVersion = rfpdPdfVersion;
	}

	public String getRfpdVersion() {
		return rfpdVersion;
	}

	public void setRfpdVersion(String rfpdVersion) {
		this.rfpdVersion = rfpdVersion;
	}

	public String getRfpdReviewedBy() {
		return rfpdReviewedBy;
	}

	public void setRfpdReviewedBy(String rfpdReviewedBy) {
		this.rfpdReviewedBy = rfpdReviewedBy;
	}

	public String getRfpdDesc() {
		return rfpdDesc;
	}

	public void setRfpdDesc(String rfpdDesc) {
		this.rfpdDesc = rfpdDesc;
	}

	public String getRpdfStatus() {
		return rpdfStatus;
	}

	public void setRpdfStatus(String rpdfStatus) {
		this.rpdfStatus = rpdfStatus;
	}

	@Override
	public String toString() {
		return "RFPDocumentDTO [rfprId=" + rfprId + ", rfpdType=" + rfpdType + ", rfprDocCategory=" + rfprDocCategory
				+ ", rfpdDocumentPath=" + rfpdDocumentPath + ", rfpdPdfVersion=" + rfpdPdfVersion + ", rfpdVersion="
				+ rfpdVersion + ", rfpdReviewedBy=" + rfpdReviewedBy + ", rfpdDesc=" + rfpdDesc + ", rpdfStatus="
				+ rpdfStatus + "]";
	}

}