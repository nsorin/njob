package com.github.nsorin.njobfilesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class NjobApplication {

	public static void main(String[] args) {
		SpringApplication.run(NjobApplication.class, args);
	}

}
