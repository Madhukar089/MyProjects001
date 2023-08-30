package com.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.contract.DynamicContract;
import com.customExceptions.BlankFormException;
import com.customExceptions.InsertException;
import com.models.Input.RequestFormModel;
import com.models.Output.CommunicationData;
import com.models.Output.EnqFieldsModel;
import com.models.Output.RFPFieldsModel;
import com.models.Output.RFPRequestFormModel;

@Controller
@SessionAttributes({ "requestModel", "rfpRequestModel" })
public class DynamicFormController {

	private final DynamicContract dynamicContract;

	private static final Logger LOGGER = Logger.getLogger(DynamicFormController.class);

	@Autowired
	DynamicFormController(DynamicContract dynamicContract) {
		this.dynamicContract = dynamicContract;
	}

	// -------------------------------------Enquires--------------------------------------------------

	// Takes in the request of 'RequestForMoreData' and routes to the DynamicEnquiryDetails view
	@RequestMapping(value = "/request", method = RequestMethod.POST)
	public String requestHandler(@Validated @ModelAttribute("requestFormModel") RequestFormModel requestFormModel,
			Model model, HttpSession session) {

		LOGGER.info("/request route is being processed");
		model.addAttribute("enquiryDetails", requestFormModel);// Adding input model to the view DynamicEnquiryDetails
		// Setting the session attribute to use the same model in the next routed views
		session.setAttribute("requestModel", requestFormModel);
		LOGGER.info("The request has been routed to the DynamicEnquiryDetails view");

		return "DynamicEnquiryDetails";
	}

	// Get the response from the customers and also their missing responses which are not yet filled
	@RequestMapping(value = "/getCustomerResponse", produces = "application/json")
	@ResponseBody
	public String getCustomerResponse(@RequestParam("enqId") int enquiryId) {

		LOGGER.info("/getCustomerResponse route is being processed");
		String json;
		try {
			json = dynamicContract.getCustomerResponseAsJson(enquiryId);
			LOGGER.info("Returning json data to the View");
			return json;
		} catch (Exception e) {
			return "{\"error\": \"An unknown error occurred..Please do reload the page.\"}";
		}

	}

