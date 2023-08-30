package com.mappers;

import java.sql.ResultSet;

import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.models.Output.RFPCategories;


public class CategoriesRowMapper implements RowMapper<RFPCategories> {

	@Override
	public RFPCategories mapRow(ResultSet rs, int rowNum) throws SQLException {
		RFPCategories rfpCategories = new RFPCategories();

		rfpCategories.setRfpcId(rs.getString("rfpc_id"));

		rfpCategories.setRfpcName(rs.getString("rfpc_name"));

		return rfpCategories;
	}

}