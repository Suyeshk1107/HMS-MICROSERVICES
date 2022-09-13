package com.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bean.Doctor;
import com.bean.DoctorList;
import com.bean.Login;
import com.bean.Patient;
import com.bean.PatientList;
import com.service.DoctorService;
import com.service.LoginService;
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
	@Mock
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
	void testAddDoctorController() {
		ModelAndView modelView = new ModelAndView("addDoctor");
		assertEquals(modelView.getViewName(), adminController.addDoctorController().getViewName());

	}

	@Test
	void testSaveDoctorController() {
		ModelAndView modelAndView = new ModelAndView("Output");
		Doctor doctor = new Doctor("1","AAA","DepartmentA",3,"M",23,"xxxxxx5555","AdressA");
		Login login = new Login();
		 MockHttpServletRequest request = new MockHttpServletRequest();
		   request.addParameter("dName", "AAA");
		   request.addParameter("dAge", "23");
		   request.addParameter("dGender", "M");
		   request.addParameter("dExperience", "3");
		   request.addParameter("dContact", "xxxxxx5555");
		   request.addParameter("dAddress", "AdressA");
		   request.addParameter("dDepartment", "DepartmentA");
		   Doctor doctor2 = new Doctor(null,"AAA","DepartmentA",3,"M",23,"xxxxxx5555","AdressA");
		   Mockito.when(doctorService.addDoctor(doctor2)).thenReturn(doctor);
		   Mockito.when(loginService.saveLogin(doctor.getDoctorId(), doctor.getDoctorId())).thenReturn(login);
		assertEquals(modelAndView.getViewName(), adminController.saveDoctorController(request).getViewName());
	}

//	@Test
//	void testGetAllDoctorIds() {
//		fail("Not yet implemented");
//	}

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
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("ShowAllDoctors");
		List<Doctor> doctors = new ArrayList<>();
		
		doctors.add(new Doctor("1","AAA","DepartmentA",3,"M",23,"xxxxxx5555","AdressA"));
		doctors.add(new Doctor("2","BBB","DepartmentB",4,"F",24,"xxxxxx5555","AdressB"));
		doctors.add(new Doctor("3","CCC","DepartmentB",5,"M",25,"xxxxxx5555","AdressC"));
		DoctorList dList = new DoctorList(doctors);
		Mockito.when(doctorService.showAllDoctor()).thenReturn(dList);
		assertEquals(modelAndView.getViewName(), adminController.showAllDoctors().getViewName());
	}
	@Test
	void testShowAllDoctors2() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Output");
		List<Doctor> doctors = new ArrayList<>();
		
		doctors.add(new Doctor());
		
		DoctorList dList = new DoctorList(doctors);
		Mockito.when(doctorService.showAllDoctor()).thenReturn(dList);
		assertEquals(modelAndView.getViewName(), adminController.showAllDoctors().getViewName());
	}

//	@Test
//	void testGetAllPatientIds() {
//		fail("Not yet implemented");
//	}

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
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("ShowAllPatient");
		List<Patient> patients = new ArrayList<>();
		
		patients.add(new Patient("1","AAA","M",23,"xxxxxx5555","AdressA","SymptomA"));
		patients.add(new Patient("2","BBB","F",24,"xxxxxx5555","AdressB","SymptomB"));
		patients.add(new Patient("3","CCC","M",25,"xxxxxx5555","AdressC","SymptomC"));
		PatientList pList = new PatientList(patients);
		
		Mockito.when(patientService.showAllPatient()).thenReturn(pList);
		assertEquals(modelAndView.getViewName(), adminController.showAllPatients().getViewName());
	}
	@Test
	void testShowAllPatients2() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Output");
		List<Patient> patients = new ArrayList<>();
		
		patients.add(new Patient("no id","no name","no gender",0,"no contact","no Address","no Symptom"));
		PatientList pList = new PatientList(patients);
		
		Mockito.when(patientService.showAllPatient()).thenReturn(pList);
		assertEquals(modelAndView.getViewName(), adminController.showAllPatients().getViewName());
	}

}
