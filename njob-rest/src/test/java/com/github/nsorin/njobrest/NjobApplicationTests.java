package com.github.nsorin.njobrest;

import com.github.nsorin.njobrest.web.controller.ApplicationController;
import com.github.nsorin.njobrest.web.controller.CompanyController;
import com.github.nsorin.njobrest.web.controller.DocumentController;
import com.github.nsorin.njobrest.web.controller.RecruiterController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NjobApplicationTests {

	@Autowired
	private CompanyController companyController;

	@Autowired
	private RecruiterController recruiterController;

	@Autowired
	private ApplicationController applicationController;

	@Autowired
	private DocumentController documentController;

	@Test
	void contextLoads() {
		assertThat(this.companyController).isNotNull();
		assertThat(this.recruiterController).isNotNull();
		assertThat(this.applicationController).isNotNull();
		assertThat(this.documentController).isNotNull();
	}

}
