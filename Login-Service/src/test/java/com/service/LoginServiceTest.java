package com.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.bean.Login;
import com.persistence.LoginDao;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

	@InjectMocks
	@Autowired
	private LoginServiceImpl loginService;
	
	@Mock
	private LoginDao loginDao;
	
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
	void testGetLoginById() {

		Login dummy = new Login("D1000","D1000");
		Mockito.when(loginDao.findById("D1000")).thenReturn(Optional.of(dummy));
		
		assertEquals(dummy, loginService.getLoginById("D1000"));
	}
	@Test
	void testGetLoginByIdNegative() {
		
		Login dummy = new Login(null,null);
		Mockito.when(loginDao.findById("D1000")).thenReturn(Optional.of(dummy));
		
		assertEquals(dummy, loginService.getLoginById("D1000"));
	}

	@Test
	void testSaveLogin() {

		Login dummy = new Login("D1000","D1000");
		Mockito.when(loginDao.save(dummy)).thenReturn(dummy);
		assertEquals(dummy, loginService.saveLogin(dummy));
		
	}
	@Test
	void testSaveLoginNegative() {
		Login dummy2 = new Login();
		Mockito.when(loginDao.save(dummy2)).thenReturn(dummy2);
		assertEquals(dummy2, loginService.saveLogin(dummy2));
		
	}

	@Test
	void testDeleteLogin() {
		Login dummy = new Login("D1000","D1000");
		Mockito.when(loginDao.findById("D1000")).thenReturn(Optional.of(dummy));
		assertEquals(dummy, loginService.deleteLogin("D1000"));
	}

}
