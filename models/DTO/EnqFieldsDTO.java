package com.models.DTO;

public class EnqFieldsDTO {
	private int enqrId;
	private int enqrFieldId;
	private String fieldType;
	private String defaultValues;
	private int enqrFieldOrder;
	private String label;
	
	public EnqFieldsDTO(int enqrId, int enqrFieldId, String fieldType, String defaultValues, int enqrFieldOrder,
			String label) {
		super();
		this.enqrId = enqrId;
		this.enqrFieldId = enqrFieldId;
		this.fieldType = fieldType;
		this.defaultValues = defaultValues;
		this.enqrFieldOrder = enqrFieldOrder;
		this.label = label;
	}
	
	public EnqFieldsDTO()
	{
		
	}
	public int getEnqrId() {
		return enqrId;
	}
	public void setEnqrId(int enqrId) {
		this.enqrId = enqrId;
	}
	public int getEnqrFieldId() {
		return enqrFieldId;
	}
	public void setEnqrFieldId(int enqrFieldId) {
		this.enqrFieldId = enqrFieldId;
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
	public int getEnqrFieldOrder() {
		return enqrFieldOrder;
	}
	public void setEnqrFieldOrder(int enqrFieldOrder) {
		this.enqrFieldOrder = enqrFieldOrder;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	@Override
	public String toString() {
		return "EnqFieldsDTO [enqrId=" + enqrId + ", enqrFieldId=" + enqrFieldId + ", fieldType=" + fieldType
				+ ", defaultValues=" + defaultValues + ", enqrFieldOrder=" + enqrFieldOrder + ", label=" + label + "]";
	}

	
	
}
