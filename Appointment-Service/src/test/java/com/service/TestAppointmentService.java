package com.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.Time;
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

import com.bean.Appointment;
import com.bean.ProcedureAppointment;
import com.persistence.AppointmentDao;
import com.persistence.ProcedureAppointmentDao;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestAppointmentService {

	@InjectMocks
	@Autowired
	private AppointmentServiceImpl appointmentServiceImpl;

	@Mock
	private AppointmentDao appointmentDao;
	@Mock
	private ProcedureAppointmentDao procedureAppointmentDao;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Nested
	class TestGetAllAppointmentsByPatientId {

		@Test
		void testGetAllAppointmentsByPatientId_T001() {
			List<Appointment> appointments = new ArrayList<>();
			appointments.add(new Appointment("P105", "P. Mishra", Time.valueOf("01:00:00"), Date.valueOf("2022-08-10"),
					"D1003", "Dr Ken Hurt", "headache, fever, weakness", 10));

			Mockito.when(appointmentDao.getAllAppointmentsByPatientId("P105")).thenReturn(appointments);

			assertIterableEquals(appointments, appointmentServiceImpl.getAllAppointmentsByPatientId("P105"));

		}

		@Test
		void testGetAllAppointmentsByPatientId_T002() {

			List<Appointment> appointments = new ArrayList<>();

			Mockito.when(appointmentDao.getAllAppointmentsByPatientId("P109")).thenReturn(appointments);

			assertIterableEquals(appointments, appointmentServiceImpl.getAllAppointmentsByPatientId("P109"));
		}

	}

	@Nested
	class TestGetAllAppointmentsByDoctorId {

		@Test
		void testGetAllAppointmentsByDoctorId_T001() {

			List<Appointment> appointments = new ArrayList<>();
			appointments.add(new Appointment("P105", "P. Mishra", Time.valueOf("01:00:00"), Date.valueOf("2022-08-10"),
					"D1003", "Dr Ken Hurt", "headache, fever, weakness", 10));

			Mockito.when(appointmentDao.getAllAppointmentsByDoctorId("D1003")).thenReturn(appointments);

			assertIterableEquals(appointments, appointmentServiceImpl.getAllAppointmentsByDoctorId("D1003"));
		}

//		@Test
//		void testGetAllAppointmentsByDoctorId_T002() {
//
//			List<Appointment> appointments = new ArrayList<>();
//			Mockito.when(appointmentDao.getAllAppointmentsByPatientId("D1000")).thenReturn(appointments);
//
//			assertIterableEquals(appointments, appointmentServiceImpl.getAllAppointmentsByDoctorId("D1000"));
//		}

	}

	@Nested
	class TestGetAllAppointments {

		@Test
		void testGetAllAppointments_T001() {
			List<Appointment> appointments = new ArrayList<>();
			appointments.add(new Appointment("P105", "P. Mishra", Time.valueOf("01:00:00"), Date.valueOf("2022-08-10"),
					"D1003", "Dr Ken Hurt", "headache, fever, weakness", 10));

			Mockito.when(appointmentDao.findAll()).thenReturn(appointments);

			assertIterableEquals(appointments, appointmentServiceImpl.getAllAppointments());
		}

		@Test
		void testGetAllAppointments_T002() {

			List<Appointment> appointments = new ArrayList<>();
			Mockito.when(appointmentDao.findAll()).thenReturn(appointments);

			assertIterableEquals(appointments, appointmentServiceImpl.getAllAppointments());
		}

	}

	@Nested
	class TestGetAppointmentById {

		@Test
		void testGetAppointmentById_T001() {
			Appointment appointment = new Appointment("P105", "P. Mishra", Time.valueOf("01:00:00"),
					Date.valueOf("2022-08-10"), "D1003", "Dr Ken Hurt", "headache, fever, weakness", 10);

			Mockito.when(appointmentDao.findById(10)).thenReturn(Optional.of(appointment));

			assertEquals(appointment, appointmentServiceImpl.getAppointmentById(10));

		}

		@Test
		void testGetAppointmentById_T002() {
			Appointment appointment = new Appointment();

			Mockito.when(appointmentDao.findById(-3)).thenReturn(Optional.of(appointment));

			assertEquals(appointment, appointmentServiceImpl.getAppointmentById(-3));

		}

	}

	@Nested
	class TestGetLatestAppointment {

		@Test
		void testGetLatestAppointment_T001() {
			Appointment appointment = new Appointment("P105", "P. Mishra", Time.valueOf("01:00:00"),
					Date.valueOf("2022-08-10"), "D1003", "Dr Ken Hurt", "headache, fever, weakness", 10);
			Mockito.when(appointmentDao.findTopByOrderByAppointmentIdDesc()).thenReturn(appointment);

			assertEquals(appointment, appointmentServiceImpl.getLatestAppointment());
		}

		@Test
		void testGetLatestAppointment_T002() {
			Mockito.when(appointmentDao.findTopByOrderByAppointmentIdDesc()).thenReturn(null);

			assertEquals(null, appointmentServiceImpl.getLatestAppointment());
		}

	}

	@Nested
	class TestAddAppointment {

		@Test
		void testAddAppointment_T001() {

			List<Appointment> appointments = new ArrayList<>();

			Appointment appointment = new Appointment(null, null, null, null, null, null, null, 20);
			ProcedureAppointment pA = new ProcedureAppointment(31, "P103", "Krishna", Time.valueOf("04:00:00"),
					Time.valueOf("06:00:00"), "D1008", "Dr Nervo", "Cardiologists");

//			appointments.add(new Appointment());

			Mockito.when(appointmentDao.callProcedure("P103", "D1008")).thenReturn(1);
			Mockito.when(procedureAppointmentDao.findTopByOrderByProcedureIdDesc()).thenReturn(pA);
			Mockito.when(appointmentDao.findAppointmentByDoctorIdAndDate("D1008", Date.valueOf("2022-10-09")))
					.thenReturn(appointments);

			Mockito.when(appointmentDao.findTopByOrderByAppointmentIdDesc())
					.thenReturn(new Appointment(null, null, null, null, null, null, null, 20));

			Mockito.when(appointmentDao.findById(20)).thenReturn(Optional.of(appointment));

			assertEquals(appointment,
					appointmentServiceImpl.addAppointment("P103", "D1008", Date.valueOf("2022-10-09")));

		}

//		negative scenarios pending

	}

	@Nested
	class TestDeleteAppointment {

		@Test
		void testDeleteAppointment_T001() {

			Appointment appointment = new Appointment("P101", "Bhuwnesh", Time.valueOf("12:40:00"),
					Date.valueOf("2022-08-25"), "D1009", "Dr Hurt", "Gastroenterologists", 6);

			Mockito.when(appointmentDao.findById(6)).thenReturn(Optional.of(appointment));
			assertEquals(appointment, appointmentServiceImpl.deleteAppointment(6));
		}

		@Test
		void testDeleteAppointment_T002() {

			Appointment appointment = new Appointment();

			Mockito.when(appointmentDao.findById(8)).thenReturn(Optional.ofNullable(null));
			assertEquals(appointment, appointmentServiceImpl.deleteAppointment(8));
		}

	}

	@Nested
	class TestModifyAppointment {
//	TestModifyAppointment pending

	}

}
