package com.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.contract.CustomerDAO;
import com.contract.CustomerUtilityInterface;
import com.contract.DocumentContract;
import com.contract.EmailContract;
import com.contract.EnquiryDAO;
import com.google.gson.Gson;
import com.models.DTO.EnquriyMasterModel;
import com.models.Input.Customer;
import com.models.Input.EnquiryDocumentModel;
import com.services.MasterRFPprocessTracker;

@Controller
@SessionAttributes({ "cust_enqrDetails" })

public class CustomerController {

	private CustomerDAO customerDao;

	private EnquiryDAO enquiryDao;
	private EmailContract emailContract;
	private DocumentContract documentContract;
	private MasterRFPprocessTracker masterRFPprocessTracker;

	private CustomerUtilityInterface customerUtilityInterface;

	private static Logger LOGGER = Logger.getLogger(CustomerController.class);

	@Autowired
	public CustomerController(CustomerDAO customerDao, EnquiryDAO enquiryDao, EmailContract emailContract,
			CustomerUtilityInterface customerUtilityInterface, DocumentContract documentContract,
			MasterRFPprocessTracker masterRFPprocessTracker) {

		this.customerDao = customerDao;
		this.enquiryDao = enquiryDao;
		this.emailContract = emailContract;
		this.customerUtilityInterface = customerUtilityInterface;
		this.documentContract = documentContract;
		this.masterRFPprocessTracker = masterRFPprocessTracker;

	}

	// Handles the request for the customer home page
	@RequestMapping(value = "customerhome")
	public String customerHome(Model model) {

		LOGGER.debug("entering customer home");
		return "home";
	}

	// Handles the form submission for the customer enquiry
	@RequestMapping(value = "enquiry")
	public RedirectView enquiryData(@ModelAttribute Customer customer, HttpServletRequest req) {
		System.out.println(customer);

		// Insert customer record and retrieve the customer ID
		Integer new_cust_id = customerDao.insertCustomerRecordAndReturnCustId(customer);
		// fire a query to enter only cust id into master table
		// Insert enquiry record and retrieve the enquiry ID
		Integer enqr_id = enquiryDao.insertEnquiryRecordAndReturnEnqrId(customer, new_cust_id);
		// update a query to enter only enqr_id into master table
		LOGGER.info("customer enquiry created with input " + customer);
		// Insert enquiry document
		MultipartFile file = customer.getFormFile();
		documentContract.uploadDocument(file, req);
		enquiryDao.insertEnquiryDocument(customer.getDesc(), enqr_id, file.getOriginalFilename());
		LOGGER.debug("customer entered enquiry details ");

		EnquriyMasterModel masterModelData = masterRFPprocessTracker.getEnquiryData(enqr_id);
		masterRFPprocessTracker.insertSalesTeamData(masterModelData);
		// Send acknowledgment mail to the customer
		emailContract.acknowledgmentMail(customer.getCustEmail(), "welcome mail", new_cust_id);
		LOGGER.info("mail sent to customer with custid: " + new_cust_id);
		return new RedirectView("customerhome");
	}

	// Checks if the customer ID is valid
	@RequestMapping(value = "checkvalidcustomer")
	@ResponseBody
	public String checkValidCustomerId(@RequestParam("customerId") Integer customerId, Model model) {
		// Logging statement to indicate the start of the method
		LOGGER.debug("Checking validity of customer ID: " + customerId);

		// Checking whether the customer ID is valid using customerUtilityInterface
		boolean isValidCustomer = customerUtilityInterface.validateCustomer(customerId);

		// Logging statement to indicate the result of customer ID validation
		if (isValidCustomer) {
			LOGGER.info("Customer ID is valid: " + customerId);

			// Getting the customer name using customerUtilityInterface
			String customerName = customerUtilityInterface.getCustomerName(customerId);

			// Logging statement to indicate the successful retrieval of customer name
			LOGGER.info("Retrieved customer name for ID: " + customerId + " - " + customerName);

			// Returning the customer name as a response
			return customerName;
		} else {
			LOGGER.error("Customer ID is invalid: " + customerId);

			// Returning an error message as a response
			return "You are not a customer. Please fill out the request form.";
		}
	}

