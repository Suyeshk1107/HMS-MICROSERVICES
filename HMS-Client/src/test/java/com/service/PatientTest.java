package com.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.client.RestTemplate;

import com.bean.Patient;
import com.bean.PatientList;


@ExtendWith(MockitoExtension.class)
class PatientTest {


	@Autowired
	@InjectMocks
	private PatientService patientService;
	
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

	@Test
	void testShowAllPatient() {
//		fail("Not yet implemented");
		List<Patient> patients = new ArrayList<>();
		
		patients.add(new Patient("1","AAA","M",23,"xxxxxx5555","AdressA","SymptomA"));
		patients.add(new Patient("2","BBB","F",24,"xxxxxx5555","AdressB","SymptomB"));
		patients.add(new Patient("3","CCC","M",25,"xxxxxx5555","AdressC","SymptomC"));
		PatientList pList = new PatientList(patients);
		Mockito.when(restTemplate.getForObject("http://patient-service/patients", PatientList.class)).thenReturn(pList);
		assertEquals(pList, patientService.showAllPatient());
	}
	@Test
	void testShowAllPatientNegative() {
//		fail("Not yet implemented");
		List<Patient> patients = new ArrayList<>();
		PatientList pList = new PatientList(patients);
		Mockito.when(restTemplate.getForObject("http://patient-service/patients", PatientList.class)).thenReturn(pList);
		assertEquals(pList, patientService.showAllPatient());
	}

	@Test
	void testShowPatientById() {
//		fail("Not yet implemented");
		Patient patient = new Patient("P101",	"Bhuwnesh",	"m",	24,	"9876987655",	"11, Welcome Shpg Centre, Old Padra Road, Old Padra Road"	,"Gastroenterologists");
		Mockito.when(restTemplate.getForObject("http://patient-service/patients/"+"P101", Patient.class)).thenReturn(patient);
		
		assertEquals(patient, patientService.showPatientById("P101"));
	}
	@Test
	void testShowPatientByIdNegative() {
//		fail("Not yet implemented");
		Patient patient = new Patient();
		Mockito.when(restTemplate.getForObject("http://patient-service/patients/"+"P101", Patient.class)).thenReturn(patient);
		
		assertEquals(patient, patientService.showPatientById("P101"));
	}

	@Test
	void testAddPatient() {
//		fail("Not yet implemented");
		Patient patient = new Patient("P101",	"Bhuwnesh",	"m",	24,	"9876987655",	"11, Welcome Shpg Centre, Old Padra Road, Old Padra Road"	,"Gastroenterologists");
		Mockito.when(restTemplate.postForObject("http://patient-service/patients",patient, Patient.class)).thenReturn(patient);
		assertEquals(patient, patientService.addPatient(patient));
	}
	@Test
	void testAddPatientNegative() {
//		fail("Not yet implemented");
		Patient patient = new Patient();
		Mockito.when(restTemplate.postForObject("http://patient-service/patients",patient, Patient.class)).thenReturn(patient);
		assertEquals(patient, patientService.addPatient(patient));
	}

	@Test
	void testDeletePatient() {
//		fail("Not yet implemented");
		Patient patient = new Patient("P101",	"Bhuwnesh",	"m",	24,	"9876987655",	"11, Welcome Shpg Centre, Old Padra Road, Old Padra Road"	,"Gastroenterologists");
		restTemplate.delete("http://patient-service/patients/"+"P101");
		Mockito.when(restTemplate.getForObject("http://patient-service/patients/"+"P101", Patient.class)).thenReturn(patient);
		assertEquals(patient, patientService.deletePatient("P101"));
	}
		@Test
		void testDeletePatientNegative() {
//		fail("Not yet implemented");
			restTemplate.delete("http://patient-service/patients/"+"P101");
			Mockito.when(restTemplate.getForObject("http://patient-service/patients/"+"P10111", Patient.class)).thenReturn(null);
			assertEquals(null, patientService.deletePatient("P10111"));
	}

}
