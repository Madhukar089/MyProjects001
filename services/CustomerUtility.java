package com.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;

import com.contract.CustomerUtilityInterface;
import com.contract.EnquiryDAO;
import com.mappers.AccountsClosedDTORowMapper;
import com.mappers.EnquiryDescMapper;
import com.models.DTO.AccountsClosedDTO;
import com.models.Input.EnquiryDocumentModel;
import com.models.Output.Auth_users;
import com.models.Output.TrackerDocument;

public class CustomerUtility implements CustomerUtilityInterface {

	private JdbcTemplate jdbcTemplate;

	private EnquiryDAO enquiryDao;

	public CustomerUtility() {
	}

	@Autowired
	public CustomerUtility(JdbcTemplate jdbcTemplate, EnquiryDAO enquiryDao) {
		this.jdbcTemplate = jdbcTemplate;
		this.enquiryDao = enquiryDao;
	}

	final String VALIDATE = "select cust_id from tracker_customers where cust_id =?";
	final String CUSTOMER_NAME = "select cust_name from tracker_customers where cust_id =?";
	final String INSERT_NEW_ENQUIRY = "insert into tracker_enquiries  (enqr_createdby, enqr_cust_id,enqr_subject,enqr_desc) values (?,?,?,?) returning enqr_id";
	final String ACC_CLOSED = "SELECT * from tracker_rfp where rfpr_status='RFPR_COMPLETED'";
	final String GET_CUSTOMER_ENQUIRIES = "select enqr_id,enqr_subject from tracker_enquiries where enqr_cust_id = ?";

	@Override
	public boolean validateCustomer(Integer cust_id) {

		if (jdbcTemplate.queryForObject(VALIDATE, new Object[] { cust_id }, Integer.class) > 0)
			return jdbcTemplate.queryForObject(VALIDATE, new Object[] { cust_id }, Integer.class) > 0;
		else
			return false;

	}

	@Override
	public String getCustomerName(Integer cust_id) {

		if (jdbcTemplate.queryForObject(CUSTOMER_NAME, new Object[] { cust_id }, String.class).length() > 0) {
			return jdbcTemplate.queryForObject(CUSTOMER_NAME, new Object[] { cust_id }, String.class);
		}
		return "Not valid cust_id";
	}

	@Override
	public Integer addNewEnquiry(EnquiryDocumentModel enquiryDocumentModel) {
		String customername = getCustomerName(enquiryDocumentModel.getCustId());
		Object[] params = { customername, enquiryDocumentModel.getCustId(), enquiryDocumentModel.getSubject(),
				enquiryDocumentModel.getDesc() };

		return jdbcTemplate.queryForObject(INSERT_NEW_ENQUIRY, params, Integer.class);
	}

	@Override
	public List<AccountsClosedDTO> getAccountsClosed() {

		return jdbcTemplate.query(ACC_CLOSED, new AccountsClosedDTORowMapper());

	}

	@Override
	public Map<String, Integer> getEnquiryID(Integer cust_id) {
		List<EnquiryDocumentModel> obj = jdbcTemplate.query(GET_CUSTOMER_ENQUIRIES, new Object[] { cust_id },
				new EnquiryDescMapper());
		Map<String, Integer> data = new HashMap<>();
		for (EnquiryDocumentModel enquiryDocumentModel : obj) {
			System.out.println(enquiryDocumentModel.getDesc() + "  " + enquiryDocumentModel.getCustId());
			data.put(enquiryDocumentModel.getDesc(), enquiryDocumentModel.getCustId());
		}
		return data;
	}

	@Override
	public List<String> getDocuments(int enquiryId) {
		List<TrackerDocument> documnetData = enquiryDao.getAllDocumnets(enquiryId);
		List<String> path = new ArrayList<>();
		for (TrackerDocument Docpath : documnetData) {
			path.add(Docpath.getEnqdDocumentPath());
		}
		return path;
	}

	@Override
	public void assignEnquirytouser(int enquiryId, Model model) {
		List<Auth_users> usersdata = enquiryDao.assignEnquirytoPresales();
		// Lists the presales team
		List<String> presalesteam = new ArrayList<>();
		for (Auth_users user : usersdata) {
			presalesteam.add(user.getAusrId());
		}
		model.addAttribute("users", presalesteam);
	}

}
