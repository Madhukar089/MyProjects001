package com.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.filter.CustomerFilter;
import com.filter.EnquiryFilter;
import com.filter.RFPDocumentFilter;
import com.filter.RFPFilter;
import com.models.DTO.RFPRDocumentDTO;
import com.models.Input.Customer;
import com.models.Input.CustomerFilterModel;
import com.models.Input.EnquiryFilterModel;
import com.models.Input.RFPDocumentFilterModel;
// import com.models.Input.RFPFilterModel;
// import com.models.Input.RfpDocumentFilterModel;
import com.models.Input.RFPFilterModel;
import com.models.Output.TrackerEnquiry;
import com.models.Output.TrackerRFP;
import com.services.RFPService;

@Controller

@SessionAttributes({ "allenquiries", "customer", "technicalTeam", "RFPDCategories" })
public class FilterController {

	private CustomerFilter customerFilter;

	private EnquiryFilter enquiryFilter;

	private RFPFilter rfpFilter;

	private RFPDocumentFilter rfpDocumentFilter;

	private RFPService serivce;

	private static Logger LOGGER = Logger.getLogger(FilterController.class);

	@Autowired
	public FilterController(CustomerFilter customerFilter, EnquiryFilter enquiryFilter, RFPFilter rfpFilter,
			RFPDocumentFilter rfpDocumentFilter, RFPService serivce) {
		this.customerFilter = customerFilter;
		this.enquiryFilter = enquiryFilter;
		this.rfpFilter = rfpFilter;
		this.rfpDocumentFilter = rfpDocumentFilter;

		this.serivce = serivce;

	}

	@RequestMapping(value = "Enquiryfilter")
	public String filterEnquiryData(Model model, @ModelAttribute EnquiryFilterModel enqr,
			@RequestParam("username") String user) {

		// Logging statement to indicate the start of the method
		LOGGER.debug("Filtering Enquiry data...");

		// Filtering the enquiry data based on the provided filter criteria
		List<TrackerEnquiry> enquirydata = enquiryFilter.filterEnquiryData(enqr.getEnqrId(), enqr.getEnqrStatus(),
				enqr.getEnqrCreatedBy(), user);

		// Logging statement to indicate the successful filtering of enquiry data
		LOGGER.debug("Enquiry data filtered based on Enquiry Id/Enquiry status/Enquiry created by");

		// Adding the filtered enquiry data to the model for rendering in the view
		model.addAttribute("allenquiries", enquirydata);

		serivce.assignRFP(model);

		serivce.getCategories(model);

		// Logging statement to indicate the successful completion of the method
		LOGGER.info("Enquiry data filtered :" + enquirydata);

		return "Enquiries";
	}

	@RequestMapping(value = "rfpfilter")
	public String filterRFPData(Model model, @ModelAttribute RFPFilterModel rfp,
			@RequestParam("username") String user) {

		// Logging statement to indicate the start of the method
		LOGGER.debug("Filtering RFP data...");

		// Filtering the RFP data based on the provided filter criteria
		List<TrackerRFP> RFPData = rfpFilter.filterRFPData(rfp.getRfprId(), rfp.getRfprStatus(),
				rfp.getRfprCreatedAusrId(), user);
		System.out.println("=====================================");
		System.out.println(RFPData);

		// Logging statement to indicate the successful filtering of RFP data
		LOGGER.debug("RFP data filtered based on RFP Id/RFP status/RFP created by");

		// Adding the filtered RFP data to the model for rendering in the view
		model.addAttribute("allrfps", RFPData);

		serivce.assignRFP(model);

		serivce.getRFPDocumentTypes(model);

		serivce.getCategories(model);

		serivce.getRFPDocumentCategories(model);

		serivce.assignRFPtoManager(model);

		// Logging statement to indicate the successful completion of the method
		LOGGER.info("RFP data filtered : " + RFPData);

		return "RFPs";
	}

	@RequestMapping(value = "customerfilter")
	public String filterCustomerData(@RequestParam(name = "page", defaultValue = "1") int page, Model model,
			@ModelAttribute CustomerFilterModel filteroptions) {

		// Logging statement to indicate the start of the method
		LOGGER.debug("Filtering customer data...");
		List<Customer> customerData = customerFilter.filterCustomerData(filteroptions.getCustId(),
				filteroptions.getCustType(), filteroptions.getCustName());

		int pageSize = 10; // Number of records to display per page
		int totalCustomer = customerData.size();
		int totalPages = (int) Math.ceil(totalCustomer / (double) pageSize);

		// Calculate the start and end indexes for the current page
		int startIndex = (page - 1) * pageSize;
		int endIndex = Math.min(startIndex + pageSize, totalCustomer);

		List<Customer> customersonPage = customerData.subList(startIndex, endIndex);
		// Filtering the customer data based on the provided filter criteria

		// Adding the filtered customer data to the model for rendering in the view
		model.addAttribute("customer", customerData);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);

		// Logging statement to indicate the successful filtering of customer data
		LOGGER.debug("Customer data filtered based on Customer Id/Customer Type/Customer Name");
		LOGGER.info("customer data filtered : " + customerData);
		return "customer";
	}

	@RequestMapping(value = "RFPDocumentfilter")
	public String filterRFPDocumentData(Model model, @ModelAttribute RFPDocumentFilterModel rfpd,
			@RequestParam("username") String user) {

		// Logging statement to indicate the start of the method
		LOGGER.debug("Filtering RFP Document data...");

		// Filtering the RFP Document data based on the provided filter criteria
		List<RFPRDocumentDTO> RFPDocData = rfpDocumentFilter.filterRFPDocumentData(rfpd.getRfpdId(),
				rfpd.getRfpdSharedStatus(), rfpd.getRpfdReviewedBy(), user);

		// Adding the filtered RFP Document data to the model for rendering in the view
		model.addAttribute("reviewmanagerdata", RFPDocData);

		// Logging statement to indicate the successful filtering of RFP Document data
		LOGGER.debug("RFP Document data filtered based on RFP Document Id/ Shared Status/ Reviewed By");
		LOGGER.info("RFP Document data filtered : " + RFPDocData);

		return "reviewmanager";
	}

}