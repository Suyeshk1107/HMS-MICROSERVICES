package com.service;

import java.sql.Date;
import java.util.List;

import com.bean.Appointment;

public interface AppointmentService {

	public Appointment addAppointment(String patientId, String doctorId, Date date);
	
	public Appointment deleteAppointment(int appoointmentId);
	
	public Appointment modifyAppointment(Appointment appointment);

	List<Appointment> getAllAppointments();

	Appointment getAppointmentById(int id);

	List<Appointment> getAllAppointmentsByPatientId(String pId);

	List<Appointment> getAllAppointmentsByDoctorId(String dId);

	Appointment getLatestAppointment();
}
