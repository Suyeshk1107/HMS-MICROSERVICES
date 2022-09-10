package com.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.bean.Login;
import com.service.LoginService;

@ExtendWith(MockitoExtension.class)
class LoginTest {

	@Autowired
	@InjectMocks
	private LoginService loginService;
	
	@Mock
	private RestTemplate restTemplate;
	
	
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

	
//	@Test
//	void testGetLogin() {
//		fail("Not yet implemented");
//		loginService.getLogin("D1000");
//	}

//	@Test
//	void testSaveLogin() {
//		fail("Not yet implemented");
//	}

//	@Test
//	void testDeleteLogin() {
//		fail("Not yet implemented");
//	}
	
	
	@Nested
	class TestGetLogin{
		
		@Test
		void testGetLogin_T001() {
			
			Login login = new Login("D1000","D1000");
			
			Mockito.when(restTemplate.getForObject("http://login-service/logins/" + "D1000", Login.class )).thenReturn(login);
			
			assertEquals(login, loginService.getLogin("D1000"));
			
		}
		
		@Test
		void testGetLogin_T002() {
			Login login = new Login();
			
			Mockito.when(restTemplate.getForObject("http://login-service/logins/" + "D123", Login.class )).thenReturn(login);
			
			assertEquals(login, loginService.getLogin("D123"));
		}
		
	}
	
	@Nested
	class TestSaveLogin{
		
		@Test
		void testSaveLogin_T001() {
			
			Login login = new Login("D1000","D1000");
			
			Mockito.when(restTemplate.postForObject("http://login-service/logins", new Login("D1000", "D1000"), Login.class)).thenReturn(login);
			
			assertEquals(login, loginService.saveLogin("D1000","D1000"));
			
		}
		
		@Test
		void testSaveLogin_T002() {
			
			Login login = new Login();
			
			Mockito.when(restTemplate.postForObject("http://login-service/logins", new Login(null, null), Login.class)).thenReturn(login);
			
			assertEquals(login, loginService.saveLogin(null,null));
		}
		
	}
	
	@Nested
	class TestDeleteLogin{
		
		@Test
		void testDeleteLogin_T001() {
			Login login = new Login("D1000","D1000");
			
			Mockito.when(restTemplate.getForObject("http://login-service/logins/" + "D1000", Login.class )).thenReturn(login);
			
			assertEquals(login, loginService.deleteLogin("D1000"));
		}
		
		@Test
		void testDeleteLogin_T002() {
			Login login = new Login();
			
			Mockito.when(restTemplate.getForObject("http://login-service/logins/" + "D123", Login.class )).thenReturn(login);
			
			assertEquals(login, loginService.deleteLogin("D123"));
		}
		
	}
	
	

}
