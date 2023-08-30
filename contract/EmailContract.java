package com.contract;

import com.models.DTO.ReviewApproveDTO;

public interface EmailContract {

	public String requestEmail(String to, String subject, String formIdentifier);

	public String rfpRequestEmail(String to, String subject, String formIdentifier);

	public String acknowledgmentMail(String to, String subject, Integer cust_id);

	public String sendreviewEmail(String to, String subject, ReviewApproveDTO reviewApproveDTO);

	String rejectMail(String to, String subject, Integer custId, String Desc);

	String rfpConvertionMail(String to, String subject, Integer custId);

}
