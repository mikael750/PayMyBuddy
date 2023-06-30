package com.paymybuddy.application;

import com.paymybuddy.application.controllers.AuthController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ApplicationTests {

	@Autowired
	private AuthController controller;
	@Test
	void contextLoads()throws Exception {
		assertThat(controller).isNotNull();
	}

}
