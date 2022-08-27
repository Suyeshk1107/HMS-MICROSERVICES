package com.patient.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.patient.bean.PatientList;
import com.patient.bean.Patient;
import com.patient.service.PatientService;

@RestController
public class PatientResource {

	@Autowired
	private PatientService patientService;
	
	@GetMapping(path = "/patients", produces = MediaType.APPLICATION_JSON_VALUE)
	public PatientList getAllPatients() {
		return new PatientList(patientService.getAllPatient());
	}
	
	@GetMapping(path = "/patients/{pId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Patient getPatientById(@PathVariable("pId") String patientId) {
		return patientService.getPatientById(patientId);
	}
	
	@PostMapping(path = "/patients", produces = MediaType.APPLICATION_JSON_VALUE)
	public Patient savePatient(@RequestBody Patient patient) {
		return patientService.addPatient(patient);
	}
	
	@DeleteMapping(path = "/patients/remove/{pId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Patient removeDoctorById(@PathVariable("pId") String patientId) {
		return patientService.deletePatient(patientId);
	}
}
