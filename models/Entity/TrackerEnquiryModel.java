package com.models.Entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tracker_enquiries")
public class TrackerEnquiryModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enqr_id")
	private int enqrId;

	@Column(name = "enqr_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp enqrDate;

	@Column(name = "enqr_createdby")
	private String enqrCreatedBy;

	@Column(name = "enqr_cust_id")
	private int enqrCustId;

	@Column(name = "enqr_subject")
	private String enqrSubject;

	@Column(name = "enqr_desc")
	private String enqrDesc;

	@Column(name = "enqr_status", columnDefinition = "CHAR(20) DEFAULT 'Open'")
	private String enqrStatus;

	@Column(name = "enqr_assigned_to", columnDefinition = "varchar(60) DEFAULT 'ramu'")
	private String enqrAssignedTo;

	@Column(name = "enqr_ludate")
	private Timestamp enqrLudate;

	@Column(name = "enqr_luser", columnDefinition = "varchar(60) DEFAULT 'ramu'")
	private String enqrLuser;

	@ManyToOne
	@JoinColumn(name = "enqr_cust_id", referencedColumnName = "cust_id", insertable = false, updatable = false)
	private TrackerCustomer customer;

	@ManyToOne
	@JoinColumn(name = "enqr_luser", referencedColumnName = "ausr_id", insertable = false, updatable = false)
	private TrackerAuthUser luser;

	@ManyToOne
	@JoinColumn(name = "enqr_assigned_to", referencedColumnName = "ausr_id", insertable = false, updatable = false)
	private TrackerAuthUser assignedTo;

	public TrackerEnquiryModel() {
	}

	public TrackerEnquiryModel(int enqrId, Timestamp enqrDate, String enqrCreatedBy, int enqrCustId, String enqrSubject,
			String enqrDesc, String enqrStatus, String enqrAssignedTo, Timestamp enqrLudate, String enqrLuser,
			TrackerCustomer customer, TrackerAuthUser luser, TrackerAuthUser assignedTo) {
		super();
		this.enqrId = enqrId;
		this.enqrDate = enqrDate;
		this.enqrCreatedBy = enqrCreatedBy;
		this.enqrCustId = enqrCustId;
		this.enqrSubject = enqrSubject;
		this.enqrDesc = enqrDesc;
		this.enqrStatus = enqrStatus;
		this.enqrAssignedTo = enqrAssignedTo;
		this.enqrLudate = enqrLudate;
		this.enqrLuser = enqrLuser;
		this.customer = customer;
		this.luser = luser;
		this.assignedTo = assignedTo;
	}

	@Override
	public String toString() {
		return "TrackerEnquiry [enqrId=" + enqrId + ", enqrDate=" + enqrDate + ", enqrCreatedBy=" + enqrCreatedBy
				+ ", enqrCustId=" + enqrCustId + ", enqrSubject=" + enqrSubject + ", enqrDesc=" + enqrDesc
				+ ", enqrStatus=" + enqrStatus + ", enqrAssignedTo=" + enqrAssignedTo + ", enqrLudate=" + enqrLudate
				+ ", enqrLuser=" + enqrLuser + ", customer=" + customer + ", luser=" + luser + ", assignedTo="
				+ assignedTo + "]";
	}

	public int getEnqrId() {
		return enqrId;
	}

	public void setEnqrId(int enqrId) {
		this.enqrId = enqrId;
	}

	public Timestamp getEnqrDate() {
		return enqrDate;
	}

	public void setEnqrDate(Timestamp enqrDate) {
		this.enqrDate = enqrDate;
	}

	public String getEnqrCreatedBy() {
		return enqrCreatedBy;
	}

	public void setEnqrCreatedBy(String enqrCreatedBy) {
		this.enqrCreatedBy = enqrCreatedBy;
	}

	public int getEnqrCustId() {
		return enqrCustId;
	}

	public void setEnqrCustId(int enqrCustId) {
		this.enqrCustId = enqrCustId;
	}

	public String getEnqrSubject() {
		return enqrSubject;
	}

	public void setEnqrSubject(String enqrSubject) {
		this.enqrSubject = enqrSubject;
	}

	public String getEnqrDesc() {
		return enqrDesc;
	}

	public void setEnqrDesc(String enqrDesc) {
		this.enqrDesc = enqrDesc;
	}

	public String getEnqrStatus() {
		return enqrStatus;
	}

	public void setEnqrStatus(String enqrStatus) {
		this.enqrStatus = enqrStatus;
	}

	public String getEnqrAssignedTo() {
		return enqrAssignedTo;
	}

	public void setEnqrAssignedTo(String enqrAssignedTo) {
		this.enqrAssignedTo = enqrAssignedTo;
	}

	public Timestamp getEnqrLudate() {
		return enqrLudate;
	}

	public void setEnqrLudate(Timestamp enqrLudate) {
		this.enqrLudate = enqrLudate;
	}

	public String getEnqrLuser() {
		return enqrLuser;
	}

	public void setEnqrLuser(String enqrLuser) {
		this.enqrLuser = enqrLuser;
	}

	public TrackerCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(TrackerCustomer customer) {
		this.customer = customer;
	}

	public TrackerAuthUser getLuser() {
		return luser;
	}

	public void setLuser(TrackerAuthUser luser) {
		this.luser = luser;
	}

	public TrackerAuthUser getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(TrackerAuthUser assignedTo) {
		this.assignedTo = assignedTo;
	}

}
