package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.view.RedirectView;

import com.contract.EmailContract;
import com.contract.EnquiryDAO;
import com.contract.RFPDAO;
import com.models.DTO.RFPDTO;
import com.models.DTO.RejectionMailDTO;
import com.models.Output.TrackerRFP;
import com.services.MasterRFPprocessTracker;
import com.services.RFPService;

@Controller

public class RFPController {

	private RFPDAO RFPDao;

	private RFPService serivce;

	private EmailContract emailContract;

	private EnquiryDAO enquiryDAO;
	private MasterRFPprocessTracker masterRFPprocessTracker;

	@Autowired
	public RFPController(RFPDAO rFPDao, RFPService serivce, EmailContract emailContract, EnquiryDAO enquiryDAO,
			MasterRFPprocessTracker masterRFPprocessTracker) {
		RFPDao = rFPDao;
		this.serivce = serivce;
		this.emailContract = emailContract;
		this.enquiryDAO = enquiryDAO;
		this.masterRFPprocessTracker = masterRFPprocessTracker;
	}

	// Retrieves RFPS by user
	@RequestMapping(value = "getRfpsByUser")
	public String getRFPByUser(@RequestParam("username") String loginuser, Model model) {
		List<TrackerRFP> rfpsList = RFPDao.getRFPsByUser(loginuser);

		serivce.getRFPMetaData(model);

		model.addAttribute("allrfps", rfpsList);

		return "RFPs";
	}

	@RequestMapping(value = "activeRFPs", method = RequestMethod.GET)
	public String getActiveRFPs(@RequestParam("username") String loginUser, Model model) {

		List<TrackerRFP> activeRFPs = RFPDao.getOpenRFPs(loginUser);

		serivce.getRFPMetaData(model);

		model.addAttribute("allrfps", activeRFPs);

		return "RFPs";
	}

	// Retrieves only the rejected RFPs
	@RequestMapping(value = "rejectedRFPs", method = RequestMethod.GET)
	public String getRejectedRFPs(@RequestParam("username") String loginUser, Model model) {
		List<TrackerRFP> rejectedRFPs = RFPDao.getRejecetdRFPs(loginUser);

		serivce.getRFPMetaData(model);
		model.addAttribute("allrfps", rejectedRFPs);
		return "RFPs";
	}

	// Converts enquiry into RFP
	@RequestMapping(value = "convertToRfp")
	public String convertIntoRFP(@ModelAttribute RFPDTO RfpDto, Model model) {
		// Insert the RFP data into the database

		int rfp_id = RFPDao.insertRFP(RfpDto);
		RejectionMailDTO convertedData = enquiryDAO.rejectionMail(RfpDto.getRfprEnqrId());
		String to = convertedData.getCustomerEmail();
		int customerId = convertedData.getCustomerId();

		TrackerRFP rfpdata = RFPDao.getRFPByenqrId(RfpDto.getRfprEnqrId());
		masterRFPprocessTracker.updateTechTeamUserByPresales(rfpdata.getRfprId(), RfpDto.getAssigned_to(),
				RfpDto.getRfprEnqrId());
		emailContract.rfpConvertionMail(to, "Convertion mail", customerId);
		return "Success";
	}

	// Retrieves RFP data by ID
	@RequestMapping(value = "rfpdata")
	public String getRFPDataById(@RequestParam("rfprId") int rfpr_id, Model model) {
		// Retrieve RFP data based on the provided ID
		TrackerRFP rfpData = RFPDao.getRFPById(rfpr_id);

		model.addAttribute("rfpdata", rfpData);
		return "RfpData";
	}

	// Retrieves all RFPs
	@RequestMapping(value = "RFPSRout")
	public String getAllRFPs(Model model) {
		// Retrieve all RFPs
		List<TrackerRFP> rfpsList = RFPDao.getAllRFPs();

		model.addAttribute("allrfps", rfpsList);

		return "RFPs";
	}

	// Updates RFP status
	@RequestMapping(value = "updaterfpstatus")
	public String updateStatus(@RequestParam("rfprid") Integer rfprid, @RequestParam("status") String status) {
		// Update the status of the RFP with the provided ID
		boolean statusUpdated = RFPDao.updateEnquiryStatus(rfprid, status);

		if (statusUpdated) {
			return "RFPSRout";
		} else {
			return "Failed to update status";
		}
	}

	// Rejects RFP
	@RequestMapping(value = "rejectrfp")
	public String reject(@RequestParam("rfprid") int rfpr_id) {
		// Reject the RFP with the provided RFP ID
		boolean val = RFPDao.rejectByEnquiryId(rfpr_id);
		if (val) {
			return "RFPS";
		}
		return "Failed";
	}

	// Handles RFP document submission
	@RequestMapping(value = "Rfpdocument", consumes = "multipart/form-data")
	public RedirectView RFPdata(MultipartHttpServletRequest request, HttpServletRequest req) {

		// Extract the form data and file from the request
		serivce.extractAndUpdateFormDetails(request, req);

		return new RedirectView("dashboard");
	}

	// Retrieves RFPs for review
	@RequestMapping(value = "reviewed")
	public String getRFPsByUser(@RequestParam("username") String loginUser, Model model) {
		// Retrieve RFPs based on the user's status
		List<TrackerRFP> RFPByStatus = RFPDao.getRFPByStatus(loginUser);

		// Retrieve necessary data for rendering the view
		serivce.getCategories(model);
		model.addAttribute("RFPByStatus", RFPByStatus);

		return "review";
	}

}
