package com.models.DTO;

public class RFPFieldsDTO {
	private int rfprId;
	private int rfprFieldId;
	private String fieldType;
	private String defaultValues;
	private int rfprFieldOrder;
	private String label;

	public RFPFieldsDTO() {

	}

	public RFPFieldsDTO(int rfprId, int rfprFieldId, String fieldType, String defaultValues, int rfprFieldOrder,
			String label) {
		super();
		this.rfprId = rfprId;
		this.rfprFieldId = rfprFieldId;
		this.fieldType = fieldType;
		this.defaultValues = defaultValues;
		this.rfprFieldOrder = rfprFieldOrder;
		this.label = label;
	}

	public int getRfprId() {
		return rfprId;
	}

	public void setRfprId(int rfprId) {
		this.rfprId = rfprId;
	}

	public int getRfprFieldId() {
		return rfprFieldId;
	}

	public void setRfprFieldId(int rfprFieldId) {
		this.rfprFieldId = rfprFieldId;
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

	public int getRfprFieldOrder() {
		return rfprFieldOrder;
	}

	public void setRfprFieldOrder(int rfprFieldOrder) {
		this.rfprFieldOrder = rfprFieldOrder;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "RfpFieldsDTO [rfprId=" + rfprId + ", rfprFieldId=" + rfprFieldId + ", fieldType=" + fieldType
				+ ", defaultValues=" + defaultValues + ", rfprFieldOrder=" + rfprFieldOrder + ", label=" + label + "]";
	}

	

}
