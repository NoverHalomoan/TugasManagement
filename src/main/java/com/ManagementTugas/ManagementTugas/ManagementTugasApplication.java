package com.ManagementTugas.ManagementTugas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
// @EnableJpaRepositories(basePackages =
// "com.ManagementTugas.ManagementTugas.repository")
public class ManagementTugasApplication {

	@Profile({ "dev", "prod" })
	public static void main(String[] args) {
		SpringApplication.run(ManagementTugasApplication.class, args);

	}

}
