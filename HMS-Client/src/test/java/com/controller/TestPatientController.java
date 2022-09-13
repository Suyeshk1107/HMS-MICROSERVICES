package com.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.ModelAndView;

import com.bean.Appointment;
import com.bean.AppointmentList;
import com.bean.Patient;
import com.bean.Schedule;
import com.bean.ScheduleList;
import com.service.AppointmentService;
import com.service.PatientService;
import com.service.ScheduleService;

@ExtendWith(MockitoExtension.class)
class TestPatientController {
	
	@InjectMocks
	@Autowired
	private PatientController patientController;

	@Mock
	private HttpSession session;
	@Mock
	private ModelAndView modelAndView;
	@Mock
	private PatientService patientService;
	@Mock
	private HttpServletRequest request;
	@Mock
	private ScheduleService scheduleService;
	@Mock
	private AppointmentService appointmentService;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Nested
	class TestshowPatientController{
		
		@Test
		void TestShowPatientController_T001() {
			
			modelAndView = new ModelAndView("ShowPatient");

			Mockito.when(session.getAttribute("userName")).thenReturn("P101");
			Mockito.when(patientService.showPatientById("P101")).thenReturn(new Patient("P101",null,null,0,null,null,null));
			
			
			assertEquals(modelAndView.getViewName(), patientController.showPatientController(session).getViewName());
			
		}
		
		@Test
		void TestShowPatientController_T002() {
			
			modelAndView = new ModelAndView("Output");
			
			Mockito.when(session.getAttribute("userName")).thenReturn("P101");
			Mockito.when(patientService.showPatientById("P101")).thenReturn(new Patient("no userName",null,null,0,null,null,null));
			
			
			assertEquals(modelAndView.getViewName(), patientController.showPatientController(session).getViewName());
			
		}
		@Test
		void TestShowPatientController_T003() {
			
			modelAndView = new ModelAndView("Output");
			
			Mockito.when(session.getAttribute("userName")).thenReturn("P101");
			Mockito.when(patientService.showPatientById("P101")).thenReturn(null);
			
			
			assertEquals(modelAndView.getViewName(), patientController.showPatientController(session).getViewName());
			
		}
	}
	
	@Test
	void testRequestAppointmentController() {
		modelAndView = new ModelAndView("requestAppointmentPage");
		assertEquals(modelAndView.getViewName(), patientController.requestAppointmentController().getViewName());
	}
	
	@Nested
	class TestGenerateAppointmentController {
		
		@Test
		void testGenerateAppointmentController_T001() {
			
			modelAndView = new ModelAndView("ShowAvailableDoctorsSchedulePage");
			List<Schedule> availableDoctorsSchedule = new ArrayList<>();
			availableDoctorsSchedule.add(new Schedule(1	,"D1000"	,"Dr Pullen"	,"Monday"	,Time.valueOf("10:00:00")	,Time.valueOf("12:00:00")));
			availableDoctorsSchedule.add(new Schedule(2	,"D1001"	,"Dr Qullen"	,"Tuesday"	,Time.valueOf("11:00:00")	,Time.valueOf("13:00:00")));
			ScheduleList sList = new ScheduleList(availableDoctorsSchedule);
			Mockito.when(request.getParameter("appointmentDate")).thenReturn("2022-09-12");
			Mockito.when(scheduleService.showAvailableDoctorSchedule(Date.valueOf("2022-09-12"))).thenReturn(sList);
			
			assertEquals(modelAndView.getViewName(),patientController.generateAppointmentController(request, session).getViewName());
		}
		@Test
		void testGenerateAppointmentController_T002() {
			
			modelAndView = new ModelAndView("Output");
			List<Schedule> availableDoctorsSchedule = new ArrayList<>();
			ScheduleList sList = new ScheduleList(availableDoctorsSchedule);
			Mockito.when(request.getParameter("appointmentDate")).thenReturn("2022-09-12");
			Mockito.when(scheduleService.showAvailableDoctorSchedule(Date.valueOf("2022-09-12"))).thenReturn(sList);
			
			assertEquals(modelAndView.getViewName(),patientController.generateAppointmentController(request, session).getViewName());
		}
		
