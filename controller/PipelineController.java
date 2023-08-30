package com.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dao.TrackerEnquiryDao;
import com.google.gson.Gson;
import com.models.DTO.MainModelFormDTO;
import com.models.Input.RejectEnquiry;
import com.models.Input.RejectRfp;
import com.models.Input.TrackerEnquiryConfig;
import com.models.Input.TrackerRfpConfig;
import com.services.PipelineService;

@Controller
public class PipelineController {

	private TrackerEnquiryDao trackerenquirydao;

	private MainModelFormDTO mainmodelform;

	private PipelineService pipelineService;

	@Autowired
	public PipelineController(TrackerEnquiryDao trackerenquirydao, MainModelFormDTO mainmodelform,
			PipelineService pipelineService) {

		this.trackerenquirydao = trackerenquirydao;
		this.mainmodelform = mainmodelform;
		this.pipelineService = pipelineService;
	}

	@RequestMapping(value = "/pipeline", method = RequestMethod.GET)
	public String getEnquiryid(Model model) {
		// all enquiry's details are comes to the list

		List<TrackerEnquiryConfig> enquiry = trackerenquirydao.getEnquiry();

		model.addAttribute("enquiry", enquiry);

		// all the reject data we will access a list
		List<RejectEnquiry> reject = trackerenquirydao.getEnquiryReject();

		model.addAttribute("reject", reject);

		// all the approve details are comes to the list
		List<TrackerEnquiryConfig> approve = trackerenquirydao.getEnquiryApprove();

		model.addAttribute("approve", approve);

		// all the convert to rfp's details are comes to the list
		List<RejectRfp> converttorfo = trackerenquirydao.getConvertToRfp();

		model.addAttribute("converttorfo", converttorfo);

		// all the rfp's rejects are comes to and access the list
		List<RejectRfp> rfpreject = trackerenquirydao.getRfpReject();

		model.addAttribute("rfpreject", rfpreject);

		// all the rfp's approve comes to the list
		List<RejectRfp> rfpapprove = trackerenquirydao.getRfpApprove();

		model.addAttribute("rfpapprove", rfpapprove);

		// all the rfp's completed are comes to the list
		List<TrackerRfpConfig> rfpcompleted = trackerenquirydao.getCompleted();

		// this model we can access the user interface access the addAttribute and getAttributes
		model.addAttribute("rfpcompleted", rfpcompleted);

		// The List of data will be set to the Data Transfer Object we will access the data in a service Class
		mainmodelform.setTrackerenquiry(enquiry);
		mainmodelform.setRejectenjuiry(reject);
		mainmodelform.setApproveenquiry(approve);
		mainmodelform.setConvertrfp(converttorfo);
		mainmodelform.setRejectrfp(rfpreject);
		mainmodelform.setRfpapproive(rfpapprove);
		mainmodelform.setRfpcompleted(rfpcompleted);
		model.addAttribute("viewmodel", mainmodelform);

		return "pipeline";

	}
	// Request will be goes to the service class this method will be return the map access the data in a object

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public ResponseEntity<String> getForm(@RequestParam("id") String id, Model model) {

		// String data = mainservice.checkEnquiryStatus(Integer.parseInt(id.trim()));

		Map<String, Object> enquiryStatus = pipelineService.checkEnquiryStatu(Integer.parseInt(id.trim()));
		System.out.println(enquiryStatus);

		new Gson().toJson(enquiryStatus);

		return ResponseEntity.ok(new Gson().toJson(enquiryStatus));

	}

	/*
	 * // Handles pipeline by getting list of Status options
	 * 
	 * @RequestMapping(value = "pipeline") public String showPipeline(Model model) {
	 * 
	 * List<PipeLineOutputModel> rm = enquiryDao.getEnquiryStatusList();
	 * 
	 * model.addAttribute("myData", rm); return "pipeline";
	 * 
	 * }
	 */

}
