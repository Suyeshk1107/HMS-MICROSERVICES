package com.doctor.service;

import java.util.List;

import com.doctor.bean.Doctor;

public interface DoctorService {
	
	List<Doctor> getAllDoctor();
	
	boolean addDoctor(Doctor doctor);
	
	boolean deleteDoctor(String doctorId);

	Doctor getDoctorById(String doctorId);
	
	String getLastDoctorId();
	
	String setDoctorId();

}
