package com.models.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tracker_customers")
public class TrackerCustomer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cust_id")
	private int custId;

	@Column(name = "cust_type")
	private String custType;

	@Column(name = "cust_name")
	private String custName;

	@Column(name = "cust_company")
	private String custCompany;

	@Column(name = "cust_address")
	private String custAddress;

	@Column(name = "cust_location")
	private String custLocation;

	@Column(name = "cust_mobile")
	private long custMobile;

	@Column(name = "cust_email")
	private String custEmail;

	@Column(name = "cust_website")
	private String custWebsite;

	// Default constructor
	public TrackerCustomer() {
	}

	// Getters and setters
	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustCompany() {
		return custCompany;
	}

	public void setCustCompany(String custCompany) {
		this.custCompany = custCompany;
	}

	public String getCustAddress() {
		return custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	public String getCustLocation() {
		return custLocation;
	}

	public void setCustLocation(String custLocation) {
		this.custLocation = custLocation;
	}

	public long getCustMobile() {
		return custMobile;
	}

	public void setCustMobile(long custMobile) {
		this.custMobile = custMobile;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getCustWebsite() {
		return custWebsite;
	}

	public void setCustWebsite(String custWebsite) {
		this.custWebsite = custWebsite;
	}

	public TrackerCustomer(int custId, String custType, String custName, String custCompany, String custAddress,
			String custLocation, long custMobile, String custEmail, String custWebsite) {
		super();
		this.custId = custId;
		this.custType = custType;
		this.custName = custName;
		this.custCompany = custCompany;
		this.custAddress = custAddress;
		this.custLocation = custLocation;
		this.custMobile = custMobile;
		this.custEmail = custEmail;
		this.custWebsite = custWebsite;
	}

	@Override
	public String toString() {
		return "TrackerCustomer [custId=" + custId + ", custType=" + custType + ", custName=" + custName
				+ ", custCompany=" + custCompany + ", custAddress=" + custAddress + ", custLocation=" + custLocation
				+ ", custMobile=" + custMobile + ", custEmail=" + custEmail + ", custWebsite=" + custWebsite + "]";
	}

}
