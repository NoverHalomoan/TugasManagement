package com.ManagementTugas.ManagementTugas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ManagementTugas.ManagementTugas.repository")
public class ManagementTugasApplication {

	@Profile({ "dev", "prod" })
	public static void main(String[] args) {
		SpringApplication.run(ManagementTugasApplication.class, args);

	}

}
