package com.models.DTO;

import java.sql.Date;

public class MissingResponseDTO {
	private int enqrId;
	private int enqrIterationIndex;
	private String uuid;
	private int fieldId;
	private String fieldType;
	private String defaultValues;
	private int fieldOrder;
	private String enqrLabel;
	private Date createdDate;

	public int getEnqrId() {
		return enqrId;
	}

	public void setEnqrId(int enqrId) {
		this.enqrId = enqrId;
	}

	public int getEnqrIterationIndex() {
		return enqrIterationIndex;
	}

	public void setEnqrIterationIndex(int enqrIterationIndex) {
		this.enqrIterationIndex = enqrIterationIndex;
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

	public String getEnqrLabel() {
		return enqrLabel;
	}

	public void setEnqrLabel(String enqrLabel) {
		this.enqrLabel = enqrLabel;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public MissingResponseDTO(int enqrId, int enqrIterationIndex, String uuid, int fieldId, String fieldType,
			String defaultValues, int fieldOrder, String enqrLabel, Date createdDate) {
		super();
		this.enqrId = enqrId;
		this.enqrIterationIndex = enqrIterationIndex;
		this.uuid = uuid;
		this.fieldId = fieldId;
		this.fieldType = fieldType;
		this.defaultValues = defaultValues;
		this.fieldOrder = fieldOrder;
		this.enqrLabel = enqrLabel;
		this.createdDate = createdDate;
	}

	public MissingResponseDTO() {

	}

	@Override
	public String toString() {
		return "MissingResponseDTO [enqrId=" + enqrId + ", enqrIterationIndex=" + enqrIterationIndex + ", uuid=" + uuid
				+ ", fieldId=" + fieldId + ", fieldType=" + fieldType + ", defaultValues=" + defaultValues
				+ ", fieldOrder=" + fieldOrder + ", enqrLabel=" + enqrLabel + ", createdDate=" + createdDate + "]";
	}

}
