package com.patient.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.patient.bean.Patient;
@Service
public interface PatientService {
	
	List<Patient> getAllPatient();
	
	Patient addPatient(Patient patient);
	
	Patient deletePatient(String patientId);

	Patient getPatientById(String patientId);

	String getLastPatientId();
	
	String setNewPatientId();

//	List<Appointment> getMyAppointments(String pid);

//	boolean rescheduleAppointment(int aid, Date newDate);

//	boolean cancelAppointmentRequest(int aid);

}