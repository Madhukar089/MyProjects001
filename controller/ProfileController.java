package com.controller;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.contract.ProfileDAO;
import com.models.Input.ProfileInput;
import com.models.Output.ProfileOutput;

@Controller

public class ProfileController {

	private ProfileDAO profileDao;

	private static Logger LOGGER = Logger.getLogger(ProfileController.class);

	@Autowired
	public ProfileController(ProfileDAO profileDao) {

		this.profileDao = profileDao;

	}

	@RequestMapping(value = "userprofile", method = RequestMethod.GET)
	public String editProfile(@RequestParam("username") String loginuser, Model model) {
		// Logging statement to indicate the start of the method
		LOGGER.info("Editing profile for user: " + loginuser);

		// Getting the profile data for the user from profileDao
		ProfileOutput user = profileDao.getProfileByUser(loginuser);

		// Adding the user profile to the model
		model.addAttribute("user", user);

		// Logging statement to indicate the successful retrieval of the user profile
		LOGGER.info("Retrieved profile data for user: " + loginuser);

		return "profile";
	}

	@RequestMapping(value = "updateprofile", method = RequestMethod.GET)
	public ResponseEntity<String> updateProfile(@ModelAttribute ProfileInput profileInput) throws IOException {
		// Logging statement to indicate the start of the method
		LOGGER.info("Updating profile for user: " + profileInput.getAusrId());

		// Updating the user profile using profileDao
		profileDao.updateProfile(profileInput);

		// Logging statement to indicate the successful update of the profile
		LOGGER.info("Profile updated successfully for user: " + profileInput.getAusrId());

		// Returning a ResponseEntity with a success message
		return ResponseEntity.ok("Profile Updated Successfully");
	}

}
