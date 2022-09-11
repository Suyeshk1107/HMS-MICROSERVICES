package com.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

	@Nested
	class TestHomePageController{
		
		@Test
		void testHomePageController() {
			ModelAndView modelView = new ModelAndView("index");
//			Mockito.when()
			assertInstanceOf(ModelAndView.class, commonController.homePageController());
//			assertNotNull(commonController.homePageController());
			assertViewNa
			ModelAndViewAssert
		}
		
		
	}

}
