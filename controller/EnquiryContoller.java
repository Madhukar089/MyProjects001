package com.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.contract.EmailContract;
import com.contract.EnquiryDAO;
import com.models.DTO.EnquiryDTO;
import com.models.DTO.RejectionMailDTO;
import com.models.DTO.UpdateDTO;
import com.models.Input.RejectedEnquiry;
import com.models.Output.ClosedAccountsModel;
import com.models.Output.TrackerEnquiry;
import com.services.CustomerUtility;
import com.services.MasterRFPprocessTracker;
import com.services.RFPService;

@Controller
@SessionAttributes({ "allenquiries", "technicalTeam", "RFPDCategories", "loginuser" })

public class EnquiryContoller {
	// enq
	private EnquiryDAO enquiryDao;

	private RFPService serivce;

	private CustomerUtility customerUtility;

	private EmailContract emailContract;
	private MasterRFPprocessTracker masterRFPprocessTracker;
	private static Logger LOGGER = Logger.getLogger(EnquiryContoller.class);

	@Autowired
	public EnquiryContoller(EnquiryDAO enquiryDao, EmailContract emailContract, RFPService serivce,
			CustomerUtility customerUtility, MasterRFPprocessTracker masterRFPprocessTracker) {

		this.enquiryDao = enquiryDao;

		this.serivce = serivce;

		this.customerUtility = customerUtility;

		this.emailContract = emailContract;
		this.masterRFPprocessTracker = masterRFPprocessTracker;

	}

	@RequestMapping(value = "EnquiriesByUser", method = RequestMethod.GET)
	public String getEnquiriesByUser(@RequestParam("username") String loginuser, Model model) {
		// Logging statement to indicate the start of the method
		LOGGER.debug("Getting enquiries for user: " + loginuser);

		// Getting user-based enquires from enquiryDao
		List<TrackerEnquiry> user_based_enquiries = enquiryDao.getEnquiriesByUser(loginuser);

		// Logging statement to indicate the successful retrieval of user-based enquires
		LOGGER.info("Retrieved user-based enquiries for user: " + loginuser);

		// Adding user-based enquires and login user to the model
		model.addAttribute("allenquiries", user_based_enquiries);
		model.addAttribute("loginuser", loginuser);
		serivce.getCategories(model);
		serivce.assignRFP(model);
		serivce.getRFPDocumentTypes(model);

		// Logging statement to indicate the redirection to the Enquires page
		LOGGER.debug("Redirecting to Enquiries page with the list of enquiries based on the loginuser");

		return "Enquiries";
	}

	@RequestMapping(value = "activeEnquiries", method = RequestMethod.GET)
	public String getActiveEnquiries(@RequestParam("username") String loginUser, Model model) {
		// Logging statement to indicate the start of the method
		LOGGER.debug("Getting active enquiries for user: " + loginUser);

		// Getting list of active enquires from enquiryDao
		List<TrackerEnquiry> activeEnquires = enquiryDao.getOpenEnquiries(loginUser);

		// Logging statement to indicate the successful retrieval of active enquires
		LOGGER.info("Retrieved active enquiries for user: " + loginUser);

		// Adding active enquires to the model
		model.addAttribute("allenquiries", activeEnquires);

		// Logging statement to indicate the redirection to the Enquires page
		LOGGER.debug("Redirecting to Enquiries page with the list of active enquiries based on the loginuser");

		return "Enquiries";
	}

	@RequestMapping(value = "rejectedEnquiries", method = RequestMethod.GET)
	public String getRejectedEnquiries(@RequestParam("username") String loginUser, Model model) {
		// Logging statement to indicate the start of the method
		LOGGER.debug("Getting rejected enquiries for user: " + loginUser);

		// Getting list of rejected enquires from enquiryDao
		List<TrackerEnquiry> rejectedEnquiries = enquiryDao.getRejectedEnquiries(loginUser);

		// Logging statement to indicate the successful retrieval of rejected enquires
		LOGGER.info("Retrieved rejected enquiries for user: " + loginUser);

		// Adding rejected enquires to the model
		model.addAttribute("allenquiries", rejectedEnquiries);

		// Logging statement to indicate the redirection to the Enquires page
		LOGGER.debug("Redirecting to Enquiries page with the list of rejected enquiries based on the loginuser");

		return "Enquiries";
	}

