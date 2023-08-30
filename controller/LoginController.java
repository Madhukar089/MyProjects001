package com.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.contract.CustomerDAO;
import com.models.Input.Customer;
import com.models.Input.LogginUser;

@Controller

@SessionAttributes({ "username", "userrole", "customer", "custcols", "loginuser" })
public class LoginController {

	private CustomerDAO customerDao;

	private static Logger LOGGER = Logger.getLogger(LoginController.class);

	@Autowired
	public LoginController(CustomerDAO customerDao) {

		this.customerDao = customerDao;
	}

	// Displays the login page
	@RequestMapping(value = "customlogin")
	public String getLogin() {
		// Logging statement to indicate the start of the method
		LOGGER.debug("Displaying the login page...");

		return "login";
	}

	@RequestMapping(value = "customerroute", method = RequestMethod.GET)
	public String showCustomerList(@RequestParam(name = "page", defaultValue = "1") int page, Model model) {

		// Logging statement to indicate the start of the method
		LOGGER.debug("Displaying the customer list...");

		int pageSize = 10; // Number of records to display per page
		List<Customer> candidates = customerDao.getAllcustomer();
		int totalCustomer = candidates.size();
		int totalPages = (int) Math.ceil(totalCustomer / (double) pageSize);

		// Calculate the start and end indexes for the current page
		int startIndex = (page - 1) * pageSize;
		int endIndex = Math.min(startIndex + pageSize, totalCustomer);

		List<Customer> customersonPage = candidates.subList(startIndex, endIndex);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);
		model.addAttribute("customer", customersonPage);

		return "customer";
	}

	// Verifies the admin login and redirects to the dashboard
	@RequestMapping(value = "dashboard")
	public String adminverify(@ModelAttribute LogginUser login, Model model) {
		// Logging statement to indicate the start of the method
		LOGGER.debug("Verifying admin login and redirecting to the dashboard...");

		// Adding the login username to the model
		model.addAttribute("loginuser", login.getUsername());

		return "dashboardlatest";
	}

	// Displays the create account page
	@RequestMapping("createAcc")
	public String createNewUser() {
		// Logging statement to indicate the start of the method
		LOGGER.debug("Displaying the create account page...");

		return "createAcc";
	}

	// Displays the profile page
	@RequestMapping("profile")
	public String profilepage() {
		// Logging statement to indicate the start of the method
		LOGGER.debug("Displaying the profile page...");

		return "profile";
	}

	// Logs out the user
	@GetMapping("adminlogout")
	public String logout() {
		// Logging statement to indicate the start of the method
		LOGGER.debug("Logging out the admin user...");
		return "login";
	}

}
