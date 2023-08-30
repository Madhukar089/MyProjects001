package com.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.contract.CustomerDAO;
import com.mappers.CustomerMapper;
import com.models.Entity.TrackerCustomer;
import com.models.Input.Customer;

@Transactional
public class CustomerDaoImpl implements CustomerDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static Logger LOGGER = Logger.getLogger(CustomerDaoImpl.class);

	@PersistenceContext
	EntityManager em;

	@Autowired
	TrackerCustomer trackerCustomer;

	@Autowired
	ModelMapper modelMapper;

	private final String customer_list = "select * from tracker_customers where cust_id not in (select cust_id from  delete_customers)";
	// private final String customerCount = "select count(*) from tracker_customers where cust_id not in (select cust_id
	// from delete_customers)";

	private final String login_user_dept = "select department from tracker_auth_users  where ausr_id=?";
	private final String cust_delete = "insert  into  delete_customers(cust_id) values (?) ";
	private final String cust_update = "update tracker_customers set cust_type=?,cust_name=?, cust_company =?,cust_address=?,cust_location=?,cust_mobile=?,cust_email=?,cust_website=? where cust_id=?";

	@Override
	public Integer insertCustomerRecordAndReturnCustId(Customer customer) {
		// Map Customer object to TrackerCustomer entity
		trackerCustomer = modelMapper.map(customer, TrackerCustomer.class);

		// Persist the TrackerCustomer entity in the EntityManager
		em.persist(trackerCustomer);

		// Log the customer insertion
		LOGGER.info("Inserted customer record with ID: " + customer.getCustId());

		return trackerCustomer.getCustId();
	}

	@Override
	public boolean deleteCustomerById(Integer custId) {
		// Execute an update query to insert the customer ID into delete_customers table
		int rowsAffected = jdbcTemplate.update(cust_delete, custId);

		// Log the deletion status
		if (rowsAffected > 0) {
			LOGGER.info("Deleted customer with ID: " + custId);
			return true;
		} else {
			LOGGER.error("No customer found with ID: " + custId);
			return false;
		}
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		int rowsAffected = jdbcTemplate.update(cust_update, customer.getCustType(), customer.getCustName(),
				customer.getCustCompany(), customer.getCustAddress(), customer.getCustLocation(),
				new BigInteger(customer.getCustMobile()), customer.getCustEmail(), customer.getCustWebsite(),
				customer.getCustId());

		// Log the update status
		if (rowsAffected > 0) {
			LOGGER.info("Updated customer record with ID: " + customer.getCustId());
			return true;
		} else {
			LOGGER.error("No customer found with ID: " + customer.getCustId());
			return false;
		}
	}

	@Override
	public String getUserByDepartment(String username) {
		Object[] params = { username };

		// Execute a query to retrieve the department of a user based on their username
		String userType = jdbcTemplate.queryForObject(login_user_dept, params, String.class);

		// Log the retrieved user department
		LOGGER.info("Retrieved user department - " + userType + " for username " + username);

		return userType;
	}

	@Override
	public List<Customer> getAllcustomer() {
		// Retrieve all customers using JDBC template and CustomerMapper
		List<Customer> customers = jdbcTemplate.query(customer_list, new CustomerMapper());

		// Log the number of retrieved customers
		LOGGER.info("Retrieved {} customer(s)" + customers.size());

		return customers;
	}
	/*
	 * @Override public int getCustomerCount() { int count = jdbcTemplate.queryForObject(customerCount, Integer.class);
	 * return count; }
	 */

}
