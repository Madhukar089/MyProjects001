package com.contract;

import java.util.List;

import java.util.Map;

import org.springframework.ui.Model;

import com.models.DTO.AccountsClosedDTO;
import com.models.Input.EnquiryDocumentModel;

public interface CustomerUtilityInterface {

	public boolean validateCustomer(Integer cust_id);

	public String getCustomerName(Integer cust_id);

	public Integer addNewEnquiry(EnquiryDocumentModel enquiryDocumentModel);

	public List<AccountsClosedDTO> getAccountsClosed();

	public Map<String, Integer> getEnquiryID(Integer cust_id);
	public List<String> getDocuments(int enquiryId);
	public void assignEnquirytouser(int enquiryId, Model model);
}
