package com.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mappers.RFPRDocumentRowMapper;
import com.models.DTO.RFPRDocumentDTO;

public class RFPDocumentFilter {

	@Autowired
	JdbcTemplate jdbcTemplate;;

	public List<RFPRDocumentDTO> filterRFPDocumentData(String rfpdId, String rfpdSharedStatus, String rfpdReviewedBy,
			String user) {
		StringBuilder sqlBuilder = new StringBuilder(
				"SELECT * FROM tracker_rfpr_documents WHERE rpdf_status = 'Pending'");

		List<Object> parameters = new ArrayList<>();

		if (rfpdId != null && !rfpdId.isEmpty()) {
			sqlBuilder.append(" AND rfpd_id = ? ");
			parameters.add(Integer.parseInt(rfpdId));
		}

		if (rfpdSharedStatus != null && !rfpdSharedStatus.isEmpty()) {
			sqlBuilder.append(" AND rfpd_sharedstatus = ? ");
			parameters.add(rfpdSharedStatus);
		}

		if (rfpdReviewedBy != null && !rfpdReviewedBy.isEmpty()) {
			sqlBuilder.append(" AND rpfd_reviewedby LIKE ? ");
			parameters.add("%" + rfpdReviewedBy + "%");
		}
		parameters.add(user);

		String sql = sqlBuilder.toString();
		String sql2 = " and rfpd_prepareddate IN (" + "SELECT MAX(rfpd_prepareddate) FROM ("
				+ "SELECT * FROM tracker_rfpr_documents WHERE rpfd_reviewedby = ? AND rfpr_id IN ("
				+ "SELECT DISTINCT(rfpr_id) FROM tracker_rfpr_documents GROUP BY rfpr_id) ORDER BY rfpr_id DESC) p "
				+ "GROUP BY rfpr_id)";
		String finalsql = sql + sql2;
		System.out.println("--------------------====================-----------------------");
		System.out.println(parameters);

		return jdbcTemplate.query(finalsql, parameters.toArray(), new RFPRDocumentRowMapper());
	}

}