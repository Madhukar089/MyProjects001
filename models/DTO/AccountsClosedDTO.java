package com.models.DTO;

public class AccountsClosedDTO {

	private int custId;
	private String customerName;
	private int enqrId;
	private int rfprId;
	private String accountClosedBy;

	public AccountsClosedDTO(int custId, String customerName, int enqrId, int rfprId, String accountClosedBy) {
		super();
		this.custId = custId;
		this.customerName = customerName;
		this.enqrId = enqrId;
		this.rfprId = rfprId;
		this.accountClosedBy = accountClosedBy;
	}

	public AccountsClosedDTO() {

	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getEnqrId() {
		return enqrId;
	}

	public void setEnqrId(int enqrId) {
		this.enqrId = enqrId;
	}

	public int getRfprId() {
		return rfprId;
	}

	public void setRfprId(int rfprId) {
		this.rfprId = rfprId;
	}

	public String getAccountClosedBy() {
		return accountClosedBy;
	}

	public void setAccountClosedBy(String accountClosedBy) {
		this.accountClosedBy = accountClosedBy;
	}

}
