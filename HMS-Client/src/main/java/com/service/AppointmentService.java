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
	
	@CircuitBreaker(name = "appointments",fallbackMethod ="showAllAppointmentsFallBack" )
	public AppointmentList showAllAppointments() {
		return restTemplate.getForObject("http://appointment-service/appointments", AppointmentList.class);
	}
	
	@CircuitBreaker(name = "appointments",fallbackMethod ="showAllAppointmentsFallBack" )
	public AppointmentList showAllAppointmentsByPatientId(String pId) {
		return restTemplate.getForObject("http://appointment-service/appointments/p/"+pId, AppointmentList.class);
	}
	
	
	@CircuitBreaker(name = "appointments",fallbackMethod ="showAllAppointmentsFallBack" )
	public AppointmentList showAllAppointmentsByDoctorId(String dId) {
		return restTemplate.getForObject("http://appointment-service/appointments/d/"+dId, AppointmentList.class);
	}

	public AppointmentList showAllAppointmentsFallBack(Exception e) {
		List<Appointment> appointments = new ArrayList<Appointment>();
		appointments.add(new Appointment("no pID","no pName",Time.valueOf(LocalTime.now()), Date.valueOf(LocalDate.now()), "no dID", "no dName", "no department",0));
		return new AppointmentList(appointments);
	}
	
	@CircuitBreaker(name = "appointments",fallbackMethod ="showAppointmentByIdFallBack" )
	public Appointment showAppointmentsById(int appointmentId) {
		return restTemplate.getForObject("http://appointment-service/appointments/"+appointmentId, Appointment.class);
	}
	public Appointment showAppointmentByIdFallBack(Exception e) {
		return new Appointment("no pID","no pName",Time.valueOf(LocalTime.now()), Date.valueOf(LocalDate.now()), "no dID", "no dName", "no department",0);
	}
	
	@CircuitBreaker(name = "appointments",fallbackMethod ="showLatestAppointmentFallBack" )
	public Appointment showLatestAppointment() {
		return restTemplate.getForObject("http://appointment-service/appointments/latest", Appointment.class);
	}
	
	public Appointment showLatestAppointmentFallBack(Exception e) {
		
		return new Appointment("no pID","no pName",Time.valueOf(LocalTime.now()), Date.valueOf(LocalDate.now()), "no dID", "no dName", "no department",0);
	}

	@CircuitBreaker(name = "appointments",fallbackMethod ="addAppointmentFallBack" )
	public Appointment addAppointment(Appointment appointment) {
		return restTemplate.postForObject("http://appointment-service/appointments/",appointment, Appointment.class);
	}
	
	public Appointment addAppointmentFallBack(Exception e) {
		return new Appointment("no pID","no pName",Time.valueOf(LocalTime.now()), Date.valueOf(LocalDate.now()), "no dID", "no dName", "no department",0);
	}
	
	
	@CircuitBreaker(name = "appointments",fallbackMethod ="deleteAppointmentFallBack" )
	public Appointment deleteAppointmentById(int appointmentId) {
		restTemplate.delete("http://appointment-service/appointments/"+appointmentId);
		return restTemplate.getForObject("http://appointment-service/appointments/"+appointmentId, Appointment.class);
	}
	public Appointment deleteAppointmentFallBack(Exception e) {
		return new Appointment("no pID","no pName",Time.valueOf(LocalTime.now()), Date.valueOf(LocalDate.now()), "no dID", "no dName", "no department",0);
	}
	
	@CircuitBreaker(name = "appointments",fallbackMethod ="modifyAppointmentFallBack" )
	public Appointment modifyAppointment(Appointment appointment) {
		restTemplate.put("http://appointment-service/appointments/"+appointment.getAppointmentId(), appointment, Appointment.class);
		return restTemplate.getForObject("http://appointment-service/appointments/"+appointment.getAppointmentId(), Appointment.class);
	}
	
	public Appointment modifyAppointmentFallBack(Appointment appointment) {
		return new Appointment("no pID","no pName",Time.valueOf(LocalTime.now()), Date.valueOf(LocalDate.now()), "no dID", "no dName", "no department",0);
	}
	
}
