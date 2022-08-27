package com.service;

import java.sql.Date;
import java.util.List;

import com.bean.Appointment;

public interface AppointmentService {

	public Appointment addAppointment(String patientId, String doctorId, Date date);
	
	public Appointment deleteAppointment(int appoointmentId);
	
	public Appointment modifyAppointment(int id, Date date);

	List<Appointment> getAllAppointments();

	Appointment getAppointmentById(int id);
}
