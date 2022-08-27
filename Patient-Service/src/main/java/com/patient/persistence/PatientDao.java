package com.patient.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patient.bean.Patient;


@Repository
public interface PatientDao extends JpaRepository<Patient, String>{
	
	
//	@Query("SELECT p FROM Patient p ORDER BY p.patientId DESC LIMIT 1")
//	Patient getLastPatientDetails();
	
	Patient findTopByOrderByPatientIdDesc();

//	List<Patient> getPatientList();
	
//	List<Patient> findAllPatient();
//	
//	boolean addPatient(Patient patient);
	
//	Patient savePatient(Patient patient);
	
//	boolean removePatient(String patientId);
	
//	Patient
	
//	Patient getPatientById(String patientId);
//
//	int getLastPId();
		
}
