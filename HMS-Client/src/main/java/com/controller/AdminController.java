package com.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bean.Doctor;
import com.bean.DoctorList;
import com.bean.Login;
import com.bean.Patient;
import com.bean.PatientList;
import com.service.DoctorService;
import com.service.LoginService;
import com.service.PatientService;

@Controller
public class AdminController {
	
	
	
//  Admin Functionalities Start--------------------------------------------------------------------------------------------------------------------------
	
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private LoginService loginService;
	
//  Add Doctor
	@RequestMapping("/addDoctor")
	public ModelAndView addDoctorController() {
		return new ModelAndView("addDoctor");
	}
	@RequestMapping("/saveDoctor")
	public ModelAndView saveDoctorController(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		Doctor doctor = new Doctor();
	
		
		doctor.setDoctorName(request.getParameter("dName"));
		doctor.setDoctorAge(Integer.parseInt(request.getParameter("dAge")));
		doctor.setDoctorGender(request.getParameter("dGender"));
		doctor.setDoctorExperience(Integer.parseInt(request.getParameter("dExperience")));
		doctor.setDoctorContact(request.getParameter("dContact"));
		doctor.setDoctorAddress(request.getParameter("dAddress"));
		doctor.setDoctorDepartment(request.getParameter("dDepartment"));
		
		String message = null;
		Doctor doc = doctorService.addDoctor(doctor);
		if (doc.getDoctorContact().equals(request.getParameter("dContact"))) {
			message = "Doctor Added Successfully with user id : " + doc.getDoctorId() + " and Password : "+ doc.getDoctorId();
			loginService.saveLogin(doc.getDoctorId(), doc.getDoctorId());}
		else
			message = "couldn't reach the server, please try again after some time";

		modelAndView.addObject("message", message);
		modelAndView.setViewName("Output");
		return modelAndView;
	}
	
//  Remove Doctor
	
	@ModelAttribute("doctorIds")
	public List<String> getAllDoctorIds(){
		DoctorList doctorList = doctorService.showAllDoctor();
		
		return doctorList.getDoctorList().stream().
				map(Doctor::getDoctorId).
				collect(Collectors.toList());
	}

	@RequestMapping("/removeDoctorByID")
	public ModelAndView removeDoctorByIdController() {
		ModelAndView modelAndView = new ModelAndView();
		DoctorList doctorList = doctorService.showAllDoctor();
		String message = null;
		if (doctorList != null) {
			if(doctorList.getDoctorList().get(0).getDoctorId() == null) {
				message = "couldn't reach the server, please try again after some time";
				modelAndView.addObject("message", message);
				modelAndView.setViewName("Output");
				return modelAndView;
			}else {
			modelAndView.addObject("doctorList", doctorList.getDoctorList());
			modelAndView.addObject("command2",new Doctor());
			modelAndView.setViewName("ShowAllDoctorsToRemove");
			return modelAndView;
		}}
		else {
			message = "No Doctor to show !";

		modelAndView.addObject("message", message);
		modelAndView.setViewName("Output");
		return modelAndView;
		}
	}
	@RequestMapping("/removeDoctor")
	public ModelAndView removeDoctorController(@ModelAttribute("command2") Doctor doctor) {
		ModelAndView modelAndView = new ModelAndView();
		
		
		String message = null;
		Doctor doc = doctorService.deleteDoctor(doctor.getDoctorId());
		if (doc.getDoctorId() == null) {
			message = "Doctor Removed Successfully";
			loginService.deleteLogin(doctor.getDoctorId());
			modelAndView.addObject("message", message);
			modelAndView.setViewName("Output");
			return modelAndView;
		}
		else {
			if(doctorService.deleteDoctor(doctor.getDoctorId()).getDoctorId().equals("no id")) {
				message = "couldn't reach the server, please try again after some time";
				modelAndView.addObject("message", message);
				modelAndView.setViewName("Output");
				return modelAndView;
			}
			else {
				message = "Remove Failed";
				modelAndView.addObject("message", message);
				modelAndView.setViewName("Output");
				return modelAndView;
			}
		}
	}
//Show All Doctors	
	@RequestMapping("/showAllDoctors")
	public ModelAndView showAllDoctors() {
		ModelAndView modelAndView = new ModelAndView();
		
		DoctorList docList = doctorService.showAllDoctor();
		String message = null;
		if (docList.getDoctorList() != null) {
			if(docList.getDoctorList().get(0).getDoctorId()==null) {
				message = "couldn't reach the server, please try again after some time";
				modelAndView.addObject("message", message);
				modelAndView.setViewName("Output");
				return modelAndView;
			}else {
			List<Doctor> doctorList= docList.getDoctorList();
			modelAndView.addObject("doctorList", doctorList);
			modelAndView.setViewName("ShowAllDoctors");
			return modelAndView;
		}}
		else {
			message = "No Doctor to show !";

		modelAndView.addObject("message", message);
		modelAndView.setViewName("Output");
		return modelAndView;
		}
	}
	
	
//Remove Patient
	
