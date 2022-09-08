package com.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.patient.bean.Patient;
import com.patient.persistence.PatientDao;



@SpringBootTest
class PatientServiceApplicationTests {

	@Autowired
	private PatientDao patientDao;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	
		
	@Test
	public void testfindTopByOrderByPatientIdDesc() {

		Patient patient = new Patient("P114"	,"adfda",	"Male"	,342311	,"3254565723"	,"asdc",	"dsfawr");
		
		assertEquals(patient, patientDao.findTopByOrderByPatientIdDesc());
	}


		
	
}
