package com.doctor.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.bean.Doctor;
import com.doctor.bean.DoctorList;
import com.doctor.service.DoctorService;

@RestController
public class DoctorResource {
	
	@Autowired
	private DoctorService doctorService;

	
//	@GetMapping(path = "/shares/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
//	public Share getShareResource(@PathVariable("id") int shareId) {
//		return shareService.getShareById(shareId);
//	}
	
	@GetMapping(path = "/doctors", produces = MediaType.APPLICATION_JSON_VALUE)
	public DoctorList getAllDoctors() {
		return new DoctorList(doctorService.getAllDoctor());
	}
	
	@GetMapping(path = "/doctors/{dId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Doctor getDoctorById(@PathVariable("dId") String doctorId) {
		return doctorService.getDoctorById(doctorId);
	}
	
	@GetMapping(path = "/doctors/remove/{dId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String removeDoctorById(@PathVariable("dId") String doctorId) {
		if(doctorService.deleteDoctor(doctorId)) {
			return "Doctor Deleted Successfully !";
		}
		return "Unable to delete doctor ! Please try again later.";
	}
}