	@RequestMapping(value = "checkvalidcustforfeature")
	@ResponseBody
	public String checkValidCustomerById(@RequestParam("customerId") Integer customerId) {
		// Logging statement to indicate the start of the method
		LOGGER.debug("Checking validity of customer ID for features: " + customerId);

		// Checking whether the customer ID is valid using customerUtilityInterface
		boolean isValidCustomer = customerUtilityInterface.validateCustomer(customerId);

		// Logging statement to indicate the result of customer ID validation
		if (isValidCustomer) {
			LOGGER.info("Customer ID is valid for features: " + customerId);

			// Getting the enquiry details for the customer using customerUtilityInterface
			Map<String, Integer> custEnquiryDetails = customerUtilityInterface.getEnquiryID(customerId);

			// Logging statement to indicate the successful retrieval of enquiry details
			LOGGER.info("Retrieved enquiry details for customer ID: " + customerId);

			// Getting the customer name using customerUtilityInterface
			String name = customerUtilityInterface.getCustomerName(customerId);

			// Logging statement to indicate the successful retrieval of customer name
			LOGGER.info("Retrieved customer name for ID: " + customerId + " - " + name);
			Gson gson = new Gson();
			String json = gson.toJson(custEnquiryDetails);

			return json + "\n" + name;
		} else {
			LOGGER.error("Customer ID is invalid for features: " + customerId);

			// Returning an error message as a response
			return "You are not a customer. Please fill out the request form.";
			// hello
		}
	}

	// Handles the request for new enquiry from the customer
	@RequestMapping(value = "addenquiry")
	public RedirectView addNewEnquiry(@ModelAttribute EnquiryDocumentModel enquiryDocumentModel) {
		// Logging statement to indicate the start of the method
		LOGGER.debug("Adding a new enquiry");

		// Adding a new enquiry using customerUtilityInterface and retrieving the enquiry ID
		Integer enquiryId = customerUtilityInterface.addNewEnquiry(enquiryDocumentModel);

		// Logging statement to indicate the successful addition of the enquiry
		LOGGER.info("Added a new enquiry with ID: " + enquiryId);

		// Inserting the enquiry document into the database using enquiryDao
		enquiryDao.insertEnquiryDocument(enquiryDocumentModel.getDesc(), enquiryId,
				enquiryDocumentModel.getFiles().getAbsolutePath());

		// Logging statement to indicate the successful insertion of the enquiry document
		LOGGER.info("Inserted enquiry document with description for enquiry ID: " + enquiryId);
		return new RedirectView("customerhome");
	}

	// Handles the request for more features from the customer
	@RequestMapping(value = "addfeature")
	public RedirectView addNewFeature(@ModelAttribute EnquiryDocumentModel enquiryDocumentModel) {
		// Logging statement to indicate the start of the method
		LOGGER.debug("Adding a new feature");

		// Inserting the feature enquiry document into the database using enquiryDao
		enquiryDao.insertEnquiryDocument(enquiryDocumentModel.getDesc(), enquiryDocumentModel.getCustId(),
				enquiryDocumentModel.getFiles().getAbsolutePath());

		// Logging statement to indicate the successful insertion of the feature enquiry document
		LOGGER.info("Inserting feature for enquiry with document and description for enquiry ID: "
				+ enquiryDocumentModel.getEnqrId());

		// Returning a redirect view to the customer home page
		return new RedirectView("customerhome");
	}

	@RequestMapping(value = "showFile", method = RequestMethod.GET)
	public void showFile(@RequestParam("filename") String filename, HttpServletRequest request,
			HttpServletResponse response) {
		documentContract.getFile(filename, request, response);
	}

}