	@ModelAttribute("patientIds")
	public List<String> getAllPatientIds(){
		PatientList patientList = patientService.showAllPatient();
		
		return patientList.getPatientList().stream().
				map(Patient::getPatientId).
				collect(Collectors.toList());
	}
	@RequestMapping("/removePatient")
	public ModelAndView removePatientController() {
		ModelAndView modelAndView = new ModelAndView();
		
		PatientList pList = patientService.showAllPatient();
		String message = null;
		if (!pList.getPatientList().isEmpty()) {
			if(pList.getPatientList().get(0).getPatientId().equals("no id")) {
				message = "couldn't reach the server, please try again after some time";
				modelAndView.addObject("message", message);
				modelAndView.setViewName("Output");
				return modelAndView;
			}else {
			List<Patient> patientList = pList.getPatientList();
			modelAndView.addObject("patientList", patientList);
			modelAndView.addObject("command",new Patient());
			modelAndView.setViewName("ShowAllPatientToRemove");
			return modelAndView;}
		}
		else {
			message = "No Patient to delete !";

		modelAndView.addObject("message", message);
		modelAndView.setViewName("Output");
		return modelAndView;
		}
	}
	
	@RequestMapping("/removePatientMessage")
	public ModelAndView removePatientMessage(@ModelAttribute("command") Patient patient) {
		ModelAndView modelAndView = new ModelAndView();
		String message = null;
		Patient pt = patientService.deletePatient(patient.getPatientId());
		if (pt.getPatientId()==null) {
			message = "Patient deleted Successfully";
			Login login = loginService.deleteLogin(patient.getPatientId());
			if(login.getId()!=null) {
				if(login.getId().equals("no User Id")) {
					message = "Failed to reach login service. please try again after some time";
				}else {
					message = "Failed to delete login credentials. Please contact admin for further process.";
				}
			}
			modelAndView.addObject("message", message);
			modelAndView.setViewName("Output");
			return modelAndView;
		}
		else {
			if(patientService.deletePatient(patient.getPatientId()).getPatientId().equals("no id")) {
				message = "Failed to reach the patient service, please try again after some time";
				modelAndView.addObject("message", message);
				modelAndView.setViewName("Output");
				return modelAndView;
			}else {
			message = "Patient deletion failed";
			modelAndView.addObject("message", message);
			modelAndView.setViewName("Output");
			return modelAndView;
		}}
		
	}
	
	
	
//Show All Patients	
	@RequestMapping("/showAllPatients")
	public ModelAndView showAllPatients() {
		ModelAndView modelAndView = new ModelAndView();
		
		PatientList pList = patientService.showAllPatient();
		String message = null;
		if (pList.getPatientList() != null) {
			if(pList.getPatientList().get(0).getPatientId().equals("no id")) {
				message = "couldn't reach the server, please try again after some time";
				modelAndView.addObject("message", message);
				modelAndView.setViewName("Output");
				return modelAndView;
			}else {
			List<Patient> patientList = pList.getPatientList();
			modelAndView.addObject("patientList", patientList);
			modelAndView.setViewName("ShowAllPatient");
			return modelAndView;
			}
		}
		else {
			
		message = "No Patient to show !";
		modelAndView.addObject("message", message);
		modelAndView.setViewName("Output");
		return modelAndView;
		}
	}
	
//  Admin Functionalities End ------------------------------------------------------------------------------------------------------------------------
	
	

}
