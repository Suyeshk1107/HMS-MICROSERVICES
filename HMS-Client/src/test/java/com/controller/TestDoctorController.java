package com.controller;

import static org.junit.jupiter.api.Assertions.*;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.ModelAndView;

@ExtendWith(MockitoExtension.class)
class TestDoctorController {

	
	@InjectMocks
	@Autowired
	private DoctorController doctorController;
	
	@Mock
	private HttpSession session;
	
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
	void testViewPatientEnterIdController() {
		ModelAndView modelAndView = new ModelAndView("PatientEnterId");
		assertEquals(modelAndView.getViewName(),doctorController.viewPatientEnterIdController());
	}

	@Test
	void testViewPatientController() {
		fail("Not yet implemented");
	}

	@Test
	void testShowAppointmentControllerForDoctor() {
		fail("Not yet implemented");
	}

	@Test
	void testViewScheduleControllerForDoctor() {
		ModelAndView modelAndView = new ModelAndView("ShowMySchedules");
		Mockito.when(session.getAttribute("userName")).thenReturn("P101");
	}
}
