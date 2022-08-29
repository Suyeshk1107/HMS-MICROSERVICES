package com.service;

import java.util.List;

import com.bean.Appointment;

public interface HmsClientService {

	boolean isValid(String id, String password);

	boolean registerUser(String userId, String password);

	boolean rescheduleAppointment(Appointment appointment);

	List<Appointment> showAllAppointmentsByPatientId(String pId);

	List<Appointment> showAllAppointmentsByDoctorId(String dId);

	List<Appointment> showAllAppointments();

	boolean requestAppointment(Appointment appointment);

}
