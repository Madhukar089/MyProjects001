package com.dao;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.contract.PipelineDAO;
import com.models.DTO.*;

public class PipelineDaoImpl implements PipelineDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	private final String sql = "select enqr_id, " + "enqr_status,rfpr_id,rfpr_status from tracker_enquiries"
			+ " te join tracker_rfp tr on te.enqr_id=tr.rfpr_enqr_id";

	@Override
	public List<PipelineDTO> getStatus() {
		return null;
	}

}
