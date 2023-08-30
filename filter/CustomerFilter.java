package com.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mappers.CustomerMapper;
import com.models.Input.Customer;

public class CustomerFilter {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Customer> filterCustomerData(String custId, String custType, String custName) {
		StringBuilder sqlBuilder = new StringBuilder(
				"SELECT * FROM tracker_customers WHERE cust_id not in (select cust_id from  delete_customers)");
		List<Object> parameters = new ArrayList<>();

		if (custId != null && !custId.isEmpty()) {
			sqlBuilder.append(" AND cust_id = ?");

			parameters.add(Integer.parseInt(custId));
		}

		if (custType != null && !custType.isEmpty()) {
			sqlBuilder.append(" AND cust_type = ?");
			parameters.add(custType);
		}

		if (custName != null && !custName.isEmpty()) {
			sqlBuilder.append(" AND cust_name LIKE ?");
			parameters.add("%" + custName + "%");
		}

		String sql = sqlBuilder.toString();
		return jdbcTemplate.query(sql, parameters.toArray(), new CustomerMapper());
	}
}