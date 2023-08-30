package com.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.contract.DocumentContract;
import com.contract.ObjectMapperContract;
import com.contract.RFPDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.models.DTO.RFPDocumentDTO;
import com.models.Output.Auth_users;
import com.models.Output.RFPCategories;
import com.models.Output.RFPDocumentCategory;
import com.models.Output.RFPDocumentTypes;

public class RFPService {

	private RFPDAO RFPDao;

	@Autowired
	public RFPService(RFPDAO rFPDao) {

		RFPDao = rFPDao;

	}

	@Autowired
	private DocumentContract documentContract;
	@Autowired
	private ObjectMapperContract customObjectMapperWrapper;
	@Autowired
	private MasterRFPprocessTracker masterRFPprocessTracker;

	public RFPService() {

	}

	// Handles RFP assigning to Review Managers
	public void assignRFPtoManager(Model model) {

		// Lists the Review Managers
		List<String> reviewManagers = new ArrayList<>();
		List<Auth_users> usersdata = new ArrayList<>();
		usersdata = RFPDao.assignRFPtoManager();

		for (Auth_users user : usersdata) {
			reviewManagers.add(user.getAusrId().trim());
		}
		model.addAttribute("reviewManagers", reviewManagers);

	}

	// To get the List of RFP Categories
	public void getCategories(Model model) {
		List<RFPCategories> rfpCategories = new ArrayList<>();

		rfpCategories = RFPDao.getCategories();

		Map<String, String> map = new HashMap<>();
		for (RFPCategories rfpCategory : rfpCategories) {

			map.put(rfpCategory.getRfpcId(), rfpCategory.getRfpcName());
		}
		model.addAttribute("rfpCategories", map);
	}

	// Lists the RFP Document Types
	public void getRFPDocumentTypes(Model model) {
		List<RFPDocumentTypes> RFPDTypes = new ArrayList<>();
		RFPDTypes = RFPDao.getRFPDocumentTypes();
		Map<String, String> map = new HashMap<>();
		for (RFPDocumentTypes rfpdTypes : RFPDTypes) {

			map.put(rfpdTypes.getRfpTypeId(), rfpdTypes.getRfpTypeName());
		}

		model.addAttribute("RFPDTypes", map);
	}

	// Lists the RFP Document Categories
	public void getRFPDocumentCategories(Model model) {

		List<RFPDocumentCategory> RfpdCategory = new ArrayList<>();
		RfpdCategory = RFPDao.getRFPDocumentCategories();
		Map<String, String> map = new HashMap<>();
		for (RFPDocumentCategory rfpdcategories : RfpdCategory) {

			map.put(rfpdcategories.getRfpdDocCategory(), rfpdcategories.getRfpdDescription());
		}
		model.addAttribute("RFPDCategories", map);
	}

	// Assigning RFP to Technical Team
	public void assignRFP(Model model) {
		// Lists the Technical Team for assigning RFP
		List<String> userData = new ArrayList<>();
		userData = RFPDao.assignRFPToUser();
		model.addAttribute("technicalTeam", userData);
	}

	public void getRFPMetaData(Model model) {
		// Retrieve necessary data for rendering the view
		getCategories(model);
		getRFPDocumentTypes(model);
		getRFPDocumentCategories(model);
		assignRFPtoManager(model);

	}

	public boolean extractAndUpdateFormDetails(MultipartHttpServletRequest request, HttpServletRequest req) {
		MultipartFile file = documentContract.extractFile(request);
		documentContract.uploadDocument(file, req);
		RFPDocumentDTO rfpModel = extractRFPDTOModel(request);

		String docName = file.getOriginalFilename();
		// Insert the RFP document into the database
		RFPDao.insertTrackerDocument(rfpModel, docName);

		masterRFPprocessTracker.updateRfprDetailsMaster(rfpModel.getRfprId(), rfpModel.getRfpdReviewedBy(),
				rfpModel.getRpdfStatus());

		return true;
	}

	public RFPDocumentDTO extractRFPDTOModel(MultipartHttpServletRequest request) {
		String dynamicFormModelJson = request.getParameter("RFPDocData");
		System.out.println(dynamicFormModelJson);
		RFPDocumentDTO dynamicFormModel = null;

		try {
			dynamicFormModel = customObjectMapperWrapper.readValue(dynamicFormModelJson, RFPDocumentDTO.class);
		} catch (JsonProcessingException e) {

		}

		return dynamicFormModel;
	}

}