package com.service.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bean.Login;
import com.service.LoginService;

class LoginTest {

	@Autowired
	private LoginService loginService;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetLogin() {
//		fail("Not yet implemented");
		loginService.getLogin("D1000");
	}

	@Test
	void testSaveLogin() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteLogin() {
		fail("Not yet implemented");
	}

}
