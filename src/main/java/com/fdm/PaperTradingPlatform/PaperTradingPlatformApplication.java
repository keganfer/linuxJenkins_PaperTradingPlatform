package com.fdm.PaperTradingPlatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages = {"com.fdm.controllers","com.fdm.setup","com.fdm.service"})
@EntityScan(basePackages = {"com.fdm.model"})
@EnableJpaRepositories(basePackages = {"com.fdm.dal"})
public class PaperTradingPlatformApplication extends SpringBootServletInitializer {

	
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(PaperTradingPlatformApplication.class);
	    }
	
	public static void main(String[] args) {
		SpringApplication.run(PaperTradingPlatformApplication.class, args);
	}

}
