package com.fdm.PaperTradingPlatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages = {"com.fdm.controllers","com.fdm.setup","com.fdm.service"})
@EntityScan(basePackages = {"com.fdm.model"})
@EnableJpaRepositories(basePackages = {"com.fdm.dal"})
public class PaperTradingPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaperTradingPlatformApplication.class, args);
	}

}
