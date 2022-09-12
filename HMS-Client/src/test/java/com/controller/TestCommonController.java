package com.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.ModelAndViewAssert.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

@ExtendWith(MockitoExtension.class)
class TestCommonController {
	
	@InjectMocks
	@Autowired
	private CommonController commonController;
	
	@Mock
	private ModelAndView modelAndView;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testHomePageController() {
		ModelAndView modelView = new ModelAndView("index");

//		ModelAndViewAssert.assertViewName(commonController.homePageController(),"index");
		assertEquals(modelView.getViewName(), commonController.homePageController().getViewName());
	}
	
	@Test
	void testRegisterPageController() {
		assertEquals("patientRegister",commonController.registerPageController().getViewName());
	}

	@Test
	void testBeforeLoginController() {
		assertEquals("beforeLogin",commonController.beforeLoginController().getViewName());
	}
	
	@Test
	void testLoginPageController() {
		assertEquals("login", commonController.loginPageController().getViewName());
	}
}
