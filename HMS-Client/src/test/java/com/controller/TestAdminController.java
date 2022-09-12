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

import com.service.DoctorService;
import com.service.PatientService;

@ExtendWith(MockitoExtension.class)
class TestAdminController {

	@InjectMocks
	@Autowired
	private AdminController adminController;

	@Mock
	private HttpSession session;
	@Mock
	private ModelAndView modelAndView;
	@Mock
	private PatientService patientService;
	@Mock
	private DoctorService doctorService;
	
	
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
	void testAddDoctorController() {
		ModelAndView modelView = new ModelAndView("index");
		assertEquals(modelView.getViewName(), adminController.addDoctorController().getViewName());

	}

//	@Test
//	void testSaveDoctorController() {
//		ModelAndView modelAndView = new ModelAndView();
//		assertEquals(modelAndView.getViewName(), adminController.saveDoctorController().getViewName());
//	}

	@Test
	void testGetAllDoctorIds() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveDoctorByIdController() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveDoctorController() {
		fail("Not yet implemented");
	}

	@Test
	void testShowAllDoctors() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAllPatientIds() {
		fail("Not yet implemented");
	}

	@Test
	void testRemovePatientController() {
		fail("Not yet implemented");
	}

	@Test
	void testRemovePatientMessage() {
		fail("Not yet implemented");
	}

	@Test
	void testShowAllPatients() {
		fail("Not yet implemented");
	}

}
