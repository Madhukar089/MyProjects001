package com.models.DTO;

import java.sql.Date;

public class CustomerRFPResponseDTO {
	private Date createdDate;
	private int rfpr_id;
	private int rfpr_iteration_index;
	private String rfpr_uuid;
	private int rfpr_fieldid;
	private String label;
	private String rfpr_value;

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getRfpr_id() {
		return rfpr_id;
	}

	public void setRfpr_id(int rfpr_id) {
		this.rfpr_id = rfpr_id;
	}

	public int getRfpr_iteration_index() {
		return rfpr_iteration_index;
	}

	public void setRfpr_iteration_index(int rfpr_iteration_index) {
		this.rfpr_iteration_index = rfpr_iteration_index;
	}

	public String getRfpr_uuid() {
		return rfpr_uuid;
	}

	public void setRfpr_uuid(String rfpr_uuid) {
		this.rfpr_uuid = rfpr_uuid;
	}

	public int getRfpr_fieldid() {
		return rfpr_fieldid;
	}

	public void setRfpr_fieldid(int rfpr_fieldid) {
		this.rfpr_fieldid = rfpr_fieldid;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getRfpr_value() {
		return rfpr_value;
	}

	public void setRfpr_value(String rfpr_value) {
		this.rfpr_value = rfpr_value;
	}

	public CustomerRFPResponseDTO(Date createdDate, int rfpr_id, int rfpr_iteration_index, String rfpr_uuid,
			int rfpr_fieldid, String label, String rfpr_value) {
		super();
		this.createdDate = createdDate;
		this.rfpr_id = rfpr_id;
		this.rfpr_iteration_index = rfpr_iteration_index;
		this.rfpr_uuid = rfpr_uuid;
		this.rfpr_fieldid = rfpr_fieldid;
		this.label = label;
		this.rfpr_value = rfpr_value;
	}

	public CustomerRFPResponseDTO() {

	}

	@Override
	public String toString() {
		return "CustomerRFPResponseDTO [createdDate=" + createdDate + ", rfpr_id=" + rfpr_id + ", rfpr_iteration_index="
				+ rfpr_iteration_index + ", rfpr_uuid=" + rfpr_uuid + ", rfpr_fieldid=" + rfpr_fieldid + ", label="
				+ label + ", rfpr_value=" + rfpr_value + "]";
	}

}
