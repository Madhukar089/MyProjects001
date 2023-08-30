package com.models.DTO;

import java.sql.Timestamp;

public class RFPRDocumentDTO {
	private int rfprId;
	private int rfpdId;
	private int rfpdDocIndex;
	private String rfpdType;
	private String rfpdDocCategory;
	private Timestamp rfpdPreparedDate;
	private String rfpdSharedStatus;
	private String rfpdDocumentPath;
	private float rfpdPDFVersion;
	private float rfpdVersion;
	private String rpfdReviewedBy;
	private String rfpdDescription;
	private String rpdfStatus;

	// Constructors

	public RFPRDocumentDTO() {
	}

	@Override
	public String toString() {
		return "RFPRDocumentDTO [rfprId=" + rfprId + ", rfpdId=" + rfpdId + ", rfpdDocIndex=" + rfpdDocIndex
				+ ", rfpdType=" + rfpdType + ", rfpdDocCategory=" + rfpdDocCategory + ", rfpdPreparedDate="
				+ rfpdPreparedDate + ", rfpdSharedStatus=" + rfpdSharedStatus + ", rfpdDocumentPath=" + rfpdDocumentPath
				+ ", rfpdPDFVersion=" + rfpdPDFVersion + ", rfpdVersion=" + rfpdVersion + ", rpfdReviewedBy="
				+ rpfdReviewedBy + ", rfpdDescription=" + rfpdDescription + ", rpdfStatus=" + rpdfStatus + "]";
	}

	public RFPRDocumentDTO(int rfprId, int rfpdId, int rfpdDocIndex, String rfpdType, String rfpdDocCategory,
			Timestamp rfpdPreparedDate, String rfpdSharedStatus, String rfpdDocumentPath, float rfpdPDFVersion,
			float rfpdVersion, String rpfdReviewedBy, String rfpdDescription, String rpdfStatus) {
		super();
		this.rfprId = rfprId;
		this.rfpdId = rfpdId;
		this.rfpdDocIndex = rfpdDocIndex;
		this.rfpdType = rfpdType;
		this.rfpdDocCategory = rfpdDocCategory;
		this.rfpdPreparedDate = rfpdPreparedDate;
		this.rfpdSharedStatus = rfpdSharedStatus;
		this.rfpdDocumentPath = rfpdDocumentPath;
		this.rfpdPDFVersion = rfpdPDFVersion;
		this.rfpdVersion = rfpdVersion;
		this.rpfdReviewedBy = rpfdReviewedBy;
		this.rfpdDescription = rfpdDescription;
		this.rpdfStatus = rpdfStatus;
	}

	public int getRfprId() {
		return rfprId;
	}

	public void setRfprId(int rfprId) {
		this.rfprId = rfprId;
	}

	public int getRfpdDocIndex() {
		return rfpdDocIndex;
	}

	public void setRfpdDocIndex(int rfpdDocIndex) {
		this.rfpdDocIndex = rfpdDocIndex;
	}

	public String getRfpdType() {
		return rfpdType;
	}

	public void setRfpdType(String rfpdType) {
		this.rfpdType = rfpdType;
	}

	public String getRfpdDocCategory() {
		return rfpdDocCategory;
	}

	public void setRfpdDocCategory(String rfpdDocCategory) {
		this.rfpdDocCategory = rfpdDocCategory;
	}

	public Timestamp getRfpdPreparedDate() {
		return rfpdPreparedDate;
	}

	public void setRfpdPreparedDate(Timestamp rfpdPreparedDate) {
		this.rfpdPreparedDate = rfpdPreparedDate;
	}

	public String getRfpdSharedStatus() {
		return rfpdSharedStatus;
	}

	public void setRfpdSharedStatus(String rfpdSharedStatus) {
		this.rfpdSharedStatus = rfpdSharedStatus;
	}

	public String getRfpdDocumentPath() {
		return rfpdDocumentPath;
	}

	public void setRfpdDocumentPath(String rfpdDocumentPath) {
		this.rfpdDocumentPath = rfpdDocumentPath;
	}

	public float getRfpdPDFVersion() {
		return rfpdPDFVersion;
	}

	public void setRfpdPDFVersion(float rfpdPDFVersion) {
		this.rfpdPDFVersion = rfpdPDFVersion;
	}

	public float getRfpdVersion() {
		return rfpdVersion;
	}

	public void setRfpdVersion(float rfpdVersion) {
		this.rfpdVersion = rfpdVersion;
	}

	public String getRpfdReviewedBy() {
		return rpfdReviewedBy;
	}

	public void setRpfdReviewedBy(String rpfdReviewedBy) {
		this.rpfdReviewedBy = rpfdReviewedBy;
	}

	public String getRfpdDescription() {
		return rfpdDescription;
	}

	public void setRfpdDescription(String rfpdDescription) {
		this.rfpdDescription = rfpdDescription;
	}

	public String getRpdfStatus() {
		return rpdfStatus;
	}

	public void setRpdfStatus(String rpdfStatus) {
		this.rpdfStatus = rpdfStatus;
	}

	public int getRfpdId() {
		return rfpdId;
	}

	public void setRfpdId(int rfpdId) {
		this.rfpdId = rfpdId;
	}

}
