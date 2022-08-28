package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bean.Doctor;
import com.bean.DoctorList;
import com.bean.Patient;
import com.bean.PatientList;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class PatientService {

	@Autowired
	private RestTemplate restTemplate;
	
	@CircuitBreaker(name = "allPatient",fallbackMethod ="showAllPatientFallBack" )
	public PatientList showAllPatient() {
		return restTemplate.getForObject("http://patient-service/patients", PatientList.class);
	}
	
	public PatientList showAllPatientFallBack(Exception e) {
		List<Patient> patientList = new ArrayList<Patient>();
		patientList.add(new Patient("no id","no name","no gender",0,"no contact","no address","no symptoms"));
		return new PatientList(patientList);
	}
	
	@CircuitBreaker(name = "Patient",fallbackMethod ="showPatientByIdFallBack" )
	public Patient showPatientById(String patientId) {
		return restTemplate.getForObject("http://patient-service/patients/"+patientId, Patient.class);
	}
	public Patient showPatientByIdFallBack(Exception e) {
		return new Patient("no id","no name","no gender",0,"no contact","no address","no symptoms");
	}
	
	@CircuitBreaker(name = "savePatient",fallbackMethod ="addPatientFallBack" )
	public Patient addPatient(Patient patient) {
		return restTemplate.postForObject("http://patient-service/patients",patient, Patient.class);
	}
	public Patient addPatientFallBack(Exception e) {
		return new Patient("no id","no name","no gender",0,"no contact","no address","no symptoms");
	}
	
	@CircuitBreaker(name = "removePatient",fallbackMethod ="deletePatientFallBack" )
	public Patient deletePatient(String patientId) {
		restTemplate.delete("http://patient-service/patients/"+patientId);
		return restTemplate.getForObject("http://patient-service/patients/"+patientId, Patient.class);
	}
	public Patient deletePatientFallBack(Exception e) {
		return new Patient("no id","no name","no gender",0,"no contact","no address","no symptoms");
	}
}
