package com.models.Input;

import org.springframework.web.multipart.MultipartFile;

public class Customer {
	private int custId;
	private String custType;
	private String custName;
	private String custCompany;
	private String custAddress;
	private String custLocation;
	private String custMobile;
	private String custEmail;
	private String custWebsite;
	private String desc;
	private String sub;
	private MultipartFile formFile;

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custType=" + custType + ", custName=" + custName + ", custCompany="
				+ custCompany + ", custAddress=" + custAddress + ", custLocation=" + custLocation + ", custMobile="
				+ custMobile + ", custEmail=" + custEmail + ", custWebsite=" + custWebsite + ", desc=" + desc + ", sub="
				+ sub + ", formFile=" + formFile + "]";
	}

	public Customer(int custId, String custType, String custName, String custCompany, String custAddress,
			String custLocation, String custMobile, String custEmail, String custWebsite, String desc, String sub,
			MultipartFile formFile) {
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
		this.desc = desc;
		this.sub = sub;
		this.formFile = formFile;
	}

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

	public String getCustMobile() {
		return custMobile;
	}

	public void setCustMobile(String custMobile) {
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public MultipartFile getFormFile() {
		return formFile;
	}

	public void setFormFile(MultipartFile formFile) {
		this.formFile = formFile;
	}

	public Customer() {
	}

}