	@RequestMapping(value = "rejectEnquiry")
	public RedirectView reject(@ModelAttribute RejectedEnquiry rejectedEnquiry) {
		// Logging statement to indicate the start of the method
		LOGGER.debug("Rejecting the enquiry with enquiry Id: " + rejectedEnquiry.getEnqrId());

		// Rejecting the enquiry based on enquiry details using enquiryDao
		boolean val = enquiryDao.rejectByEnquiryId(rejectedEnquiry.getEnqrId(), rejectedEnquiry.getEnqrDescription());

		RejectionMailDTO rejectedData = enquiryDao.rejectionMail(rejectedEnquiry.getEnqrId());
		String to = rejectedData.getCustomerEmail();
		int customerId = rejectedData.getCustomerId();
		String description = rejectedEnquiry.getEnqrDescription();
		emailContract.rejectMail(to, "Rejection mail", customerId, description);

		// Logging statement to indicate the rejection of the enquiry
		LOGGER.info("Rejected the enquiry with enquiry Id: " + rejectedEnquiry.getEnqrId());

		return new RedirectView("Enquiries");
	}

	// Retrieves enquiry data by its ID and adds it to the model
	@RequestMapping(value = "getenquirydata", method = RequestMethod.GET)
	public String getEnquiryDatabyId(@RequestParam("enquiryId") int enquiryId, Model model) {
		// Getting the document details of an enquiry based on enquiry Id
		List<String> documnetPath = customerUtility.getDocuments(enquiryId);
		LOGGER.info("Getting list of documents of a enquiry by the enquiry Id: " + enquiryId);

		// Getting the details of an enquiry
		EnquiryDTO enquirydata = enquiryDao.getEnquiryDetails(enquiryId);
		LOGGER.info("Getting details of a enquiry by the enquiry Id : " + enquiryId);

		enquirydata.setDocuments(documnetPath);
		// Getting the list of user Id's of presales team
		customerUtility.assignEnquirytouser(enquiryId, model);
		LOGGER.debug("Getting the list of user Id's of presales team to assign the enquiry");
		model.addAttribute("enquiry", enquirydata);
		return "EnquiryData";
	}

	// Updates the status of an enquiry
	@RequestMapping(value = "updatestatus", method = RequestMethod.POST)
	public RedirectView updateStatus(@ModelAttribute UpdateDTO updatestatus, Model model) {
		// Update the status of an enquiry
		boolean statusUpdated = enquiryDao.updateEnquiryStatus(updatestatus.getEnquiryId(), updatestatus.getStatus());
		LOGGER.info("Update the status of an enquiry with enquiry Id: " + updatestatus.getEnquiryId());
		if (statusUpdated) {
			return new RedirectView("Enquiries");
		} else {
			model.addAttribute("failedmessage", "Failed to update the status of the Enquiry");
			LOGGER.error("Failed to update the status of an enquiry with enquiry Id: " + updatestatus.getEnquiryId());

			return new RedirectView("Failed");
		}
	}

	// Assigns an enquiry to a user
	@RequestMapping(value = "assignEnquiry", method = RequestMethod.GET)
	public RedirectView assignEnquiry(@RequestParam("enquiryId") Integer enquiryId,
			@RequestParam("assign_to") String user, Model model) {
		// Assigning the enquiry to the presales team
		boolean enquiryAssigned = enquiryDao.EnquiryAssignedto(enquiryId, user);
		if (enquiryAssigned) {

			masterRFPprocessTracker.udpatePresalesUserStatusBySalesUser(user, enquiryId);
			LOGGER.info("Assigning the enquiry to the presales team for enquiry Id: " + enquiryId);

			return new RedirectView("Enquiries");
		} else {
			// If assigning the enquiry is failed, then redirect to Failed.jsp page to show the
			// message
			model.addAttribute("failedmessage", "Failed to assign the Enquiry");
			LOGGER.error("Failed to assign the enquiry to the presales team for enquiry Id: " + enquiryId);

			return new RedirectView("Failed");
		}
	}

	@RequestMapping(value = "accClosed")
	public String getClosedAccountsData(Model model) {
		// Logging statement to indicate the start of the method
		LOGGER.debug("Getting the list of customer accounts that are closed");

		// Getting list of customer accounts closed from enquiryDao
		List<ClosedAccountsModel> closedAccounts = enquiryDao.getClosedAccounts();

		// Adding closed accounts to the model
		model.addAttribute("closedAccounts", closedAccounts);

		return "Closed_Accounts";
	}

}
