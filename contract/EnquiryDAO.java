package com.contract;

import java.util.List;

import com.models.DTO.EnquiryDTO;
import com.models.DTO.RejectionMailDTO;
import com.models.DTO.ReviewApproveDTO;
import com.models.Input.Customer;
import com.models.Output.Auth_users;
import com.models.Output.ClosedAccountsModel;
import com.models.Output.PipeLineOutputModel;
import com.models.Output.TrackerDocument;
import com.models.Output.TrackerEnquiry;

public interface EnquiryDAO {

	Integer insertEnquiryRecordAndReturnEnqrId(Customer customer, Integer customerId);

	int insertEnquiryDocument(String description, Integer enquiryId, String documentName);

	EnquiryDTO getEnquiryDetails(Integer enquiryId);

	boolean rejectByEnquiryId(Integer enquiryId, String desc);

	public List<TrackerEnquiry> getAllEnquiries();

	boolean updateEnquiryStatus(Integer enquiryId, String status);

	List<Auth_users> assignEnquirytoPresales();

	boolean EnquiryAssignedto(Integer enquiryId, String User);

	List<ClosedAccountsModel> getClosedAccounts();

	String getCustomerEmail(ReviewApproveDTO reviewApproveDTO);

	public List<PipeLineOutputModel> getEnquiryStatusList();

	List<TrackerDocument> getAllDocumnets(Integer enqr_id);

	List<TrackerEnquiry> getEnquiriesByUser(String loginUser);

	List<TrackerEnquiry> getOpenEnquiries(String loginUser);

	List<TrackerEnquiry> getRejectedEnquiries(String loginUser);

	RejectionMailDTO rejectionMail(int enqrId);
}
