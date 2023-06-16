package com.paymybuddy.application;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.paymybuddy.application.controllers.LoginController;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ApplicationTests {

	@Autowired
	private LoginController controller;
	@Test
	void contextLoads()throws Exception {
		assertThat(controller).isNotNull();
	}

}
