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

import com.bean.Doctor;
import com.bean.DoctorList;

@ExtendWith(MockitoExtension.class)
class DoctorTest {
	
	@Autowired
	@InjectMocks
	private DoctorService doctorService;
	
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
	void testShowAllDoctor() {
//		fail("Not yet implemented");
		List<Doctor> doctors = new ArrayList<>();
		
		doctors.add(new Doctor("1","AAA","DepartmentA",3,"M",23,"xxxxxx5555","AdressA"));
		doctors.add(new Doctor("2","BBB","DepartmentB",4,"F",24,"xxxxxx5555","AdressB"));
		doctors.add(new Doctor("3","CCC","DepartmentB",5,"M",25,"xxxxxx5555","AdressC"));
		DoctorList dList = new DoctorList(doctors);
		
		Mockito.when(restTemplate.getForObject("http://doctor-service/doctors", DoctorList.class)).thenReturn(dList);
		assertEquals(dList, doctorService.showAllDoctor());
	}
	@Test
	void testShowAllDoctorNegative() {
//		fail("Not yet implemented");
		List<Doctor> doctors = new ArrayList<>();
		DoctorList dList = new DoctorList(doctors);
		
		Mockito.when(restTemplate.getForObject("http://doctor-service/doctors", DoctorList.class)).thenReturn(dList);
		assertEquals(dList, doctorService.showAllDoctor());
	}

	@Test
	void testShowDoctorById() {
//		fail("Not yet implemented");
		Doctor doctor = new Doctor("D1000",	"Dr Pullen",	"Cardiologists"	,5,	"m"	,30,	"+1 582-558-3861",	"1128, 3rd Floor, Fat No 13a Wings, Sanjay Society, Prabhadevi");
		Mockito.when(restTemplate.getForObject("http://doctor-service/doctors/"+"D1000", Doctor.class)).thenReturn(doctor);
		
		assertEquals(doctor, doctorService.showDoctorById("D1000"));
	}
	@Test
	void testShowDoctorByIdNegative() {
//		fail("Not yet implemented");
		Doctor doctor = new Doctor();
		Mockito.when(restTemplate.getForObject("http://doctor-service/doctors/"+"D10000", Doctor.class)).thenReturn(doctor);
		
		assertEquals(doctor, doctorService.showDoctorById("D10000"));
	}

	@Test
	void testAddDoctor() {
//		fail("Not yet implemented");
		Doctor doctor = new Doctor("D1000",	"Dr Pullen",	"Cardiologists"	,5,	"m"	,30,	"+1 582-558-3861",	"1128, 3rd Floor, Fat No 13a Wings, Sanjay Society, Prabhadevi");
		Mockito.when(restTemplate.postForObject("http://doctor-service/doctors",doctor, Doctor.class)).thenReturn(doctor);
		assertEquals(doctor, doctorService.addDoctor(doctor));
	}
	@Test
	void testAddDoctorNegative() {
//		fail("Not yet implemented");
		Mockito.when(restTemplate.postForObject("http://doctor-service/doctors",null, Doctor.class)).thenReturn(null);
		assertEquals(null, doctorService.addDoctor(null));
	}

	@Test
	void testDeleteDoctor() {
//		fail("Not yet implemented");
		Doctor doctor = new Doctor("D1000",	"Dr Pullen",	"Cardiologists"	,5,	"m"	,30,	"+1 582-558-3861",	"1128, 3rd Floor, Fat No 13a Wings, Sanjay Society, Prabhadevi");
		restTemplate.delete("http://doctor-service/doctors/"+"D1000");
		Mockito.when(restTemplate.getForObject("http://doctor-service/doctors/"+"D1000", Doctor.class)).thenReturn(doctor);
		assertEquals(doctor, doctorService.deleteDoctor("D1000"));
	}
	@Test
	void testDeleteDoctorNegative() {
//		fail("Not yet implemented");
		restTemplate.delete("http://doctor-service/doctors/"+"D10000");
		Mockito.when(restTemplate.getForObject("http://doctor-service/doctors/"+"D10000", Doctor.class)).thenReturn(null);
		assertEquals(null, doctorService.deleteDoctor("D10000"));
	}

}
