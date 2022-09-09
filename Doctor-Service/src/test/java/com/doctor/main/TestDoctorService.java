package com.doctor.main;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.doctor.bean.Doctor;
import com.doctor.persistence.DoctorDao;
import com.doctor.service.DoctorServiceImpl;


@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestDoctorService {
	
	@InjectMocks
	@Autowired
	private DoctorServiceImpl doctorServiceImpl;
	
	@Mock
	private DoctorDao doctorDao;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	
	@Nested
	class TestGetDoctorById{
		
		@Test
		void getDoctorById_T001() {
			Doctor doctor = new Doctor("D1000",	"Dr Pullen",	"Cardiologists"	,5,	"m"	,30,	"+1 582-558-3861",	"1128, 3rd Floor, Fat No 13a Wings, Sanjay Society, Prabhadevi");
			Mockito.when(doctorDao.findById("D1000")).thenReturn(Optional.of(doctor));
			
			assertEquals(doctor, doctorServiceImpl.getDoctorById("D1000"));
		}
		
		@Test
		void getDoctorById_T002() {
			Doctor doctor = new Doctor();
			Mockito.when(doctorDao.findById("1")).thenReturn(Optional.of(doctor));
			
			assertEquals(doctor, doctorServiceImpl.getDoctorById("1"));
		}
		
	}
	
	
	@Nested
	class TestAddDoctor{
		
		@Test
		void testAddDoctor_T001() {

			Doctor doctor = new Doctor("D1000",	"Dr Pullen",	"Cardiologists"	,5,	"m"	,30,	"+1 582-558-3861",	"1128, 3rd Floor, Fat No 13a Wings, Sanjay Society, Prabhadevi");
			Mockito.when(doctorDao.save(doctor)).thenReturn(doctor);
			assertEquals(doctor, doctorServiceImpl.addDoctor(doctor));
			
		}
		
		@Test
		void testAddDoctor_T002() {

			Mockito.when(doctorDao.save(null)).thenReturn(null);
			assertEquals(null, doctorServiceImpl.addDoctor(null));
			
		}
		
		@Test
		void testAddDoctor_T003() {
			Doctor doctor = new Doctor();
			Mockito.when(doctorDao.save(doctor)).thenReturn(doctor);
			assertEquals(doctor, doctorServiceImpl.addDoctor(doctor));
			
		}
		
	}
	
	@Nested
	class TestDeleteDoctor{
		
		@Test
		void testDeleteDoctor_T001() {
			Doctor doctor = new Doctor("D1000",	"Dr Pullen",	"Cardiologists"	,5,	"m"	,30,	"+1 582-558-3861",	"1128, 3rd Floor, Fat No 13a Wings, Sanjay Society, Prabhadevi");
			Mockito.when(doctorDao.findById("D1000")).thenReturn(Optional.of(doctor));
			assertEquals(doctor, doctorServiceImpl.deleteDoctor("D1000"));
		}
		
		@Test
		void testDeleteDoctor_T002() {
			Doctor doctor = new Doctor();
			Mockito.when(doctorDao.findById("345")).thenReturn(Optional.of(doctor));
			assertEquals(doctor, doctorServiceImpl.deleteDoctor("345"));
		}
	}
	
	@Nested
	class TestGetAllDoctor{
		
		@Test 
		void testGetAllDoctor_T001() {
			List<Doctor> doctors = new ArrayList<>();
			
			doctors.add(new Doctor("1","AAA","DepartmentA",3,"M",23,"xxxxxx5555","AdressA"));
			doctors.add(new Doctor("2","BBB","DepartmentB",4,"F",24,"xxxxxx5555","AdressB"));
			doctors.add(new Doctor("3","CCC","DepartmentB",5,"M",25,"xxxxxx5555","AdressC"));
			
			Mockito.when(doctorDao.findAll()).thenReturn(doctors);
			assertIterableEquals(doctors, doctorServiceImpl.getAllDoctor());
			
		}
		
		@Test 
		void testGetAllDoctor_T002() {
			List<Doctor> doctors = new ArrayList<>();
			
			Mockito.when(doctorDao.findAll()).thenReturn(doctors);
			assertIterableEquals(doctors, doctorServiceImpl.getAllDoctor());
			
		}
		
		
	}
	
	@Nested
	class TestGetLastDoctorId_And_TestSetNewDoctorId {
		
		@Test
		void testGetLastDoctorId_T001() {
			
			Doctor doctor = new Doctor("D1000",	"Dr Pullen",	"Cardiologists"	,5,	"m"	,30,	"+1 582-558-3861",	"1128, 3rd Floor, Fat No 13a Wings, Sanjay Society, Prabhadevi");
			Mockito.when(doctorDao.findTopByOrderByDoctorIdDesc()).thenReturn(doctor);
			
			assertEquals("D1000", doctorServiceImpl.getLastDoctorId());
		}
		
		@Test
		void testGetLastDoctorId_T002() {
			
			Mockito.when(doctorDao.findTopByOrderByDoctorIdDesc()).thenReturn(null);
			
			assertEquals(null, doctorServiceImpl.getLastDoctorId());
		}
		
//		@Test // throws error
//		void testSetNewDoctorId_T001() {
//			
//			Mockito.when(doctorService.getLastDoctorId()).thenReturn("D1000");
//			
//			assertEquals("D1001", patientService.setNewPatientId());
//		}
		
		@Test
		void testSetNewDoctorId_T002() {
			Mockito.when(doctorServiceImpl.getLastDoctorId()).thenReturn(null);
			assertEquals("D1000", doctorServiceImpl.setDoctorId());
		}
	}

}