	// Updates data and send email to the customer along with the dynamicForm's link
	@RequestMapping(value = "/sendMail", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> updateAndSendMail(@RequestBody String enquiryData, HttpSession session) {

		LOGGER.info("/sendMail route is being processed");
		try {
			// Get the RequestFormModel object from the session which has been set before creating the dynamicForm
			RequestFormModel requestFormModel = (RequestFormModel) session.getAttribute("requestModel");

			dynamicContract.UpdateAndSendMail(requestFormModel, enquiryData);

			LOGGER.info("Keeping success message to the view");

			return ResponseEntity.ok("Email sent successfully....Redirecting you to the home page");
		} catch (BlankFormException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (InsertException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An unknown error occured, Please do reload the page!");
		}
	}

	// Shows the dynamic form to the user
	@RequestMapping(value = "/formpage")
	public String showDynamicFormToUser(@RequestParam("formIdentifier") String formIdentifier, Model model) {
		LOGGER.info("/formpage route is being processed");
		try {
			// Retrieve EnquiryFields data based on the formIdentifier generated when sending the dynamicForm
			List<EnqFieldsModel> fields = dynamicContract.getFieldsData(formIdentifier);

			model.addAttribute("formData", fields);// Adding input model for the formPage view
			LOGGER.info("The request has been routed to the formPage view");
			return "formPage";
		} catch (SQLException e) {
			model.addAttribute("errorMessage",
					"An error occurred while retrieving the dynamic form data. Please try again later.");
			return "errorPageForFormLoading";
		}
	}

	// Submits the customer dynamicForm data
	@RequestMapping(value = "/submitting", consumes = "multipart/form-data")
	public ResponseEntity<String> submitCustomerForm(MultipartHttpServletRequest request, HttpServletRequest req) {

		LOGGER.info("Customer form submittion is being processed in /submitting route");
		try {
			// Extract the form data and file from the request
			dynamicContract.extractAndUpdateFormDetails(request, req);

			LOGGER.info("Keeping success message to the view");

			return ResponseEntity.ok("Form submitted successfully!");
		} catch (BlankFormException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You cannot submit the form more than one time!");
		}
	}

	// -------------------------------------RFP-------------------------------------------------------

	// Takes in the request of 'RequestForMoreData' and routes to the DynamicRFPDetails view
	@RequestMapping(value = "/rfpRequest", method = RequestMethod.POST)
	public String rfpRequestHandler(@ModelAttribute("rfpRequestFormModel") RFPRequestFormModel rfpRequestFormModel,
			Model model, HttpSession session) {

		LOGGER.info("/rfpRequest route is being processed");

		// Adding input model to the view DynamicRFPDetails
		model.addAttribute("enquiryDetails", rfpRequestFormModel);
		// Setting the session attribute to use the same model in the next routed views
		session.setAttribute("rfpRequestModel", rfpRequestFormModel);

		LOGGER.info("The request has been routed to the DynamicDetailsRFP view");
		return "DynamicDetailsRFP";
	}

	// Get the response from the customers and also their missing responses which are not yet filled
	@RequestMapping(value = "/getRFPCustomerResponse", produces = "application/json")
	@ResponseBody
	public String getRFPRequestResponses(@RequestParam("rfpId") int rfpId) {

		LOGGER.info("/getRFPCustomerResponse route is being processed");
		String json;
		try {
			json = dynamicContract.getRFPCustomerResponseAsJson(rfpId);
			LOGGER.info("Returning json data to the View");
			return json;
		} catch (Exception e) {
			return "{\"error\": \"An unknown error occurred..Please do reload the page.\"}";
		}
	}

	// Updates data and send email to the customer along with the dynamicForm's link
	@RequestMapping(value = "/RfpSendMail", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> updateRfpAndSendMail(@RequestBody String rfpData, HttpSession session) {

		LOGGER.info("/RfpSendMail route is being processed");
		try {
			// Get the RequestFormModel object from the session which has been set before creating the dynamicForm
			RFPRequestFormModel requestFormModel = (RFPRequestFormModel) session.getAttribute("rfpRequestModel");

			dynamicContract.UpdateRFPAndSendMail(requestFormModel, rfpData);
			LOGGER.info("Keeping success message to the view");

			return ResponseEntity.ok("Email sent successfully....Redirecting you to the home page");
		} catch (BlankFormException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (InsertException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An unknown error occured, Please do reload the page!");
		}
	}

	// Shows the RFP dynamic form to the user
	@RequestMapping(value = "/rfpformpage")
	public String showRfpDynamicFormToUser(@RequestParam("RfpFormIdentifier") String RfpFormIdentifier, Model model) {

		LOGGER.info("/rfpformpage route is being processed");
		try {
			// Retrieve RFPFields data based on the rfpFormIdentifier generated when sending the dynamicForm
			List<RFPFieldsModel> fields = dynamicContract.getRFPFieldsData(RfpFormIdentifier);
			model.addAttribute("formData", fields);// Adding input model for the RFPFormPage view
			LOGGER.info("The request has been routed to the RFPFormPage view");
			return "RFPFormPage";
		} catch (SQLException e) {
			model.addAttribute("errorMessage",
					"An error occurred while retrieving the dynamic form data. Please try again later.");
			return "errorPageForFormLoading";
		}
	}

	// Submits the RFP customer form data
	@RequestMapping(value = "/rfpSubmitting", consumes = "multipart/form-data")
	public ResponseEntity<String> submitRfpCustomerForm(MultipartHttpServletRequest request, HttpServletRequest req) {
		LOGGER.info("Customer form submittion is being processed in /submitting route");
		try {
			// Extract the form data and file from the request
			dynamicContract.extractAndUpdateRFPFormDetails(request, req);

			LOGGER.info("Keeping success message to the view");

			return ResponseEntity.ok("Form submitted successfully!");
		} catch (BlankFormException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You cannot submit the form more than one time!");
		}
	}

	// ----------------------------------------ApprovalForm--------------------------------------------

	// Shows the Regular Approve or Reject Form for the customer when clicking on the link of the email
	@RequestMapping(value = "/formfor")
	public String getApproveRejectForm() {
		return "FormForCommunication";
	}

	// Handles the request approval
	@RequestMapping(value = "/approvalRequest", consumes = "application/json")
	public ResponseEntity<String> handleApproveAction(@RequestBody CommunicationData communicationData) {
		try {
			// Update customer submitted Form details
			dynamicContract.updateApprovalStatus(communicationData);
			return ResponseEntity.ok("Approved");
		} catch (Exception e) {
			// Handling the DataAccessException and returning a specific error response for data access errors
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while updating the data, Please do reload the page!");
		}

	}

	// Handles the request rejection
	@RequestMapping(value = "/rejectionRequest", consumes = "application/json")
	public ResponseEntity<String> handleRejectAction(@RequestBody CommunicationData communicationData) {
		try {
			// Update customer submitted Form details
			dynamicContract.updateApprovalStatus(communicationData);
			return ResponseEntity.ok("Rejected");
		} catch (Exception e) {
			// Handling the DataAccessException and returning a specific error response for data access errors
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while updating the data, Please do reload the page!");
		}

	}

}
