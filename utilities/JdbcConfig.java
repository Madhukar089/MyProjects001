package com.utilities;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class JdbcConfig {

	// @Bean
	// public DataSource dataSource() {
	// // Configure and return the DataSource bean based on your database settings
	// DriverManagerDataSource dataSource = new DriverManagerDataSource();
	// dataSource.setDriverClassName("org.postgresql.Driver");
	// dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
	// dataSource.setUsername("postgres");
	// dataSource.setPassword("root");
	// return dataSource;
	// }

	private static DataSource dataSource() {
		// Configure and return the DataSource bean based on your database settings
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://192.168.110.48:5432/plf_training");
		dataSource.setUsername("plf_training_admin");
		dataSource.setPassword("pff123");
		return dataSource;
	}

	private static JdbcTemplate getTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource());
	}

	@Bean
	public static JdbcTemplate jdbcTemplate() {
		return getTemplate(dataSource());
	}

}
