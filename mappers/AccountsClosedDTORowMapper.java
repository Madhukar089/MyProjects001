package com.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.models.DTO.AccountsClosedDTO;

public class AccountsClosedDTORowMapper implements RowMapper<AccountsClosedDTO> {

	@Override
	public AccountsClosedDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		AccountsClosedDTO accountsClosedDTO = new AccountsClosedDTO();

		// Set the values from the ResultSet to the AccountsClosedDTO object
		accountsClosedDTO.setCustId(rs.getInt("cust_id"));
		accountsClosedDTO.setCustomerName(rs.getString("cust_name"));
		accountsClosedDTO.setEnqrId(rs.getInt("enqr_id"));
		accountsClosedDTO.setRfprId(rs.getInt("rfpr_id"));
		accountsClosedDTO.setAccountClosedBy(rs.getString("rfpr_l"));

		return accountsClosedDTO;
	}
}
