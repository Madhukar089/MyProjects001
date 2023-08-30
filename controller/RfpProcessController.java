package com.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.contract.AnalyticsDao;
import com.contract.EmailContract;
import com.contract.EnquiryDAO;
import com.contract.RFPDAO;
import com.models.DTO.AdminUsersDTO;
import com.models.DTO.RFPRDocumentDTO;
import com.models.DTO.ReviewApproveDTO;

@Controller

public class RfpProcessController {

	private RFPDAO RFPDao;
	private EnquiryDAO enquiryDao;
	private EmailContract emailContract;
	private AnalyticsDao analyticsDao;
	private static Logger LOGGER = Logger.getLogger(RfpProcessController.class);

	@Autowired
	public RfpProcessController(RFPDAO rFPDao, EnquiryDAO enquiryDao, EmailContract emailContract,
			AnalyticsDao analyticsDao) {
		this.enquiryDao = enquiryDao;
		RFPDao = rFPDao;
		this.emailContract = emailContract;
		this.analyticsDao = analyticsDao;
	}

	// Retrieves RFPs for review by the specified user
	@RequestMapping(value = "getrfpforreview")
	public String getRfpForReview(@RequestParam("username") String loginUser, Model model) {
		// Logging statement to indicate the start of the method
		LOGGER.debug("Retrieving RFPs for review for user: " + loginUser);

		// Retrieve RFPs for review by the specified user
		List<RFPRDocumentDTO> data = RFPDao.getRFPsForReview(loginUser);
		LOGGER.debug("Getting RFP data for review ");
		// Add the retrieved data to the model
		model.addAttribute("reviewmanagerdata", data);

		// Logging statement to indicate successful retrieval of RFPs for review
		LOGGER.debug("Retrieved RFPs for review successfully");

		return "reviewmanager";
	}

	// Retrieves RFP document data by ID
	@RequestMapping(value = "getRFPDdata")
	public String getRfpdDatabyId(@RequestParam("rfpdId") int rfpdId, Model model) {
		// Logging statement to indicate the start of the method
		LOGGER.debug("Retrieving RFP document data for ID: " + rfpdId);

		// Retrieve RFP document details based on the provided ID
		RFPRDocumentDTO rfpDocumentData = RFPDao.getRFPDocumentDetails(rfpdId);

		model.addAttribute("rfpr", rfpDocumentData);

		// Logging statement to indicate the successful retrieval of RFP document data
		LOGGER.debug("Retrieved RFP document data for ID: " + rfpdId);

		return "reviewManagerData";
	}

	@RequestMapping(value = "rejectRFPDoc")
	public void rejectRFPDocument(@RequestParam("rfpdId") int rfpdId) {
		// Logging statement to indicate the start of the method
		LOGGER.debug("Rejecting RFP document with ID: " + rfpdId);

		// Reject the RFP document by ID
		RFPDao.rejectRFPDocumentById(rfpdId);

		// Logging statement to indicate the successful rejection of the RFP document
		LOGGER.debug("Rejected RFP document with ID: " + rfpdId);
	}

	@RequestMapping(value = "reviewApprovalRequest", method = RequestMethod.POST)
	public ResponseEntity<String> approvalRequestHandler(
			@ModelAttribute("reviewApproveDTO") ReviewApproveDTO reviewApproveDTO, Model model) {
		// Logging statement to indicate the start of the method
		LOGGER.debug("Processing approval request for RFP with ID: " + reviewApproveDTO.getRfpId());

		// Retrieve customer email
		String customerEmail = enquiryDao.getCustomerEmail(reviewApproveDTO);

		// Approve the RFP document by ID
		RFPDao.approveRFPDocumentById(reviewApproveDTO.getRfpId());

		// Compose and send email to the customer
		String to = customerEmail;
		String subject = "Congratulations! Here is your RFP ";
		emailContract.sendreviewEmail(to, subject, reviewApproveDTO);

		// Logging statement to indicate the successful email sending
		LOGGER.debug("Approval request email sent successfully to customer: " + customerEmail);

		return ResponseEntity.ok("Email sent successfully to Customer!!" + "\n" + "Please close the overlay");
	}

	@RequestMapping(value = "adminUsers")
	public String showAdminUsers(Model model) {

		List<AdminUsersDTO> adminUsers = analyticsDao.getAdminUsers();

		model.addAttribute("adminUsers", adminUsers);

		return "adminUsers";
	}

}
