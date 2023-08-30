package com.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.contract.CustomerDAO;
import com.models.Input.Customer;

@Controller
public class CustomerCRUD {

	private CustomerDAO customerDao;

	private static Logger LOGGER = Logger.getLogger(CustomerCRUD.class);

	@Autowired
	public CustomerCRUD(CustomerDAO customerDao) {
		this.customerDao = customerDao;
	}

	// Handles the deletion of a customer by their ID
	@RequestMapping(value = "delete")
	@ResponseBody
	public String deletecustomer(@RequestParam("cust_id") Integer custId, Model model) {
		Boolean status = customerDao.deleteCustomerById(custId);

		if (status) {
			// customer details are deleted for the customer Id
			LOGGER.info("customer details are deleted for the customer Id: " + custId);

			return "deleted";
		} else {
			// customer details are not deleted for the customer Id
			LOGGER.error("customer details are not deleted for the customer Id: " + custId);
			model.addAttribute("failedmessage", "Failed to delete the customer details");

			return "Failed";
		}
	}

	// Handles the update of customer details
	@RequestMapping(value = "update")
	public String updateCustomer(Model model, @ModelAttribute("customer") Customer customer) {
		// Update the customer and check if it was successful
		boolean isUpdated = customerDao.updateCustomer(customer);
		LOGGER.debug(" checking whether customer details are updated or not");
		LOGGER.info("customer details updated status is " + isUpdated);
		// Retrieve all customers
		List<Customer> customers = customerDao.getAllcustomer();
		/*
		 * int totalCustomer = customerDao.getCustomerCount(); int totalPages = (int) Math.ceil(totalCustomer / (double)
		 * 10);
		 */
		// Adding the customer list to the model for rendering in the view
		model.addAttribute("customer", customers);
		/*
		 * model.addAttribute("totalPages", totalPages); model.addAttribute("currentPage", page);
		 */
		LOGGER.debug("Getting all the customers list with updated details");

		if (isUpdated) {
			// customer details are updated
			LOGGER.info("customer details are updated for the customer Id: " + customer.getCustId());
			return "customer";
		}
		// customer details are not updated
		LOGGER.error("customer details are not updated for the customer Id: " + customer.getCustId());
		model.addAttribute("failedmessage", "Failed to update the customer details");
		return "Failed";
	}
}
