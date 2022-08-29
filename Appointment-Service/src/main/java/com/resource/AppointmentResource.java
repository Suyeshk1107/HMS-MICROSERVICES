package com.resource;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.Appointment;
import com.bean.AppointmentList;
import com.service.AppointmentService;

@RestController
public class AppointmentResource {

	
	@Autowired
	private AppointmentService appointmentService;
	
	@GetMapping(path="/appointments",produces = MediaType.APPLICATION_JSON_VALUE)
	public AppointmentList getAllAppointments() {
		
		return new AppointmentList(appointmentService.getAllAppointments());
	}
	
	@GetMapping(path = "/appointments/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Appointment getAppointmentById(@PathVariable("id") int id) {
		return appointmentService.getAppointmentById(id);
	}
	
	@GetMapping(path = "/appointments/p/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AppointmentList getAppointmentsPatientById(@PathVariable("id") String id) {
		return new AppointmentList(appointmentService.getAllAppointmentsByPatientId(id));
	}
	
	@GetMapping(path = "/appointments/d/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AppointmentList getAppointmentsByDoctorId(@PathVariable("id") String id) {
		return new AppointmentList(appointmentService.getAllAppointmentsByDoctorId(id));
	}

	@PostMapping(path="/appointments",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public Appointment saveAppointmentResource(@RequestBody Appointment appointment) {
		return appointmentService.addAppointment(appointment.getPatientId(),appointment.getDoctorId(),appointment.getDate());
	}
	
	@DeleteMapping(path = "/appointments/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Appointment deleteAppointmentResource(@PathVariable("id") int id) {
		return appointmentService.deleteAppointment(id);
	}
	
	@PatchMapping(path = "/appointments/{id}/{date}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Appointment rescheduleAppointmentResource(@PathVariable("id") int id,@PathVariable("date")Date date) {
		return appointmentService.modifyAppointment(id,date);
	}
	
	
	
}
