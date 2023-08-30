package com.models.DTO;

import java.sql.Timestamp;

public class RFPDTO {
	private int rfprId;
	private int rfprEnqrId;
	private String rfprSubject;
	private String rfprIntroNote;
	private String rpfrRfpcId;
	private String rfprCreatedAusrId;
	private Timestamp rfprLudatetime;
	private String rfprStatus;

	public RFPDTO() {
		super();
	}

	public RFPDTO(int rfprId, int rfprEnqrId, String rfprSubject, String rfprIntroNote, String rpfrRfpcId,
			String rfprCreatedAusrId, Timestamp rfprLudatetime, String rfprStatus, String assigned_to) {
		super();
		this.rfprId = rfprId;
		this.rfprEnqrId = rfprEnqrId;
		this.rfprSubject = rfprSubject;
		this.rfprIntroNote = rfprIntroNote;
		this.rpfrRfpcId = rpfrRfpcId;
		this.rfprCreatedAusrId = rfprCreatedAusrId;
		this.rfprLudatetime = rfprLudatetime;
		this.rfprStatus = rfprStatus;
		this.assigned_to = assigned_to;
	}

	private String assigned_to;

	public int getRfprId() {
		return rfprId;
	}

	public void setRfprId(int rfprId) {
		this.rfprId = rfprId;
	}

	public int getRfprEnqrId() {
		return rfprEnqrId;
	}

	public void setRfprEnqrId(int rfprEnqrId) {
		this.rfprEnqrId = rfprEnqrId;
	}

	public String getRfprSubject() {
		return rfprSubject;
	}

	public void setRfprSubject(String rfprSubject) {
		this.rfprSubject = rfprSubject;
	}

	public String getRfprIntroNote() {
		return rfprIntroNote;
	}

	public void setRfprIntroNote(String rfprIntroNote) {
		this.rfprIntroNote = rfprIntroNote;
	}

	public String getRpfrRfpcId() {
		return rpfrRfpcId;
	}

	public void setRpfrRfpcId(String rpfrRfpcId) {
		this.rpfrRfpcId = rpfrRfpcId;
	}

	public String getRfprCreatedAusrId() {
		return rfprCreatedAusrId;
	}

	public void setRfprCreatedAusrId(String rfprCreatedAusrId) {
		this.rfprCreatedAusrId = rfprCreatedAusrId;
	}

	public Timestamp getRfprLudatetime() {
		return rfprLudatetime;
	}

	public void setRfprLudatetime(Timestamp rfprLudatetime) {
		this.rfprLudatetime = rfprLudatetime;
	}

	public String getRfprStatus() {
		return rfprStatus;
	}

	public void setRfprStatus(String rfprStatus) {
		this.rfprStatus = rfprStatus;
	}

	public String getAssigned_to() {
		return assigned_to;
	}

	public void setAssigned_to(String assigned_to) {
		this.assigned_to = assigned_to;
	}

}
