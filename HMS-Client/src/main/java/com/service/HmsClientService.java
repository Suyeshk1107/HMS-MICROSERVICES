package com.service;

import java.sql.Date;
import java.util.List;

import com.bean.Appointment;
import com.bean.Patient;
import com.bean.Schedule;

public interface HmsClientService {

	boolean isValid(String id, String password);

	boolean registerUser(String userId, String password);

	boolean rescheduleAppointment(Appointment appointment);

	List<Appointment> showAllAppointmentsByPatientId(String pId);

	List<Appointment> showAllAppointmentsByDoctorId(String dId);

	List<Appointment> showAllAppointments();

	boolean requestAppointment(Appointment appointment);

	Patient addPatientToDatabase(Patient patient);

	Patient getPatientById(String pId);

	List<Schedule> getAvailableDoctorSchedule(Date date);
	
}