		@Test
		void testGenerateAppointmentController_T003() {
			
			modelAndView = new ModelAndView("Output");
			List<Schedule> availableDoctorsSchedule = new ArrayList<>();
			availableDoctorsSchedule.add(new Schedule(0	,"D1000"	,"Dr Pullen"	,"Monday"	,Time.valueOf("10:00:00")	,Time.valueOf("12:00:00")));
			
			ScheduleList sList = new ScheduleList(availableDoctorsSchedule);
			Mockito.when(request.getParameter("appointmentDate")).thenReturn("2022-09-12");
			Mockito.when(scheduleService.showAvailableDoctorSchedule(Date.valueOf("2022-09-12"))).thenReturn(sList);
			
			assertEquals(modelAndView.getViewName(),patientController.generateAppointmentController(request, session).getViewName());
		}
		
	}

	@Nested
	class TestBookAppointmentController{
		
		@Test
		void testBookAppointmentController_T001() {
			
			modelAndView = new ModelAndView("ShowMyAppointments");
			
			Appointment appointment1 = new Appointment("P101",null,null,null,"D1000",null,null,0);
			Appointment appointment2 = new Appointment("P101",null,null,Date.valueOf("2022-09-12"),"D1000",null,null,0);
//			Mockito.when(request.getParameter("appointmentDate")).thenReturn("2022-09-12");
			Mockito.when(request.getParameter("dId")).thenReturn("D1000");
			Mockito.when(session.getAttribute("userName")).thenReturn("P101");
			Mockito.when(appointmentService.addAppointment(appointment1)).thenReturn(appointment2);
			
			assertEquals(modelAndView.getViewName(), patientController.bookAppointmentController(request, session).getViewName());
		}
		
		@Test
		void testBookAppointmentController_T002() {
			
			modelAndView = new ModelAndView("Output");
			
			Appointment appointment1 = new Appointment("1234",null,null,null,"D1000",null,null,0);
			Appointment appointment2 = new Appointment("no patient id",null,null,Date.valueOf("2022-09-12"),"D1000",null,null,0);

			Mockito.when(request.getParameter("dId")).thenReturn("D1000");
			Mockito.when(session.getAttribute("userName")).thenReturn("1234");
			Mockito.when(appointmentService.addAppointment(appointment1)).thenReturn(appointment2);
			
			assertEquals(modelAndView.getViewName(), patientController.bookAppointmentController(request, session).getViewName());
			
		}
		@Test
		void testBookAppointmentController_T003() {
			
			modelAndView = new ModelAndView("Output");
			
			Appointment appointment1 = new Appointment("1234",null,null,null,"D1000",null,null,0);
			
			Mockito.when(request.getParameter("dId")).thenReturn("D1000");
			Mockito.when(session.getAttribute("userName")).thenReturn("1234");
			Mockito.when(appointmentService.addAppointment(appointment1)).thenReturn(null);
			
			assertEquals(modelAndView.getViewName(), patientController.bookAppointmentController(request, session).getViewName());
			
		}
		
	}
	
	@Nested
	class cancelAppointmentController{
		
