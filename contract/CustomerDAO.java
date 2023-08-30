package com.contract;

import java.util.List;

import com.models.Input.Customer;

public interface CustomerDAO {

	Integer insertCustomerRecordAndReturnCustId(Customer customer);

	// public int getCustomerCount();

	boolean updateCustomer(Customer customer);

	String getUserByDepartment(String username);

	boolean deleteCustomerById(Integer cust_id);

	// List<Customer> getAllcustomer(int page);

	List<Customer> getAllcustomer();

}