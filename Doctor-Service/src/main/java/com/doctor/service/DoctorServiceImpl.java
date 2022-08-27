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
	public boolean addDoctor(Doctor doctor) {
		Doctor doctor2 = new Doctor();
		doctor2.setDoctorId(setDoctorId());
		doctor2.setDoctorName(doctor.getDoctorName());
		doctor2.setDoctorGender(doctor.getDoctorGender());
		doctor2.setDoctorAge(doctor.getDoctorAge());
		doctor2.setDoctorAddress(doctor.getDoctorAddress());
		doctor2.setDoctorContact(doctor.getDoctorContact());
		doctor2.setDoctorDepartment(doctor.getDoctorDepartment());
		doctor2.setDoctorExperience(doctor.getDoctorExperience());
		
		if(doctorDao.save(doctor2) != null)
			return true;
		else
			return false;
	}

	@Override
	public boolean deleteDoctor(String doctorId) {
		Doctor doctor = getDoctorById(doctorId);
		if(doctor != null) {
			doctorDao.deleteById(doctorId);
			return true;
		}
		else
			return false;
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