		@Test
		void testCancelAppointmentController_T001() {
			
			modelAndView = new ModelAndView("cancelAppointment");
			
			List<Appointment> appointments = new ArrayList<>();
			appointments.add(new Appointment("P105",	"P. Mishra"	,Time.valueOf("01:00:00"), Date.valueOf("2022-08-10"),	"D1003",	"Dr Ken Hurt",	"headache, fever, weakness",10));
			
			AppointmentList appointmentList = new AppointmentList(appointments);
			Mockito.when(session.getAttribute("userName")).thenReturn("P105");
			Mockito.when(appointmentService.showAllAppointmentsByPatientId("P105")).thenReturn(appointmentList);
			
			assertEquals(modelAndView.getViewName(), patientController.cancelAppointmentController(session).getViewName());
			
		}
		@Test
		void testCancelAppointmentController_T002() {
			
			modelAndView = new ModelAndView("Output");
			
			List<Appointment> appointments = new ArrayList<>();
			appointments.add(new Appointment("P105",	"P. Mishra"	,Time.valueOf("01:00:00"), Date.valueOf("2022-08-10"),	"D1003",	"Dr Ken Hurt",	"headache, fever, weakness",10));
			
			AppointmentList appointmentList = new AppointmentList(appointments);
			Mockito.when(session.getAttribute("userName")).thenReturn("1234");
			Mockito.when(appointmentService.showAllAppointmentsByPatientId("1234")).thenReturn(appointmentList);
			
			assertEquals(modelAndView.getViewName(), patientController.cancelAppointmentController(session).getViewName());
			
		}
		@Test
		void testCancelAppointmentController_T003() {
			
			modelAndView = new ModelAndView("Output");
			
			List<Appointment> appointments = new ArrayList<>();
//			appointments.add(new Appointment("P105",	"P. Mishra"	,Time.valueOf("01:00:00"), Date.valueOf("2022-08-10"),	"D1003",	"Dr Ken Hurt",	"headache, fever, weakness",10));
			
			AppointmentList appointmentList = new AppointmentList(appointments);
			Mockito.when(session.getAttribute("userName")).thenReturn("15234");
			Mockito.when(appointmentService.showAllAppointmentsByPatientId("15234")).thenReturn(appointmentList);
			
			assertEquals(modelAndView.getViewName(), patientController.cancelAppointmentController(session).getViewName());
			
		}
		
	}
	
	@Nested
	class TestDeleteAppointmentController{
		
		
		@Test
		void testDeleteAppointmentController() {
	
			Appointment appointment = new Appointment("1234",null,null,null,"D1000",null,null,0);
			modelAndView = new ModelAndView("Output");
			Mockito.when(request.getParameter("appointmentId")).thenReturn("1");
			Mockito.when(appointmentService.deleteAppointmentById(1)).thenReturn(appointment);
		
			assertEquals(modelAndView.getViewName(), patientController.deleteAppointmentController(request, session).getViewName());
		}
	}
	
	@Nested
	class TestViewAllAppointmentsController{
		
		@Test
		void testViewAllAppointmentsController_T001() {
			
			modelAndView = new ModelAndView("ShowMyAppointments");
			
			List<Appointment> appointments = new ArrayList<>();
			appointments.add(new Appointment("P105",	"P. Mishra"	,Time.valueOf("01:00:00"), Date.valueOf("2022-08-10"),	"D1003",	"Dr Ken Hurt",	"headache, fever, weakness",10));
			
			AppointmentList appointmentList = new AppointmentList(appointments);
		
			Mockito.when(session.getAttribute("userName")).thenReturn("P105");
			Mockito.when(appointmentService.showAllAppointmentsByPatientId("P105")).thenReturn(appointmentList);
			
			assertEquals(modelAndView.getViewName(), patientController.viewAllAppointmentsController(session).getViewName());
		}
		@Test
		void testViewAllAppointmentsController_T002() {
			
			modelAndView = new ModelAndView("Output");
			
			List<Appointment> appointments = new ArrayList<>();
			appointments.add(new Appointment("P110",	"P. Mishra"	,Time.valueOf("01:00:00"), Date.valueOf("2022-08-10"),	"D1003",	"Dr Ken Hurt",	"headache, fever, weakness",10));
			
			AppointmentList appointmentList = new AppointmentList(appointments);
			
			Mockito.when(session.getAttribute("userName")).thenReturn("P105");
			Mockito.when(appointmentService.showAllAppointmentsByPatientId("P105")).thenReturn(appointmentList);
			
			assertEquals(modelAndView.getViewName(), patientController.viewAllAppointmentsController(session).getViewName());
		}
		@Test
		void testViewAllAppointmentsController_T003() {
			
			modelAndView = new ModelAndView("Output");
			
			List<Appointment> appointments = new ArrayList<>();
			
			AppointmentList appointmentList = new AppointmentList(appointments);
			
			Mockito.when(session.getAttribute("userName")).thenReturn("P105");
			Mockito.when(appointmentService.showAllAppointmentsByPatientId("P105")).thenReturn(appointmentList);
			
			assertEquals(modelAndView.getViewName(), patientController.viewAllAppointmentsController(session).getViewName());
		}
	}
	
	@Nested
	class TestRescheduleAppointmentController{
		
	}
}
