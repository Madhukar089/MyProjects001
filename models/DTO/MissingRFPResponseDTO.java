package com.models.DTO;

import java.sql.Date;

public class MissingRFPResponseDTO {
	private int rfprId;
	private int rfprIterationIndex;
	private String uuid;
	private int fieldId;
	private String fieldType;
	private String defaultValues;
	private int fieldOrder;
	private String rfprLabel;
	private Date createdDate;

	public int getRfprId() {
		return rfprId;
	}

	public void setRfprId(int rfprId) {
		this.rfprId = rfprId;
	}

	public int getRfprIterationIndex() {
		return rfprIterationIndex;
	}

	public void setRfprIterationIndex(int rfprIterationIndex) {
		this.rfprIterationIndex = rfprIterationIndex;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getFieldId() {
		return fieldId;
	}

	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getDefaultValues() {
		return defaultValues;
	}

	public void setDefaultValues(String defaultValues) {
		this.defaultValues = defaultValues;
	}

	public int getFieldOrder() {
		return fieldOrder;
	}

	public void setFieldOrder(int fieldOrder) {
		this.fieldOrder = fieldOrder;
	}

	public String getRfprLabel() {
		return rfprLabel;
	}

	public void setRfprLabel(String rfprLabel) {
		this.rfprLabel = rfprLabel;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public MissingRFPResponseDTO(int rfprId, int rfprIterationIndex, String uuid, int fieldId, String fieldType,
			String defaultValues, int fieldOrder, String rfprLabel, Date createdDate) {
		super();
		this.rfprId = rfprId;
		this.rfprIterationIndex = rfprIterationIndex;
		this.uuid = uuid;
		this.fieldId = fieldId;
		this.fieldType = fieldType;
		this.defaultValues = defaultValues;
		this.fieldOrder = fieldOrder;
		this.rfprLabel = rfprLabel;
		this.createdDate = createdDate;
	}

	public MissingRFPResponseDTO() {

	}

	@Override
	public String toString() {
		return "MissingRFPResponseDTO [rfprId=" + rfprId + ", rfprIterationIndex=" + rfprIterationIndex + ", uuid="
				+ uuid + ", fieldId=" + fieldId + ", fieldType=" + fieldType + ", defaultValues=" + defaultValues
				+ ", fieldOrder=" + fieldOrder + ", rfprLabel=" + rfprLabel + ", createdDate=" + createdDate + "]";
	}

}
