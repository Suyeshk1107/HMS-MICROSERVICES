package com.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.query.Param;

import com.bean.Appointment;
import com.persistence.AppointmentDao;


@SpringBootTest
class AppointmentServiceApplicationTests {

	@Autowired
	private AppointmentDao appointmentDao;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Nested
	class TestAppointmentDao{
		@Test
		void testGetAllAppointmentsByDoctorId() {
			
			List<Appointment> appointments = new ArrayList<>();
			appointments.add(new Appointment("P105",	"P. Mishra"	,Time.valueOf("01:00:00"), Date.valueOf("2022-08-10"),	"D1003",	"Dr Ken Hurt",	"headache, fever, weakness",10));
			
			assertIterableEquals(appointments, appointmentDao.getAllAppointmentsByDoctorId("D1003"));
		}
		
		@Test
		void testGetAllAppointmentsByDoctorIdNegativeScenario() {
			
			List<Appointment> appointments = new ArrayList<>();
			
			assertIterableEquals(appointments, appointmentDao.getAllAppointmentsByDoctorId("D1000"));
		}
		
		@Test
		void testGetAllAppointmentsByPatientId() {
			
			List<Appointment> appointments = new ArrayList<>();
			appointments.add(new Appointment("P105",	"P. Mishra"	,Time.valueOf("01:00:00"), Date.valueOf("2022-08-10"),	"D1003",	"Dr Ken Hurt",	"headache, fever, weakness",10));
			
			assertIterableEquals(appointments, appointmentDao.getAllAppointmentsByPatientId("P105"));
		}
		
		@Test
		void testGetAllAppointmentsByPatientIdNegativeScenario() {
			
			List<Appointment> appointments = new ArrayList<>();
			
			assertIterableEquals(appointments, appointmentDao.getAllAppointmentsByPatientId("P109"));
		}
		
		
		@Test
		void testCallProcedure() {
			assertEquals(1, appointmentDao.callProcedure("P107", "D1000"));
		}
		
	//	@Test
	//	void testCallProcedureNegativeScenario1() {
	//		assertEquals(0, appointmentDao.callProcedure("137", "D1000"));
	//	}
	//	
	//	@Test
	//	void testCallProcedureNegativeScenario2() {
	//		assertEquals(0, appointmentDao.callProcedure("P107", "12345"));
	//	}
		
		
		@Test
		void testfindAppointmentByDoctorIdAndDate() {
			
			List<Appointment> appointments = new ArrayList<>();
			appointments.add(new Appointment("P105",	"P. Mishra"	,Time.valueOf("01:00:00"), Date.valueOf("2022-08-10"),	"D1003","Dr Ken Hurt",	"headache, fever, weakness",10));
			
			assertIterableEquals(appointments, appointmentDao.findAppointmentByDoctorIdAndDate("D1003",Date.valueOf("2022-08-10")));
		}
		
		@Test
		void testfindAppointmentByDoctorIdAndDateNegativeScenario1() {
			
			List<Appointment> appointments = new ArrayList<>();
			
			assertIterableEquals(appointments, appointmentDao.findAppointmentByDoctorIdAndDate("D003",Date.valueOf("2022-08-10")));
		}
		
		@Test
		void testfindAppointmentByDoctorIdAndDateNegativeScenario12() {
			
			List<Appointment> appointments = new ArrayList<>();
			
			assertIterableEquals(appointments, appointmentDao.findAppointmentByDoctorIdAndDate("D1007",Date.valueOf("2022-08-01")));
		}
		
		
		@Test
		void testFindTopByOrderByAppointmentIdDesc() {
			Appointment appointment = new Appointment("P101"	,"Bhuwnesh"	,Time.valueOf("03:00:00"),	Date.valueOf("2022-08-26")	,"D1007"	,"Dr Carey"	,"Gastroenterologists",	19);
			
			assertEquals(appointment, appointmentDao.findTopByOrderByAppointmentIdDesc());
		}
	
	}
	
	@Nested
	class TestProcedureAppointmentDao{
		
		
	}
	
}
