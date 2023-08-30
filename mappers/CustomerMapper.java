package com.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.models.Input.Customer;

public class CustomerMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer customer = new Customer();
		customer.setCustId(rs.getInt("cust_id"));
		customer.setCustType(rs.getString("cust_type"));
		customer.setCustName(rs.getString("cust_name"));
		customer.setCustCompany(rs.getString("cust_company"));
		customer.setCustAddress(rs.getString("cust_address"));
		customer.setCustEmail(rs.getString("cust_email"));
		customer.setCustLocation(rs.getString("cust_location"));
		customer.setCustMobile(rs.getString("cust_mobile"));
		customer.setCustWebsite(rs.getString("cust_website"));

		return customer;
	}

}
