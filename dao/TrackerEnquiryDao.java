
package com.dao;

import java.util.List;

import com.models.Input.RejectEnquiry;
import com.models.Input.RejectRfp;
import com.models.Input.TrackerEnquiryConfig;
import com.models.Input.TrackerRfpConfig;

public interface TrackerEnquiryDao {
	// we can access the model class in TrackerEnquiry pojoClass
	List<TrackerEnquiryConfig> getEnquiry();

	List<RejectEnquiry> getEnquiryReject();

	List<TrackerEnquiryConfig> getEnquiryApprove();

	List<RejectRfp> getConvertToRfp();

	List<RejectRfp> getRfpReject();

	List<RejectRfp> getRfpApprove();

	List<TrackerRfpConfig> getCompleted();

}