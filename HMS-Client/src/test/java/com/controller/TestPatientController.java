package com.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

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

import com.bean.Patient;
import com.service.PatientService;

@ExtendWith(MockitoExtension.class)
class TestPatientController {
	
	@InjectMocks
	@Autowired
	private PatientController patientController;

	@Mock
	private HttpSession session;
	@Mock
	private ModelAndView modelAndView;
	@Mock
	private PatientService patientService;
	

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Nested
	class TestshowPatientController{
		
		@Test
		void TestShowPatientController_T001() {
			
			ModelAndView modelAndView = new ModelAndView("ShowPatient");

			Mockito.when(session.getAttribute("userName")).thenReturn("P101");
			Mockito.when(patientService.showPatientById("P101")).thenReturn(new Patient("P101",null,null,0,null,null,null));
			
			
			assertEquals(modelAndView.getViewName(), patientController.showPatientController(session).getViewName());
			
		}
		
		@Test
		void TestShowPatientController_T002() {
			
			ModelAndView modelAndView = new ModelAndView("Output");
			
			Mockito.when(session.getAttribute("userName")).thenReturn("P101");
			Mockito.when(patientService.showPatientById("P101")).thenReturn(new Patient("no userName",null,null,0,null,null,null));
			
			
			assertEquals(modelAndView.getViewName(), patientController.showPatientController(session).getViewName());
			
		}
	}

}
