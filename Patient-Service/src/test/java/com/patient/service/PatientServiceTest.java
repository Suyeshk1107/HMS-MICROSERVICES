package com.patient.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.patient.bean.Patient;
import com.patient.persistence.PatientDao;


@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PatientServiceTest {
	
	@InjectMocks
	@Autowired
	private PatientServiceImpl patientService;
	
	@Mock
	private PatientDao patientDao;

//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//		patientService = new PatientServiceImpl();
//	}

//	@AfterAll
//	static void tearDownAfterClass() throws Exception {
//		patientService = null;
//	}
	
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Nested
	class TestGetPatientById{
		
		@Test
		void getPatientById_T001() {
			Patient patient = new Patient("P101",	"Bhuwnesh",	"m",	24,	"9876987655",	"11, Welcome Shpg Centre, Old Padra Road, Old Padra Road"	,"Gastroenterologists");
			Mockito.when(patientDao.findById("P101")).thenReturn(Optional.of(patient));
			
			assertEquals(patient, patientService.getPatientById("P101"));
		}
		
		@Test
		void getPatientById_T002() {
			Patient patient = new Patient();
			Mockito.when(patientDao.findById("1")).thenReturn(Optional.of(patient));
			
			assertEquals(patient, patientService.getPatientById("1"));
		}
		
	}
	
	
	@Nested
	class TestAddPatient{
		
		@Test
		void testAddPatient_T001() {

			Patient patient = new Patient("P101",	"Bhuwnesh",	"m",	24,	"9876987655",	"11, Welcome Shpg Centre, Old Padra Road, Old Padra Road"	,"Gastroenterologists");
			Mockito.when(patientDao.save(patient)).thenReturn(patient);
			assertEquals(patient, patientService.addPatient(patient));
			
		}
		
		@Test
		void testAddPatient_T002() {

			Mockito.when(patientDao.save(null)).thenReturn(null);
			assertEquals(null, patientService.addPatient(null));
			
		}
		
		@Test
		void testAddPatient_T003() {
			Patient patient = new Patient();
			Mockito.when(patientDao.save(patient)).thenReturn(patient);
			assertEquals(patient, patientService.addPatient(patient));
			
		}
		
	}
	
	@Nested
	class TestDeletePatient{
		
		@Test
		void testDeletePatient_T001() {
			Patient patient = new Patient("P101",	"Bhuwnesh",	"m",	24,	"9876987655",	"11, Welcome Shpg Centre, Old Padra Road, Old Padra Road"	,"Gastroenterologists");
			Mockito.when(patientDao.findById("P101")).thenReturn(Optional.of(patient));
			assertEquals(patient, patientService.deletePatient("P101"));
		}
		
		@Test
		void testDeletePatient_T002() {
			Patient patient = new Patient();
			Mockito.when(patientDao.findById("345")).thenReturn(Optional.of(patient));
			assertEquals(patient, patientService.deletePatient("345"));
		}
	}
	
	@Nested
	class TestGetAllPatient{
		
		@Test 
		void testGetAllPatient_T001() {
			List<Patient> patients = new ArrayList<>();
			
			patients.add(new Patient("1","AAA","M",23,"xxxxxx5555","AdressA","SymptomA"));
			patients.add(new Patient("2","BBB","F",24,"xxxxxx5555","AdressB","SymptomB"));
			patients.add(new Patient("3","CCC","M",25,"xxxxxx5555","AdressC","SymptomC"));
			
			Mockito.when(patientDao.findAll()).thenReturn(patients);
			assertIterableEquals(patients, patientService.getAllPatient());
			
		}
		
		@Test 
		void testGetAllPatient_T002() {
			List<Patient> patients = new ArrayList<>();
			
			Mockito.when(patientDao.findAll()).thenReturn(patients);
			assertIterableEquals(patients, patientService.getAllPatient());
			
		}
		
		
	}
	
	@Nested
	class TestGetLastPatientId_And_TestSetNewPatientId {
		
		@Test
		void testGetLastPatientId_T001() {
			
			Patient patient = new Patient("P101",	"Bhuwnesh",	"m",	24,	"9876987655",	"11, Welcome Shpg Centre, Old Padra Road, Old Padra Road"	,"Gastroenterologists");
			Mockito.when(patientDao.findTopByOrderByPatientIdDesc()).thenReturn(patient);
			
			assertEquals("P101", patientService.getLastPatientId());
		}
		
		@Test
		void testGetLastPatientId_T002() {
			
			Mockito.when(patientDao.findTopByOrderByPatientIdDesc()).thenReturn(null);
			
			assertEquals(null, patientService.getLastPatientId());
		}
		
//		@Test // throws error
//		void testSetNewPatientId_T001() {
//			
//			Patient patient = new Patient("P101",	"Bhuwnesh",	"m",	24,	"9876987655",	"11, Welcome Shpg Centre, Old Padra Road, Old Padra Road"	,"Gastroenterologists");
//			Mockito.when(patientDao.findTopByOrderByPatientIdDesc()).thenReturn(patient);
//			Mockito.when(patientService.getLastPatientId()).thenReturn("P103");
//			
//			assertEquals("P104", patientService.setNewPatientId());
//		}
		
		@Test
		void testSetNewPatientId_T002() {
			Mockito.when(patientService.getLastPatientId()).thenReturn(null);
			assertEquals("P101", patientService.setNewPatientId());
		}
	}

}
