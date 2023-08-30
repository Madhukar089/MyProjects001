package com.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.contract.RFPDAO;
import com.mappers.Auth_usersRowMapper;
import com.mappers.CategoriesRowMapper;
import com.mappers.RFPDRowMapper;
import com.mappers.RFPDocumentCategoryMapper;
import com.mappers.RFPMapper;
import com.mappers.RFPRDocumentRowMapper;
import com.models.DTO.RFPDTO;
import com.models.DTO.RFPDocumentDTO;
import com.models.DTO.RFPRDocumentDTO;
import com.models.Output.Auth_users;
import com.models.Output.RFPCategories;
import com.models.Output.RFPDocumentCategory;
import com.models.Output.RFPDocumentTypes;
import com.models.Output.TrackerRFP;

public class RFPDaoImpl implements RFPDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;

	private static Logger LOGGER = Logger.getLogger(RFPDaoImpl.class);

	@Override
	public int insertRFP(RFPDTO RfpDto) {
		if (RfpDto == null) {
			return 0;
		}

		// Insert a new RFP (Request for Proposal) into the tracker_rfp table
		String sql = "INSERT INTO tracker_rfp (rfpr_enqr_id, rfpr_assignedto, rfpr_subject, rfpr_intro_note, rpfr_rfpc_id, rfpr_created_ausr_id, rfpr_status) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING rfpr_enqr_id";
		Object[] params = { RfpDto.getRfprEnqrId(), RfpDto.getAssigned_to(), RfpDto.getRfprSubject(),
				RfpDto.getRfprIntroNote(), RfpDto.getRpfrRfpcId(), RfpDto.getRfprCreatedAusrId(),
				RfpDto.getRfprStatus() };
		int rfpr_enqr_id = jdbcTemplate.queryForObject(sql, params, Integer.class);

		// Update the status of the associated enquiry to "ENQR_COMPLETED"
		String updateEnquiryStatus = "UPDATE tracker_enquiries SET enqr_status = 'ENQR_ARCHIEVED' WHERE enqr_id = ?";
		Object[] enquiryParams = { rfpr_enqr_id };

		LOGGER.info("RFP inserted successfully");
		return jdbcTemplate.update(updateEnquiryStatus, enquiryParams);
	}

	@Override
	public List<String> assignRFPToUser() {
		// Retrieve a list of user IDs from tracker_auth_users table for users in the "technical team" department
		String sql = "SELECT ausr_id FROM tracker_auth_users WHERE department = 'technical team'";
		List<String> assignedUsers = jdbcTemplate.queryForList(sql, String.class);

		LOGGER.info("Assigned RFPs to users in the technical team");
		return assignedUsers;
	}

	@Override
	public List<RFPCategories> getCategories() {
		// Retrieve a list of RFP categories from tracker_rfp_categories table
		String sql = "SELECT * FROM tracker_rfp_categories";
		List<RFPCategories> categories = jdbcTemplate.query(sql, new CategoriesRowMapper());

		LOGGER.info("Retrieved RFP categories");
		return categories;
	}

	@Override
	public List<TrackerRFP> getRFPByStatus(String user) {
		// Retrieve a list of RFPs (Request for Proposals) from tracker_rfp table based on the assigned user and status
		String sql = "SELECT * FROM tracker_rfp WHERE rfpr_assignedto = ? AND (rfpr_status ='RFPR_PENDING' OR rfpr_status ='RFPR_COMPLETED') "
				+ "AND rfpr_id NOT IN (SELECT rej_rfpr_id FROM rejected_rfps)";
		Object[] params = { user };
		List<TrackerRFP> rfpList = jdbcTemplate.query(sql, params, new RFPMapper());

		LOGGER.info("Retrieved RFPs by status for user: " + user);
		return rfpList;
	}

	@Override
	public List<TrackerRFP> getAllRFPs() {
		// Retrieve a list of all RFPs (Request for Proposals) from tracker_rfp table
		String sql = "SELECT * FROM tracker_rfp WHERE rfpr_id NOT IN (SELECT rej_rfpr_id FROM rejected_rfps)";
		List<TrackerRFP> rfpList = jdbcTemplate.query(sql, new RFPMapper());

		LOGGER.info("Retrieved all RFPs");
		return rfpList;
	}

	@Override
	public TrackerRFP getRFPById(int rfprId) {
		// Retrieve an RFP (Request for Proposal) from tracker_rfp table by its ID
		String sql = "SELECT * FROM tracker_rfp WHERE rfpr_id = ?";
		Object[] params = { rfprId };
		TrackerRFP rfp = jdbcTemplate.queryForObject(sql, params, new RFPMapper());

		LOGGER.info("Retrieved RFP by ID: " + rfprId);
		return rfp;
	}

	@Override
	public TrackerRFP getRFPByenqrId(int rfprId) {
		// Retrieve an RFP (Request for Proposal) from tracker_rfp table by its ID
		String sql = "SELECT * FROM tracker_rfp WHERE rfpr_enqr_id = ?";
		Object[] params = { rfprId };
		TrackerRFP rfp = jdbcTemplate.queryForObject(sql, params, new RFPMapper());

		LOGGER.info("Retrieved RFP by ID: " + rfprId);
		return rfp;
	}

	@Override
	public boolean updateEnquiryStatus(Integer rfprId, String status) {
		// Update the status of an RFP (Request for Proposal) in tracker_rfp table
		if (status.startsWith("RFPR_")) {
			String sql = "UPDATE tracker_rfp SET rfpr_status = ? WHERE rfpr_id = ?";
			Object[] params = { status, rfprId };
			int rowsAffected = jdbcTemplate.update(sql, params);
			if (rowsAffected > 0) {
				LOGGER.info("Updated RFP status for ID: " + rfprId + ", Status: " + status);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean rejectByEnquiryId(int rfprId) {
		// Insert an RFP ID into the rejected_rfps table
		String sql = "INSERT INTO rejected_rfps (rfpr_id) VALUES (?)";
		int rowsAffected = jdbcTemplate.update(sql, rfprId);

		LOGGER.info("Rejected RFP with ID: " + rfprId);
		return rowsAffected == 1;
	}

	@Override
	public List<RFPDocumentTypes> getRFPDocumentTypes() {
		// Retrieve a list of RFP document types from rfpd_types table
		String sql = "SELECT * FROM rfpd_types";
		List<RFPDocumentTypes> documentTypes = jdbcTemplate.query(sql, new RFPDRowMapper());

		LOGGER.info("Retrieved RFP document types");
		return documentTypes;
	}

	@Override
	public List<RFPDocumentCategory> getRFPDocumentCategories() {
		// Retrieve a list of RFP document categories from rfpd_categories table
		String sql = "SELECT * FROM rfpd_categories";
		List<RFPDocumentCategory> documentCategories = jdbcTemplate.query(sql, new RFPDocumentCategoryMapper());

		LOGGER.info("Retrieved RFP document categories");
		return documentCategories;
	}

	@Override
	public List<Auth_users> assignRFPtoManager() {
		// Retrieve a list of users from tracker_auth_users table in the "review managers" department
		String sql = "SELECT * FROM tracker_auth_users WHERE department = 'review managers' ";
		List<Auth_users> users = jdbcTemplate.query(sql, new Auth_usersRowMapper());

		LOGGER.info("Retrieved users assigned as RFP managers");
		return users;
	}

	@Override
	public List<TrackerRFP> getRFPsByUser(String loginUser) {
		// Retrieve a list of RFPs (Request for Proposals) from tracker_rfp table assigned to a specific user with
		// "RFPR_INPROGRESS" status
		String sql = "SELECT * FROM tracker_rfp WHERE rfpr_assignedto = ? AND rfpr_status = 'RFPR_INPROGRESS'";
		List<TrackerRFP> rfpList = jdbcTemplate.query(sql, new Object[] { loginUser }, new RFPMapper());

		LOGGER.info("Retrieved RFPs assigned to user: " + loginUser);
		return rfpList;
	}

	@Override
	public List<TrackerRFP> getOpenRFPs(String loginUser) {
		// Retrieve a list of open RFPs (Request for Proposals) from tracker_rfp table assigned to a specific user with
		// "RFPR_PENDING" or "RFPR_INPROGRESS" status
		String sql = "SELECT * FROM tracker_rfp WHERE rfpr_assignedto = ? AND rfpr_status IN ('RFPR_PENDING', 'RFPR_INPROGRESS') AND rfpr_id NOT IN (SELECT rej_rfpr_id FROM rejected_rfps)";
		List<TrackerRFP> rfpList = jdbcTemplate.query(sql, new Object[] { loginUser }, new RFPMapper());

		LOGGER.info("Retrieved open RFPs assigned to user: " + loginUser);
		return rfpList;
	}

	@Override
	public List<RFPRDocumentDTO> getRFPsForReview(String user) {
		// Retrieve a list of RFP documents from tracker_rfpr_documents table for review by a specific user
		String sql = "SELECT * FROM tracker_rfpr_documents WHERE rpdf_status = 'Pending'  and rfpd_prepareddate IN ("
				+ "SELECT MAX(rfpd_prepareddate) FROM ("
				+ "SELECT * FROM tracker_rfpr_documents WHERE rpfd_reviewedby = ? AND rfpr_id IN ("
				+ "SELECT DISTINCT(rfpr_id) FROM tracker_rfpr_documents GROUP BY rfpr_id) ORDER BY rfpr_id DESC) p "
				+ "GROUP BY rfpr_id)";
		List<RFPRDocumentDTO> rfpDocuments = jdbcTemplate.query(sql, new Object[] { user },
				new RFPRDocumentRowMapper());

		LOGGER.info("Retrieved RFP documents for review by user: " + user);
		return rfpDocuments;
	}

	@Override
	public RFPRDocumentDTO getRFPDocumentDetails(Integer rfpdId) {
		// Retrieve details of an RFP document from tracker_rfpr_documents table by its ID
		String sql = "SELECT * FROM tracker_rfpr_documents WHERE rfpd_id=?";
		RFPRDocumentDTO rfpDocument = jdbcTemplate.queryForObject(sql, new Object[] { rfpdId },
				new RFPRDocumentRowMapper());

		LOGGER.info("Retrieved details of RFP document with ID: " + rfpdId);
		return rfpDocument;
	}

	@Override
	public int insertTrackerDocument(RFPDocumentDTO rfpDocument, String path) {

		if (rfpDocument == null || path == null) {
			return 0;
		}
		// Insert a new RFP document into tracker_rfpr_documents table
		String sql = "INSERT INTO tracker_rfpr_documents (rfpr_id, rfpd_type, rfpd_doccategory, rfpd_documentpath, rpfd_reviewedby, rfpd_desc, rpdf_status) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		Object[] params = { rfpDocument.getRfprId(), rfpDocument.getRfpdType(), rfpDocument.getRfprDocCategory(), path,
				rfpDocument.getRfpdReviewedBy(), rfpDocument.getRfpdDesc(), rfpDocument.getRpdfStatus() };
		jdbcTemplate.update(sql, params);

		LOGGER.info("Inserted a new RFP document into tracker_rfpr_documents table");

		String sql2 = "UPDATE tracker_rfp SET rfpr_status = 'RFPR_PENDING' WHERE rfpr_id = ?";
		Object[] param = { rfpDocument.getRfprId() };
		LOGGER.info("Updated RFP status to 'RFPR_PENDING' for RFP with ID: " + rfpDocument.getRfprId());

		return jdbcTemplate.update(sql2, param);

	}

	@Override
	public boolean rejectRFPDocumentById(int rfpdId) {
		String sql = "UPDATE tracker_rfpr_documents SET rpdf_status = 'Rejected' WHERE rfpd_id = ?";
		Object[] param = { rfpdId };
		int rowsAffected = jdbcTemplate.update(sql, param);
		if (rowsAffected > 0) {
			LOGGER.info("Rejected RFP document with ID: " + rfpdId);
			return true;
		}
		LOGGER.error("RFP document not rejecetd with ID: " + rfpdId);
		return false;
	}

	@Override
	public boolean approveRFPDocumentById(int rfpdId) {
		String sql = "UPDATE tracker_rfpr_documents SET rpdf_status = 'approved', rfpd_sharedstatus = 'SHRD' WHERE rfpr_id = ?";
		Object[] param = { rfpdId };
		String updateRFPtoComplete = "update tracker_rfp set rfpr_status = 'RFPR_COMPLETED' where rfpr_id = ?";
		Object[] params = { rfpdId };
		int rowsAffected = jdbcTemplate.update(sql, param);
		int status = jdbcTemplate.update(updateRFPtoComplete, params);
		if (rowsAffected > 0) {
			LOGGER.info("Approved RFP document with ID: " + rfpdId);
			return true;
		}
		if (status > 0) {
			LOGGER.info("Approved RFP document and the RFP is RPR_INPROGRESS " + rfpdId);
			return true;
		}
		LOGGER.error("RFP document not approved with ID: " + rfpdId);
		return false;
	}

	@Override
	public List<TrackerRFP> getRejecetdRFPs(String loginUser) {
		// Retrieve a list of rejected RFPs (Request for Proposals) from tracker_rfp table assigned to a specific user
		String sql = "SELECT * FROM tracker_rfp WHERE rfpr_assignedto = ? and rfpr_id in (select rej_rfpr_id from rejected_rfps)";
		List<TrackerRFP> rfpList = jdbcTemplate.query(sql, new Object[] { loginUser }, new RFPMapper());

		LOGGER.info("Retrieved rejected RFPs assigned to user: " + loginUser);
		return rfpList;
	}

}