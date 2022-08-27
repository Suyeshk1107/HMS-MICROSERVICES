package com.doctor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctor.bean.Doctor;
import com.doctor.persistence.DoctorDao;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorDao doctorDao;
	
	@Override
	public List<Doctor> getAllDoctor() {
		return doctorDao.findAll();
	}

	@Override
	public Doctor addDoctor(Doctor doctor) {
		doctor.setDoctorId(setDoctorId());		
		return doctorDao.save(doctor);
	}

	@Override
	public Doctor deleteDoctor(String doctorId) {
		Optional<Doctor> docOptional=doctorDao.findById(doctorId);
		if(docOptional.isPresent()) {
			doctorDao.deleteById(doctorId);
			return docOptional.get();
		}
		return new Doctor();
	}

	@Override
	public Doctor getDoctorById(String doctorId) {
		Optional<Doctor> doctor = doctorDao.findById(doctorId);
		if(doctor.isPresent()) {
			return doctor.get();
		}else {
			return new Doctor();
		}
	}

	@Override
	public String getLastDoctorId() {
		Doctor doctor = doctorDao.findTopByOrderByDoctorIdDesc();
		return doctor.getDoctorId();
	}

	@Override
	public String setDoctorId() {
		String lastId = getLastDoctorId();
		if(lastId != null) {
			int id = Integer.parseInt(lastId.substring(1));
			id++;
			return ("D"+id);
		}else
			return "D101";
	}

}
