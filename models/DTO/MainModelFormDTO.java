package com.models.DTO;

import java.util.List;

import com.models.Input.RejectEnquiry;
import com.models.Input.RejectRfp;
import com.models.Input.TrackerEnquiryConfig;
import com.models.Input.TrackerRfpConfig;

public class MainModelFormDTO {

	private List<TrackerEnquiryConfig> trackerenquiry;

	private List<RejectEnquiry> rejectenquiry;

	private List<TrackerEnquiryConfig> Approveenquiry;

	private List<RejectRfp> convertrfp;

	private List<RejectRfp> rejectrfp;

	private List<RejectRfp> rfpapproive;

	private List<TrackerRfpConfig> rfpcompleted;

	public List<TrackerEnquiryConfig> getTrackerenquiry() {
		return trackerenquiry;
	}

	public void setTrackerenquiry(List<TrackerEnquiryConfig> trackerenquiry) {
		this.trackerenquiry = trackerenquiry;
	}

	public List<RejectEnquiry> getRejectenuiry() {
		return rejectenquiry;
	}

	public void setRejectenjuiry(List<RejectEnquiry> rejectenjuiry) {
		this.rejectenquiry = rejectenjuiry;
	}

	public List<RejectRfp> getRejectrfp() {
		return rejectrfp;
	}

	public void setRejectrfp(List<RejectRfp> rejectrfp) {
		this.rejectrfp = rejectrfp;
	}

	public List<RejectRfp> getConvertrfp() {
		return convertrfp;
	}

	public void setConvertrfp(List<RejectRfp> convertrfp) {
		this.convertrfp = convertrfp;
	}

	public List<RejectRfp> getRfpapproive() {
		return rfpapproive;
	}

	public void setRfpapproive(List<RejectRfp> rfpapproive) {
		this.rfpapproive = rfpapproive;
	}

	public List<RejectEnquiry> getRejectenquiry() {
		return rejectenquiry;
	}

	public void setRejectenquiry(List<RejectEnquiry> rejectenquiry) {
		this.rejectenquiry = rejectenquiry;
	}

	public List<TrackerEnquiryConfig> getApproveenquiry() {
		return Approveenquiry;
	}

	public void setApproveenquiry(List<TrackerEnquiryConfig> approveenquiry) {
		Approveenquiry = approveenquiry;
	}

	public List<TrackerRfpConfig> getRfpcompleted() {
		return rfpcompleted;
	}

	public void setRfpcompleted(List<TrackerRfpConfig> rfpcompleted) {
		this.rfpcompleted = rfpcompleted;
	}

}