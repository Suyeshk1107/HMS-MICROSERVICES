package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bean.Doctor;
import com.bean.DoctorList;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class DoctorService {

	@Autowired
	private RestTemplate restTemplate;
	
	@CircuitBreaker(name = "allDoctor",fallbackMethod ="showAllDoctorFallBack" )
	public DoctorList showAllDoctor() {
		return restTemplate.getForObject("http://doctor-service/doctors", DoctorList.class);
	}
	
	public DoctorList showAllDoctorFallBack(Exception e) {
		List<Doctor> docList = new ArrayList<Doctor>();
//		docList.add(new Doctor("no id","no name","no department",0,"no gender",0,"no contact","no address"));
		docList.add(new Doctor());
		return new DoctorList(docList);
	}
	
	@CircuitBreaker(name = "Doctor",fallbackMethod ="showDoctorByIdFallBack" )
	public Doctor showDoctorById(String doctorId) {
		return restTemplate.getForObject("http://doctor-service/doctors/"+doctorId, Doctor.class);
	}
	public Doctor showDoctorByIdFallBack(Exception e) {
//		return new Doctor("no id","no name","no department",0,"no gender",0,"no contact","no address");
		return new Doctor();
	}
	
	@CircuitBreaker(name = "saveDoctor",fallbackMethod ="addDoctorFallBack" )
	public Doctor addDoctor(Doctor doctor) {
		return restTemplate.postForObject("http://doctor-service/doctors",doctor, Doctor.class);
	}
	public Doctor addDoctorFallBack(Exception e) {
//		return new Doctor("no id","no name","no department",0,"no gender",0,"no contact","no address");
		return new Doctor();
	}
	
	@CircuitBreaker(name = "removeDoctor",fallbackMethod ="deleteDoctorFallBack" )
	public Doctor deleteDoctor(String doctorId) {
		restTemplate.delete("http://doctor-service/doctors/"+doctorId);
		return restTemplate.getForObject("http://doctor-service/doctors/"+doctorId, Doctor.class);
	}
	public Doctor deleteDoctorFallBack(Exception e) {
//		return new Doctor("no id","no name","no department",0,"no gender",0,"no contact","no address");
		return new Doctor();
	}
}
