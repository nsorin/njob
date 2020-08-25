package com.github.nsorin.njobfilesystem;

import com.github.nsorin.njobfilesystem.web.controller.FileController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private FileController fileController;

	@Test
	void contextLoads() {
		assertThat(this.fileController).isNotNull();
	}

}
