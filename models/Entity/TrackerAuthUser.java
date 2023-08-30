package com.models.Entity;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tracker_auth_users")
public class TrackerAuthUser {
	@Id
	@Column(name = "ausr_id")
	private String userId;

	@Column(name = "ausr_offemail")
	private String officialEmail;

	@Column(name = "ausr_pwd")
	private String password;

	@Column(name = "ausr_lldatetime")
	private LocalDateTime lastLoginDateTime;

	@Column(name = "ausr_status")
	private String status;

	@Column(name = "ausr_mobile")
	private Long mobile;

	@Column(name = "fullname")
	private String fullName;

	@Column(name = "ausr_profile")
	private byte[] profile;

	@Column(name = "department")
	private String department;

	// Default constructor
	public TrackerAuthUser() {
	}

	// Getters and setters

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOfficialEmail() {
		return officialEmail;
	}

	public void setOfficialEmail(String officialEmail) {
		this.officialEmail = officialEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getLastLoginDateTime() {
		return lastLoginDateTime;
	}

	public void setLastLoginDateTime(LocalDateTime lastLoginDateTime) {
		this.lastLoginDateTime = lastLoginDateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public byte[] getProfile() {
		return profile;
	}

	public void setProfile(byte[] profile) {
		this.profile = profile;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}
