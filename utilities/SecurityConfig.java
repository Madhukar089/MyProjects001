package com.utilities;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.inMemoryAuthentication().withUser("user1").password("sales").roles("sales").and().withUser("user2")
				.password("sales").roles("sales").and().withUser("user3").password("sales").roles("sales").and()
				.withUser("ramu").password("sales").roles("sales").and().withUser("user4").password("presales")
				.roles("presales").and().withUser("user6").password("presales").roles("presales").and()
				.withUser("user5").password("presales").roles("presales").and().withUser("user11").password("tech")
				.roles("technical").and().withUser("user12").password("tech").roles("technical").and()
				.withUser("user10").password("tech").roles("technical").and().withUser("user7").password("mang")
				.roles("review manager").and().withUser("user8").password("mang").roles("review manager").and()
				.withUser("user9").password("mang").roles("review manager");

		// auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder( getPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable().authorizeRequests()
				.antMatchers("/customerhome", "/enquiry", "/request", "/sendMail", "/submitting", "/rfpRequest",
						"/RfpSendMail", "/rfpSubmitting", "/checkvalidcustforfeature", "/EnquiriesByUser",
						"/addfeature", "/formfor", "/approvalRequest", "/rejectionRequest", "/submitting/**",
						"/formpage/**", "/rfpSubmitting/**", "/rfpformpage/**", "/showFile/**")
				.permitAll()
				.antMatchers("/Enquiries", "/rejectEnquiry", "/getenquirydata", "/updatestatus", "/dashboard",
						"/Enquiryfilter", "/customerfilter/**", "/rfpfilter", "/RFPSRout", "/updateprofile",
						"/activeEnquiries", "/analytics", "/customerroute/**")
				.hasAnyRole("presales", "sales", "technical", "review manager").antMatchers("/assignEnquiry")
				.hasRole("sales")

				.anyRequest().authenticated().and().formLogin().loginPage("/customlogin")
				.failureUrl("/customlogin?error=true").successForwardUrl("/dashboard").permitAll().and().logout()
				.logoutUrl("/adminlogout").invalidateHttpSession(true);

		;

	}

	@Bean
	PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	// new route
}
