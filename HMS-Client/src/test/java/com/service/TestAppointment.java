package com.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.bean.Appointment;
import com.bean.AppointmentList;

@ExtendWith(MockitoExtension.class)
class TestAppointment {
	
	@Autowired
	@InjectMocks
	private AppointmentService appointmentService;

	@Mock
	private RestTemplate restTemplate;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Nested
	class TestShowAllAppointments{
		
		@Test
		void testShowAllAppointments_T001() {
			List<Appointment> appointments = new ArrayList<>();
			appointments.add(new Appointment("P105",	"P. Mishra"	,Time.valueOf("01:00:00"), Date.valueOf("2022-08-10"),	"D1003",	"Dr Ken Hurt",	"headache, fever, weakness",10));
			
			AppointmentList appointmentList = new AppointmentList(appointments);
			
			Mockito.when(restTemplate.getForObject("http://appointment-service/appointments", AppointmentList.class)).thenReturn(appointmentList);
			
			assertEquals(appointmentList, appointmentService.showAllAppointments());
		}
		
		@Test
		void testShowAllAppointments_T002() {
			
			Mockito.when(restTemplate.getForObject("http://appointment-service/appointments", AppointmentList.class)).thenReturn(null);
			
			assertEquals(null, appointmentService.showAllAppointments());
		}
		
	}

	@Nested
	class TestShowAllAppointmentsByPatientId{
		
		@Test
		void testShowAllAppointmentsByPatientId_T001() {
		
			List<Appointment> appointments = new ArrayList<>();
			appointments.add(new Appointment("P105",	"P. Mishra"	,Time.valueOf("01:00:00"), Date.valueOf("2022-08-10"),	"D1003",	"Dr Ken Hurt",	"headache, fever, weakness",10));
			
			AppointmentList appointmentList = new AppointmentList(appointments);
			
			
			Mockito.when(restTemplate.getForObject("http://appointment-service/appointments/p/"+"P105", AppointmentList.class)).thenReturn(appointmentList);
			
			assertEquals(appointmentList, appointmentService.showAllAppointmentsByPatientId("P105"));
		}
		
		@Test
		void testShowAllAppointmentsByPatientId_T002() {
		
			Mockito.when(restTemplate.getForObject("http://appointment-service/appointments/p/"+"15", AppointmentList.class)).thenReturn(null);
			
			assertEquals(null, appointmentService.showAllAppointmentsByPatientId("15"));
		}
	}
	
	@Nested
	class TestShowAllAppointmentsByDoctorId{
		
		@Test
		void testShowAllAppointmentsByDoctorId_T001() {
		
			List<Appointment> appointments = new ArrayList<>();
			appointments.add(new Appointment("P105",	"P. Mishra"	,Time.valueOf("01:00:00"), Date.valueOf("2022-08-10"),	"D1003",	"Dr Ken Hurt",	"headache, fever, weakness",10));
			
			AppointmentList appointmentList = new AppointmentList(appointments);
			
			
			Mockito.when(restTemplate.getForObject("http://appointment-service/appointments/d/"+"D1003", AppointmentList.class)).thenReturn(appointmentList);
			
			assertEquals(appointmentList, appointmentService.showAllAppointmentsByDoctorId("D1003"));
		}
		
		@Test
		void testShowAllAppointmentsByDoctorId_T002() {
		
			Mockito.when(restTemplate.getForObject("http://appointment-service/appointments/d/"+"D123", AppointmentList.class)).thenReturn(null);
			
			assertEquals(null, appointmentService.showAllAppointmentsByDoctorId("D123"));
		}
		
	}
	
	@Nested
	class TestShowAppointmentsById{
		
		@Test
		void testShowAppointmentsById_T001() {
			Appointment appointment = new Appointment("P105",	"P. Mishra"	,Time.valueOf("01:00:00"), Date.valueOf("2022-08-10"),	"D1003",	"Dr Ken Hurt",	"headache, fever, weakness",10);
			
			Mockito.when(restTemplate.getForObject("http://appointment-service/appointments/"+10, Appointment.class)).thenReturn(appointment);
			
			assertEquals(appointment, appointmentService.showAppointmentsById(10));
		}
		

		@Test
		void testShowAppointmentsById_T002() {
			
			Mockito.when(restTemplate.getForObject("http://appointment-service/appointments/"+(-134), Appointment.class)).thenReturn(null);
			
			assertEquals(null, appointmentService.showAppointmentsById(-134));
		}
	}
	
	@Nested
	class TestAddAppointment{
		
		@Test
		void testAddAppointment_T001() {
			Appointment appointment = new Appointment("P105",	"P. Mishra"	,Time.valueOf("01:00:00"), Date.valueOf("2022-08-10"),	"D1003",	"Dr Ken Hurt",	"headache, fever, weakness",10);
			
			Mockito.when(restTemplate.postForObject("http://appointment-service/appointments/",appointment, Appointment.class)).thenReturn(appointment);
			
			assertEquals(appointment, appointmentService.addAppointment(appointment));
		}
		
		@Test
		void testAddAppointment_T002() {
			
			Mockito.when(restTemplate.postForObject("http://appointment-service/appointments/",null, Appointment.class)).thenReturn(null);
			
			assertEquals(null, appointmentService.addAppointment(null));
		}
		
	}
	
	
	@Nested
	class TestDeleteAppointmentById{
		
		
		@Test
		void deleteAppointmentById_T001() {
			Appointment appointment = new Appointment("P105",	"P. Mishra"	,Time.valueOf("01:00:00"), Date.valueOf("2022-08-10"),	"D1003",	"Dr Ken Hurt",	"headache, fever, weakness",10);
			
			Mockito.when(restTemplate.getForObject("http://appointment-service/appointments/"+10, Appointment.class)).thenReturn(appointment);
			
			assertEquals(appointment, appointmentService.deleteAppointmentById(10));
		}
		
		@Test
		void deleteAppointmentById_T002() {
			Mockito.when(restTemplate.getForObject("http://appointment-service/appointments/"+(-134), Appointment.class)).thenReturn(null);
			
			assertEquals(null, appointmentService.deleteAppointmentById(-134));
		}
		
	}
	
	
	@Nested
	class TestModifyAppointment{
		
		@Test
		void testModifyAppointment_T001() {
			
			Appointment appointment = new Appointment("P105",	"P. Mishra"	,Time.valueOf("01:00:00"), Date.valueOf("2022-08-10"),	"D1003",	"Dr Ken Hurt",	"headache, fever, weakness",10);
			
			Mockito.when(restTemplate.getForObject("http://appointment-service/appointments/"+10, Appointment.class)).thenReturn(appointment);
			
			assertEquals(appointment, appointmentService.modifyAppointment(appointment));
			
		}
		
		@Test
		void testModifyAppointment_T002() {
			
			
			Mockito.when(restTemplate.getForObject("http://appointment-service/appointments/"+(-134), Appointment.class)).thenReturn(null);
			
			assertEquals(null, appointmentService.modifyAppointment(null));
			
		}
	}
}
