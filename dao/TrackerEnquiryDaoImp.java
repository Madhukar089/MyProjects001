
package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mappers.EnquiryRowMapper;
import com.mappers.RejectRfpRowMapper;
import com.mappers.RejectRowMapper;
import com.mappers.TrackerRfpCompleteRowMapper;
import com.models.Input.RejectEnquiry;
import com.models.Input.RejectRfp;
import com.models.Input.TrackerEnquiryConfig;
import com.models.Input.TrackerRfpConfig;

public class TrackerEnquiryDaoImp implements TrackerEnquiryDao {

	@Autowired
	private JdbcTemplate jdbctemplate;

	@Override
	public List<TrackerEnquiryConfig> getEnquiry() {
		// TODO Auto-generated method stub
		try {
			String trackerenquiry = "SELECT DISTINCT te.enqr_id, te.enqr_cust_id, te.enqr_createdby, te.enqr_subject, te.enqr_assigned_to, te.enqr_luser FROM tracker_enquiries te  JOIN rejectedenquiries rd ON te.enqr_id != rd.e_id ORDER BY te.enqr_id ";
			return jdbctemplate.query(trackerenquiry, new EnquiryRowMapper());
		} catch (DataAccessException e) {
			throw new RuntimeException("Error while fetching tracker enquiries.", e);
		}
	}

	@Override
	public List<RejectEnquiry> getEnquiryReject() {
		// TODO Auto-generated method stub
		try {
			String enquiryreject = "select rd.e_id ,te.enqr_id,te.enqr_cust_id,te.enqr_createdby,te.enqr_subject,te.enqr_luser  from tracker_enquiries te join rejectedenquiries rd on te.enqr_id=rd.e_id  ";
			return jdbctemplate.query(enquiryreject, new RejectRowMapper());
		} catch (DataAccessException ex) {
			throw new RuntimeException("Error while fetching  enquiryreject.", ex);

		}
	}

	@Override
	public List<TrackerEnquiryConfig> getEnquiryApprove() {
		// TODO Auto-generated method stub
		try {
			String enquiryapprove = "select enqr_id,enqr_cust_id,enqr_createdby,enqr_subject,enqr_assigned_to,enqr_luser from tracker_enquiries where enqr_id not in (select e_id from rejectedenquiries)";
			return jdbctemplate.query(enquiryapprove, new EnquiryRowMapper());
		} catch (DataAccessException e) {
			throw new RuntimeException("Error while fetching  enquiryapprove.", e);

		}
	}

	@Override
	public List<RejectRfp> getConvertToRfp() {
		// TODO Auto-generated method stub
		try {
			String convertrfp = "select rfpr_enqr_id,rfpr_created_ausr_id,rfpr_status,rfpr_assignedto,rfpr_subject,rfpr_intro_note from tracker_rfp";
			return jdbctemplate.query(convertrfp, new RejectRfpRowMapper());
		} catch (DataAccessException ex) {
			throw new RuntimeException("Error while fetching  convertrfp.", ex);

		}
	}

	@Override
	public List<RejectRfp> getRfpReject() {
		// TODO Auto-generated method stub
		try {
			String rfpreject = "select rfpr_enqr_id,rfpr_created_ausr_id,rfpr_status,rfpr_assignedto,rfpr_subject,rfpr_intro_note from  tracker_rfp  where rfpr_id  in  (select rej_rfpr_id from rejected_rfps)";
			return jdbctemplate.query(rfpreject, new RejectRfpRowMapper());
		} catch (DataAccessException e) {
			throw new RuntimeException("Error while fetching  rfpreject.", e);

		}
	}

	@Override
	public List<RejectRfp> getRfpApprove() {
		// TODO Auto-generated method stub
		try {
			String rfpapprove = "select rfpr_enqr_id,rfpr_created_ausr_id,rfpr_status,rfpr_assignedto,rfpr_subject,rfpr_intro_note from  tracker_rfp  where rfpr_status='RFPR_COMPLETED'";
			return jdbctemplate.query(rfpapprove, new RejectRfpRowMapper());
		} catch (DataAccessException ex) {
			throw new RuntimeException("Error while fetching  rfpapprove.", ex);

		}
	}

	@Override
	public List<TrackerRfpConfig> getCompleted() {
		// TODO Auto-generated method stub
		try {
			String complete = "select rfpr_id,rpfd_reviewedby,rfpd_desc,rpdf_status,rfpd_type,rfpd_sharedstatus from tracker_rfpr_documents where rpdf_status='Completed'";
			return jdbctemplate.query(complete, new TrackerRfpCompleteRowMapper());
		} catch (DataAccessException e) {
			throw new RuntimeException("Error while fetching  complete.", e);

		}
	}

}