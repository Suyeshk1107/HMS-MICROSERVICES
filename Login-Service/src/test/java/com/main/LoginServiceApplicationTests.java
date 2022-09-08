package com.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bean.Login;
import com.persistence.LoginDao;

@SpringBootTest
class LoginServiceApplicationTests {

	@Autowired
	private LoginDao loginDao;
	
	@Test
	void contextLoads() {
	}
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	public void testFindPasswordById() {
		Login l = new Login("P101", "P101");
		assertEquals(l, loginDao.findPasswordById("P101"));
	}
	@Test
	public void testFindPasswordByIdNegativeCase() {
		assertEquals(null, loginDao.findPasswordById("P1011"));
	}

}
