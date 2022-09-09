package com.doctor.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.doctor.bean.Doctor;
import com.doctor.persistence.DoctorDao;


@SpringBootTest
class DoctorServiceApplicationTests {

	@Autowired
	private DoctorDao doctorDao;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	
		
	@Test
	public void testFindTopByOrderByDoctorIdDesc() {

		Doctor doctor = new Doctor("D1010",	"Dr Mishra",	"Pathology",	3,	"f",	43,	"98776452",	"steel gate");
		
		assertEquals(doctor, doctorDao.findTopByOrderByDoctorIdDesc());
	}
		
//		if empty
//		@Test
//		public void testFindTopByOrderByDoctorIdDescNegativeCase() {
//			assertEquals(null, doctorDao.findTopByOrderByDoctorIdDesc());
//		}

		
	
}
