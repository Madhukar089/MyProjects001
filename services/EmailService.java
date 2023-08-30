
package com.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.contract.EmailContract;
import com.models.DTO.ReviewApproveDTO;

@Service
public class EmailService implements EmailContract {

	@Autowired
	private JavaMailSender mailSender;

	// Mail service to Request necessary data from the customer
	@Override
	public String requestEmail(String to, String subject, String formIdentifier) {

		String msg = "hello This is from so and so company. We need more details about"
				+ " your enquire to go on with the process of your enquire." + "\n"
				+ "Tap on the link to give more details about your enquire";
		String htmlContent = "<html><body><a href=\""
				+ "http://localhost:8080/RFPprojectStructure/formpage?formIdentifier=" + formIdentifier
				+ "\">Click here to access the form</a></body></html>";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = null;
		try {
			prepare(message, msg, htmlContent, to, subject, helper);
		} catch (MessagingException e) {
			e.printStackTrace();
			return "request mail failed due to exception";

		}

		mailSender.send(message);
		return "sent request mail";
	}

	// sends acknowledgement about enquiry and Request data to process the RFP
	@Override

	public String rfpRequestEmail(String to, String subject, String formIdentifier) {

		String msg = "Congratulations.your enquire has been qualified." + " Now we want to know more details about you"
				+ " to go on with the process." + "Tap on the link to give more details about your enquire";
		String htmlContent = "<html><body><a href=\""
				+ "http://localhost:8080/RFPprojectStructure/rfpformpage?RfpFormIdentifier=" + formIdentifier
				+ "\">Click here to access the form</a></body></html>";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = null;
		try {
			prepare(message, msg, htmlContent, to, subject, helper);
		} catch (MessagingException e) {
			e.printStackTrace();
			return "rfpRequestEmail failed due to exception";
		}

		mailSender.send(message);
		return "rfpRequestEmail sent";
	}

	@Override
	public String rejectMail(String to, String subject, Integer custId, String Desc) {

		String msg = "Your Enquiry has been rejected due to " + Desc;
		String htmlContent = "<html><body><h1>Your customer ID:" + custId
				+ "</h1> Your Enquiry has been rejected </body></html>";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = null;
		try {
			prepare(message, msg, htmlContent, to, subject, helper);
		} catch (MessagingException e) {
			e.printStackTrace();
			return "acknowledgmentMail for rejecting the customer is failed due to exception";
		}

		mailSender.send(message);

		return "rejection mail is sent";

	}

	@Override
	public String rfpConvertionMail(String to, String subject, Integer custId) {

		String msg = "Your Enquiry has been converted to RFP";
		String htmlContent = "<html><body><h1>Your customer ID:" + custId
				+ "</h1> Your Enquiry has been converted to RFP.We start processing the RFP.No additional features can be added further</body></html>";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = null;
		try {
			prepare(message, msg, htmlContent, to, subject, helper);
		} catch (MessagingException e) {
			e.printStackTrace();
			return "acknowledgmentMail for rejecting the customer is failed due to exception";
		}

		mailSender.send(message);

		return "RFP Convertion mail is sent";

	}

	// sends acknowledgement after receiving required data from the customer
	@Override
	public String acknowledgmentMail(String to, String subject, Integer custId) {

		String msg = "Your Request as been recieved ";
		String htmlContent = "<html><body><h1>Your customer ID:" + custId
				+ "</h1> for further enquires please use enquiry form in our website</body></html>";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = null;
		try {
			prepare(message, msg, htmlContent, to, subject, helper);
		} catch (MessagingException e) {
			e.printStackTrace();
			return "acknowledgmentMail failed due to exception";
		}

		mailSender.send(message);

		return "acknowledgmentMail sent";
	}

	// sending prepared RFP document through mail
	@Override

	public String sendreviewEmail(String to, String subject, ReviewApproveDTO reviewApproveDTO) {

		String msg = "Here is your prepared RFP Document. Please do review this and let us know what is your opinion b clicking on the below link";
		String htmlContent = "<html><body><a href=\"" + "http://localhost:8082/RFPprojectStructure/formfor?rfpr="
				+ reviewApproveDTO.getRfpId() + "\">Click here to Approve or Reject the form</a></body></html>";

		String document = "<html><body>\n Your document is here<a href=\""
				+ "http://localhost:8082/RFPprojectStructure/showFile?filename=" + reviewApproveDTO.getDocumentPath()
				+ "\">Click here to view the Document</a></body></html>";

		htmlContent = htmlContent + document;
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = null;
		try {
			prepare(message, msg, htmlContent, to, subject, helper);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "sendreviewEmail failed due to exception ";
		}

		mailSender.send(message);
		return "sendreviewEmail sent";
	}

	private void prepare(MimeMessage message, String msg, String htmlContent, String to, String subject,
			MimeMessageHelper helper) throws MessagingException {

		helper = new MimeMessageHelper(message, true, "UTF-8");
		helper.setTo(to);
		message.setSubject(subject);
		helper.setText(msg + "\n" + htmlContent, true);

	}

}