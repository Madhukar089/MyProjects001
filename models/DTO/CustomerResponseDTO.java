package com.models.DTO;

import java.sql.Date;

public class CustomerResponseDTO {
	private Date createdDate;
	private int enqr_id;
	private int enqr_iteration_index;
	private String enqr_uuid;
	private int enqr_fieldid;
	private String label;
	private String enqr_value;

	public CustomerResponseDTO(Date createdDate, int enqr_id, int enqr_iteration_index, String enqr_uuid,
			int enqr_fieldid, String label, String enqr_value) {
		super();
		this.createdDate = createdDate;
		this.enqr_id = enqr_id;
		this.enqr_iteration_index = enqr_iteration_index;
		this.enqr_uuid = enqr_uuid;
		this.enqr_fieldid = enqr_fieldid;
		this.label = label;
		this.enqr_value = enqr_value;
	}

	public CustomerResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getEnqr_id() {
		return enqr_id;
	}

	public void setEnqr_id(int enqr_id) {
		this.enqr_id = enqr_id;
	}

	public int getEnqr_iteration_index() {
		return enqr_iteration_index;
	}

	public void setEnqr_iteration_index(int enqr_iteration_index) {
		this.enqr_iteration_index = enqr_iteration_index;
	}

	public String getEnqr_uuid() {
		return enqr_uuid;
	}

	public void setEnqr_uuid(String enqr_uuid) {
		this.enqr_uuid = enqr_uuid;
	}

	public int getEnqr_fieldid() {
		return enqr_fieldid;
	}

	public void setEnqr_fieldid(int enqr_fieldid) {
		this.enqr_fieldid = enqr_fieldid;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getEnqr_value() {
		return enqr_value;
	}

	public void setEnqr_value(String enqr_value) {
		this.enqr_value = enqr_value;
	}

	@Override
	public String toString() {
		return "CustomerResponseDTO [createdDate=" + createdDate + ", enqr_id=" + enqr_id + ", enqr_iteration_index="
				+ enqr_iteration_index + ", enqr_uuid=" + enqr_uuid + ", enqr_fieldid=" + enqr_fieldid + ", label="
				+ label + ", enqr_value=" + enqr_value + "]";
	}

}
