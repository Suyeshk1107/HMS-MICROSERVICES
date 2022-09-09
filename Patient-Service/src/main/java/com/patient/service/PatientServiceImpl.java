package com.patient.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.bean.Patient;
import com.patient.persistence.PatientDao;
@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientDao patientDao;
	
	@Override
	public List<Patient> getAllPatient() {
		// TODO Auto-generated method stub
		return patientDao.findAll();
	}

	@Override
	public Patient addPatient(Patient patient) {
		if(patient != null)
			patient.setPatientId(setNewPatientId());
		return patientDao.save(patient);
	}

	@Override
	public Patient deletePatient(String patientId) {
		Optional<Patient> patientOptional=patientDao.findById(patientId);
		if(patientOptional !=null && patientOptional.isPresent()) {
			patientDao.deleteById(patientId);
			return patientOptional.get();
		}
		return new Patient();
	}

	@Override
	public Patient getPatientById(String patientId) {
		Optional<Patient> patient = patientDao.findById(patientId);
		if(patient.isPresent()) {
			return patient.get();
		}else {
			return new Patient();
		}
	}

	@Override
	public String getLastPatientId() {
		Patient patient = patientDao.findTopByOrderByPatientIdDesc();
		if(patient != null)
			return patient.getPatientId();
		return null;
	}

	@Override
	public String setNewPatientId() {
		String lastId = getLastPatientId();
		if(lastId != null) {
			int id = Integer.parseInt(lastId.substring(1));
			++id;
			return ("P"+id);
		}else
			return "P101";
	}

}
