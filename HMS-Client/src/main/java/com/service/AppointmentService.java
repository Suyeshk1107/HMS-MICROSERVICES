package com.service;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bean.Appointment;
import com.bean.AppointmentList;
import com.bean.Patient;

import java.sql.Date;
import java.sql.Time;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class AppointmentService {

	@Autowired
	private RestTemplate restTemplate;
	
	@CircuitBreaker(name = "allAppointments",fallbackMethod ="showAllAppointmentsFallBack" )
	public AppointmentList showAllAppointments() {
		return restTemplate.getForObject("http://appointment-service/appointments", AppointmentList.class);
	}
	
	public AppointmentList showAllAppointmentsFallBack(Exception e) {
		List<Appointment> appointments = new ArrayList<Appointment>();
		appointments.add(new Appointment("no pID","no pName",Time.valueOf(LocalTime.now()), Date.valueOf(LocalDate.now()), "no dID", "no dName", "no department",0));
		return new AppointmentList(appointments);
	}

//	circuit breaker pending
	public AppointmentList showAllAppointmentsByPatientId(String pId) {
		return restTemplate.getForObject("http://appointment-service/appointments/p/"+pId, AppointmentList.class);
	}
	
	
//	circuit breaker pending
	public AppointmentList showAllAppointmentsByDoctorId(String dId) {
		return restTemplate.getForObject("http://appointment-service/appointments/d/"+dId, AppointmentList.class);
	}
	
	
	
	@CircuitBreaker(name = "Appointment",fallbackMethod ="showAppointmentByIdFallBack" )
	public Appointment showAppointmentsById(String appointmentId) {
		return restTemplate.getForObject("http://appointment-service/appointments/"+appointmentId, Appointment.class);
	}
	public Appointment showAppointmentByIdFallBack(Exception e) {
//		return new Appointment();
		return new Appointment("no pID","no pName",Time.valueOf(LocalTime.now()), Date.valueOf(LocalDate.now()), "no dID", "no dName", "no department",0);
	}
	
	@CircuitBreaker(name = "saveAppointment",fallbackMethod ="addAppointmentFallBack" )
	public Appointment addAppointment(Appointment appointment) {
		return restTemplate.postForObject("http://appointment-service/appointments",appointment, Appointment.class);
	}
	public Appointment addAppointmentFallBack(Exception e) {
		return new Appointment("no pID","no pName",Time.valueOf(LocalTime.now()), Date.valueOf(LocalDate.now()), "no dID", "no dName", "no department",0);
	}
	
	@CircuitBreaker(name = "removeAppointment",fallbackMethod ="deleteAppointmentFallBack" )
	public Appointment deleteAppointmentById(int appointmentId) {
		restTemplate.delete("http://patient-service/patients/"+appointmentId);
		return restTemplate.getForObject("http://appointment-service/appointments/"+appointmentId, Appointment.class);
	}
	public Appointment deleteAppointmentFallBack(Exception e) {
		return new Appointment("no pID","no pName",Time.valueOf(LocalTime.now()), Date.valueOf(LocalDate.now()), "no dID", "no dName", "no department",0);
	}
	
//	modify appointment pending
	
	
}