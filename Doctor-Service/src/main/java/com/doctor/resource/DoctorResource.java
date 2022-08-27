package com.doctor.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.bean.Doctor;
import com.doctor.bean.DoctorList;
import com.doctor.service.DoctorService;

@RestController
public class DoctorResource {
	
	@Autowired
	private DoctorService doctorService;
	
	@GetMapping(path = "/doctors", produces = MediaType.APPLICATION_JSON_VALUE)
	public DoctorList getAllDoctors() {
		return new DoctorList(doctorService.getAllDoctor());
	}
	
	@GetMapping(path = "/doctors/{dId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Doctor getDoctorById(@PathVariable("dId") String doctorId) {
		return doctorService.getDoctorById(doctorId);
	}
	
	@PostMapping(path = "/doctors", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean saveEmployeeResource(@RequestBody Doctor doctor) {
		return doctorService.addDoctor(doctor);
	}
	
	@DeleteMapping(path = "/doctors/{dId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String removeDoctorById(@PathVariable("dId") String doctorId) {
		
		if(doctorService.deleteDoctor(doctorId)) {
			return "Doctor Deleted Successfully !";
		}
		return "Unable to delete doctor ! Please try again later.";
	}
}
