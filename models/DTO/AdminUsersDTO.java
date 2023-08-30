package com.models.DTO;

import java.math.BigInteger;
import java.sql.Timestamp;

public class AdminUsersDTO {

	private String userId;
	private String fullName;
	private String offEmail;
	private BigInteger mobile;
	private Timestamp luDateTime;
	private String department;
	private String status;

	public AdminUsersDTO() {

	}

	public AdminUsersDTO(String userId, String fullName, String offEmail, BigInteger mobile, Timestamp timestamp,
			String department, String status) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.offEmail = offEmail;
		this.mobile = mobile;
		this.luDateTime = timestamp;
		this.department = department;
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getOffEmail() {
		return offEmail;
	}

	public void setOffEmail(String offEmail) {
		this.offEmail = offEmail;
	}

	public BigInteger getMobile() {
		return mobile;
	}

	public void setMobile(BigInteger mobile) {
		this.mobile = mobile;
	}

	public Timestamp getLuDateTime() {
		return luDateTime;
	}

	public void setLuDateTime(Timestamp luDateTime) {
		this.luDateTime = luDateTime;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
