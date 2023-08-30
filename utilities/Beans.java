package com.utilities;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.contract.AnalyticsDao;
import com.contract.CombinedCustomerRFPResponseContract;
import com.contract.CombinedCustomerResponseContract;
import com.contract.CustomerDAO;
import com.contract.CustomerUtilityInterface;
import com.contract.DocumentContract;
import com.contract.DynamicContract;
import com.contract.DynamicFormDAO;
import com.contract.EmailContract;
import com.contract.EnquiryDAO;
import com.contract.ProfileDAO;
import com.contract.RFPDAO;
import com.dao.AnalyticsDaoImpl;
import com.dao.CustomerDaoImpl;
import com.dao.DynamicFormDaoImpl;
import com.dao.EnquiryDaoImpl;
import com.dao.ProfileDaoImpl;
import com.dao.RFPDaoImpl;
import com.dao.TrackerEnquiryDao;
import com.dao.TrackerEnquiryDaoImp;
import com.filter.CustomerFilter;
import com.filter.EnquiryFilter;
import com.filter.RFPDocumentFilter;
import com.filter.RFPFilter;
import com.models.DTO.MainModelFormDTO;
import com.models.Input.CombinedCustomerRFPResponse;
import com.models.Input.CombinedCustomerResponse;
import com.services.AnalyticsService;
import com.services.CustomerUtility;
import com.services.DocumentService;
import com.services.DynamicFormService;
import com.services.EmailService;
import com.services.EnquiryService;
import com.services.MasterRFPprocessTracker;
import com.services.PipelineService;
import com.services.ProfileServices;
import com.services.RFPService;

@Configuration
public class Beans {

	@Bean
	CustomerDAO customerDao() {
		return new CustomerDaoImpl();
	}

	@Bean
	EnquiryDAO enquiryDao() {
		return new EnquiryDaoImpl();
	}

	@Bean
	CustomerFilter customerFilter() {

		return new CustomerFilter();
	}

	@Bean
	EnquiryFilter enquiryFilter() {
		return new EnquiryFilter();
	}

	@Bean
	RFPService serivce() {
		return new RFPService(RFPDao());
	}

	@Bean
	EnquiryService enquiryService() {
		return new EnquiryService();
	}

	@Bean
	RFPDAO RFPDao() {
		return new RFPDaoImpl();
	}

	@Bean
	DynamicFormDAO dynamicFormDao() {
		return new DynamicFormDaoImpl();
	}

	@Bean
	CustomerUtilityInterface customerUtilityInterface() {

		return new CustomerUtility(JdbcConfig.jdbcTemplate(), enquiryDao());
	}

	@Bean

	ProfileDAO profileDao() {
		return new ProfileDaoImpl();
	}

	@Bean
	ProfileServices profileServices() {
		return new ProfileServices();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public RFPFilter rfpFilter() {
		return new RFPFilter();
	}

	@Bean
	public RFPDocumentFilter rfpDocumentFilter() {
		return new RFPDocumentFilter();
	}

	@Bean
	public CombinedCustomerResponseContract combinedCustomerResponseContract() {
		return new CombinedCustomerResponse();
	}

	@Bean
	public CombinedCustomerRFPResponseContract combinedCustomerRFPResponseContract() {
		return new CombinedCustomerRFPResponse();
	}

	@Bean
	public EmailContract emailContract() {
		return new EmailService();
	}

	@Bean
	public DynamicContract dynamicContract() {
		return new DynamicFormService();
	}

	@Bean
	public TrackerEnquiryDao trackerenquirydao() {
		return new TrackerEnquiryDaoImp();
	}

	@Bean
	public MainModelFormDTO mainmodelform() {
		return new MainModelFormDTO();
	}

	@Bean
	public PipelineService pipelineService() {

		return new PipelineService();
	}

	@Bean
	public DocumentContract documentService() {
		return new DocumentService();
	}

	@Bean
	AnalyticsDao analyticsDao() {

		return new AnalyticsDaoImpl();
	}

	@Bean
	AnalyticsService analyticsService() {
		return new AnalyticsService(analyticsDao());
	}

	@Bean
	MasterRFPprocessTracker masterRFPprocessTracker() {
		return new MasterRFPprocessTracker();

	}
	// new beans

}
