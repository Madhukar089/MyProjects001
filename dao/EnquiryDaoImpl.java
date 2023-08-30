package com.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.contract.EnquiryDAO;
import com.mappers.Auth_usersRowMapper;
import com.mappers.ClosedAccountMapper;
import com.mappers.EnquiryDTOMapper;
import com.mappers.RejectionMailMapper;
import com.mappers.TrackerDocumnetsRowMapper;
import com.mappers.TrackerEnquiryRowMapper;
import com.models.DTO.EnquiryDTO;
import com.models.DTO.RejectionMailDTO;
import com.models.DTO.ReviewApproveDTO;
import com.models.Input.Customer;
import com.models.Output.Auth_users;
import com.models.Output.ClosedAccountsModel;
import com.models.Output.PipeLineOutputModel;
import com.models.Output.TrackerDocument;
import com.models.Output.TrackerEnquiry;

@Transactional
public class EnquiryDaoImpl implements EnquiryDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	private static Logger LOGGER = Logger.getLogger(EnquiryDaoImpl.class);

	@PersistenceContext
	EntityManager em;

	@Override
	public Integer insertEnquiryRecordAndReturnEnqrId(Customer customer, Integer cust_id) {
		if (customer == null) {
			return 0;
		}
		// Insert a new enquiry record into the tracker_enquiries table and return the generated enqr_id
		String sql = "INSERT INTO tracker_enquiries (enqr_createdby, enqr_cust_id, enqr_subject, enqr_desc) VALUES (?,?,?,?) returning enqr_id";
		Object[] params = { customer.getCustName(), cust_id, customer.getSub(), customer.getDesc() };
		Integer enqrId = jdbcTemplate.queryForObject(sql, params, Integer.class);

		// Log the inserted enquiry record ID
		LOGGER.info("Inserted enquiry record with ID: " + enqrId);

		return enqrId;
	}

	@Override
	public int insertEnquiryDocument(String description, Integer enqr_id, String documentName) {

		// Insert a new enquiry document into the tracker_enquiries_documents table
		String sql = "INSERT INTO tracker_enquiries_documents (enqr_id, enqd_documentpath, enqd_desc) VALUES (?,?,?) ";
		Object[] params = { enqr_id, documentName, description };
		int rowsAffected = jdbcTemplate.update(sql, params);
		LOGGER.info("Inserted " + rowsAffected + " enquiry document(s) for enquiry ID: " + enqr_id);
		return 1;
		// Log the number of inserted enquiry documents
	}

	@Override
	public EnquiryDTO getEnquiryDetails(Integer enqr_id) {
		// Retrieve the details of an enquiry along with the associated customer information and documents
		String sql = "SELECT e.enqr_id, e.enqr_createdby, e.enqr_cust_id, e.enqr_subject, e.enqr_desc, e.enqr_status, e.enqr_assigned_to, tc.cust_email, tc.cust_type, tc.cust_website "
				+ "FROM tracker_enquiries e " + "JOIN tracker_enquiries_documents d ON e.enqr_id = d.enqr_id "
				+ "JOIN tracker_customers tc ON tc.cust_id = e.enqr_cust_id " + "WHERE e.enqr_id = ? limit 1";
		return jdbcTemplate.queryForObject(sql, new Object[] { enqr_id }, new EnquiryDTOMapper());
	}

	public List<TrackerEnquiry> getAllEnquiries() {
		// Retrieve all enquiries excluding the rejected ones
		String sql = "SELECT * FROM tracker_enquiries WHERE enqr_id NOT IN (SELECT e_id FROM rejectedenquiries)";
		List<TrackerEnquiry> enquiries = jdbcTemplate.query(sql, new TrackerEnquiryRowMapper());

		// Log the number of retrieved enquiries
		LOGGER.info("Retrieved enquiry(s) count" + enquiries.size());

		return enquiries;
	}

	@Override
	public List<TrackerEnquiry> getOpenEnquiries(String loginUser) {
		// Retrieve all open enquiries (not completed) excluding the rejected ones
		String sql = "SELECT * FROM tracker_enquiries WHERE enqr_assigned_to = ? and  enqr_status <> 'ENQR_COMPLETED' AND enqr_id NOT IN (SELECT e_id FROM rejectedenquiries)";
		List<TrackerEnquiry> openEnquiries = jdbcTemplate.query(sql, new Object[] { loginUser },
				new TrackerEnquiryRowMapper());

		// Log the number of retrieved open enquiries
		LOGGER.info("Retrieved " + openEnquiries.size() + " open enquiry(s) for user: " + loginUser);

		return openEnquiries;
	}

	@Override
	public boolean rejectByEnquiryId(Integer enqrId, String Desc) {
		// Insert an enquiry ID into the rejectedEnquiries table to mark it as rejected
		String sql = "INSERT INTO rejectedenquiries (e_id,rej_desc) VALUES (?,?)";
		int rowsAffected = jdbcTemplate.update(sql, new Object[] { enqrId, Desc });

		// Log the rejection status
		if (rowsAffected == 1) {
			LOGGER.info("Enquiry rejected for ID  " + enqrId);
			return true;
		} else {
			LOGGER.info("Failed to reject enquiry with ID: " + enqrId);
			return false;
		}
	}

	@Override
	public RejectionMailDTO rejectionMail(int enqrId) {
		String sql = "select cust_id,cust_email from tracker_customers where cust_id =(select enqr_cust_id from tracker_enquiries where enqr_id=?)";
		return jdbcTemplate.queryForObject(sql, new Object[] { enqrId }, new RejectionMailMapper());

	}

	@Override
	public boolean updateEnquiryStatus(Integer enqrId, String status) {
		// Update the status of an enquiry in the tracker_enquiries table
		String sql = "UPDATE tracker_enquiries SET enqr_status = ? WHERE enqr_id = ?";
		int rowsAffected = jdbcTemplate.update(sql, status, enqrId);

		// Log the update status
		if (rowsAffected > 0) {
			LOGGER.info("Updated enquiry status is " + status + " for ID : " + enqrId);
			return true;
		} else {
			LOGGER.error("No enquiry found with ID: " + enqrId);
			return false;
		}
	}

	@Override
	public List<Auth_users> assignEnquirytoPresales() {
		// Retrieve all users from the tracker_auth_users table who belong to the 'presales' department
		String sql = "SELECT * FROM tracker_auth_users WHERE department='presales'";
		List<Auth_users> presalesUsers = jdbcTemplate.query(sql, new Auth_usersRowMapper());

		// Log the number of retrieved presales users
		LOGGER.info("Retrieved " + presalesUsers.size() + " presales user(s)");

		return presalesUsers;
	}

	@Override
	public boolean EnquiryAssignedto(Integer enqr_id, String user) {
		// Assign an enquiry to a user by updating the enqr_assigned_to field in the tracker_enquiries table
		user = user.trim();
		String sql = "UPDATE tracker_enquiries SET enqr_assigned_to = ? WHERE enqr_id = ? returning enqr_assigned_to";
		String assignedTo = jdbcTemplate.queryForObject(sql, new Object[] { user, enqr_id }, String.class);

		// Log the assigned user
		if (assignedTo != null) {
			LOGGER.info("Enquiry with ID " + enqr_id + " assigned to user: " + assignedTo);
			return true;
		} else {
			LOGGER.error("Failed to assign enquiry with ID: " + enqr_id);
			return false;
		}
	}

	@Override
	public List<TrackerEnquiry> getEnquiriesByUser(String loginUser) {
		// Retrieve all enquiries assigned to a specific user
		String sql = "SELECT * FROM tracker_enquiries WHERE enqr_assigned_to = ? and enqr_id NOT IN (SELECT e_id FROM rejectedenquiries) and  enqr_id not in (select rfpr_enqr_id from tracker_rfp)";
		List<TrackerEnquiry> userEnquiries = jdbcTemplate.query(sql, new Object[] { loginUser },
				new TrackerEnquiryRowMapper());

		// Log the number of retrieved user enquiries
		LOGGER.info("Retrieved " + userEnquiries.size() + " enquiry(s) for user: " + loginUser);

		return userEnquiries;
	}

	@Override
	public List<TrackerDocument> getAllDocumnets(Integer enqr_id) {
		String sql = "select * from tracker_enquiries_documents where enqr_id = ?";
		List<TrackerDocument> documents = jdbcTemplate.query(sql, new Object[] { enqr_id },
				new TrackerDocumnetsRowMapper());

		// Log the number of retrieved documents
		LOGGER.info("Retrieved " + documents.size() + " document(s) for enquiry ID: " + enqr_id);

		return documents;
	}

	@Override
	public List<ClosedAccountsModel> getClosedAccounts() {
		// Retrieve closed accounts from the tracker_CompleteStatus table
		String sql = "select * from closedAccountsView order by enqr_id desc";
		List<ClosedAccountsModel> closedAccounts = jdbcTemplate.query(sql, new ClosedAccountMapper());

		// Log the number of retrieved closed accounts
		LOGGER.info("Retrieved " + closedAccounts.size() + " closed account(s)");

		return closedAccounts;
	}

	@Override
	public String getCustomerEmail(ReviewApproveDTO reviewApproveDTO) {
		// Retrieve the customer email associated with a specific RFP
		String getRfpCustomerEmail = "SELECT cust_email FROM tracker_customers WHERE cust_id = (SELECT enqr_cust_id FROM tracker_enquiries WHERE enqr_id = (SELECT rfpr_enqr_id FROM tracker_rfp WHERE rfpr_id = ?))";
		String email = jdbcTemplate.queryForObject(getRfpCustomerEmail, String.class, reviewApproveDTO.getRfpId());

		// Log the retrieved customer email
		LOGGER.info("Retrieved customer email: " + email);

		return email;
	}

	@Override
	public List<PipeLineOutputModel> getEnquiryStatusList() {
		List<PipeLineOutputModel> enquiryStatusList = new ArrayList<>();
		String enquiryQuery = "SELECT enqr_id, enqr_status FROM tracker_enquiries";
		try {
			List<Map<String, Object>> enquiryResults = jdbcTemplate.queryForList(enquiryQuery);
			for (Map<String, Object> enquiryResult : enquiryResults) {
				int enquiryId = (int) enquiryResult.get("enqr_id");
				String status = (String) enquiryResult.get("enqr_status");
				String rfpStatus = null;
				String rfpQuery = "SELECT rfpr_status FROM tracker_rfp WHERE rfpr_enqr_id = ?";
				try {
					rfpStatus = jdbcTemplate.queryForObject(rfpQuery, String.class, enquiryId);
				} catch (EmptyResultDataAccessException e) {
					rfpStatus = null;
				} catch (Exception e) {
					LOGGER.error("Error occurred while querying RFP status for enquiry ID: " + enquiryId, e);
				}
				String finalStatus = (rfpStatus != null) ? rfpStatus : status;
				PipeLineOutputModel enquiryStatus = new PipeLineOutputModel(enquiryId, finalStatus);
				enquiryStatusList.add(enquiryStatus);
			}
		} catch (Exception e) {
			LOGGER.error("Error occurred while retrieving enquiry statuses", e);
		}

		// Log the number of retrieved enquiry statuses
		LOGGER.info("Retrieved " + enquiryStatusList.size() + " enquiry status(es)");

		return enquiryStatusList;
	}

	@Override
	public List<TrackerEnquiry> getRejectedEnquiries(String loginUser) {
		String sql = "SELECT * FROM tracker_enquiries WHERE enqr_assigned_to = ? and  enqr_id IN (SELECT e_id FROM rejectedenquiries) ";
		List<TrackerEnquiry> rejectedEnquiries = jdbcTemplate.query(sql, new Object[] { loginUser },
				new TrackerEnquiryRowMapper());

		// Log the number of retrieved rejected enquiries
		LOGGER.info("Retrieved " + rejectedEnquiries.size() + " rejected enquiry(s) for user: " + loginUser);

		return rejectedEnquiries;
	}

}
