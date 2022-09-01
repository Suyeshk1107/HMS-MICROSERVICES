package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bean.Login;
import com.bean.Patient;
import com.service.HmsClientService;
import com.service.LoginService;
import com.service.PatientService;

@Controller
public class ValidationController {
	
	@Autowired
	private HmsClientService hmsClientService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/validate")
	public ModelAndView validationController(HttpServletRequest request,HttpSession session) {
		ModelAndView modelAndView=new ModelAndView();
		
		String userName = request.getParameter("user");
		String password = request.getParameter("pass");

		session.setAttribute("userName", userName);
		
		Login login = loginService.getLogin(userName);
		if(login != null) {
			if(login.getId().equals(userName)) {
				if(password.equals(login.getPassword())){
					// validate
					if(userName.toUpperCase().charAt(0) == 'A' ) {
						 
						 modelAndView.setViewName("adminPostLogin");
					 }else if(userName.toUpperCase().charAt(0) == 'D' ) {
						 modelAndView.setViewName("doctorPostLogin");
					 }else  if(userName.toUpperCase().charAt(0) == 'P' ) {
						 modelAndView.setViewName("patientPostLogin");
					 }
				}else {
					// fallback condition
					modelAndView.addObject("message","Invalid Credentials" );
					modelAndView.setViewName("login");
					
				}
			}
			else {
				// fallback condition
				modelAndView.addObject("message","Failed to reach the server, please try again after some time.");
				modelAndView.setViewName("login");
				
			}
//"Failed to reach the server, please try again after some time."			
		}else {
			modelAndView.addObject("message", "Invalid Credentials");
			modelAndView.setViewName("login");
		}
		
		
	
		return modelAndView;
		
	}
	
	
//  Registering Patient--------------------------------------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/inputDetailsToRegisterPatient")
	public ModelAndView inputDetailsToRegisterPatientController(HttpServletRequest request,HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		
		Patient patient = new Patient();
		patient.setPatientName(request.getParameter("pName"));
		patient.setPatientAge(Integer.parseInt(request.getParameter("pAge")));
		patient.setPatientGender(request.getParameter("pGender"));
		patient.setPatientContact(request.getParameter("pContact"));
		patient.setPatientAddress(request.getParameter("pAddress"));
		patient.setPatientSymptoms(request.getParameter("pSymptom"));
		
		String message = null;
		Patient newPatient = patientService.addPatient(patient);

		if (newPatient != null) {
			
			if(newPatient.getPatientGender().equals(newPatient.getPatientGender())) {
				session.setAttribute("userName", newPatient.getPatientId());
				
				modelAndView.addObject("message", " Your Patient ID for login is :"+newPatient.getPatientId());
				modelAndView.setViewName("inputDetailsToRegisterPatient");
					
			}else {
				
				modelAndView.addObject("message", "Failed to reach login server. Please try again after some time.");
				modelAndView.setViewName("Login");
				
			}
			
			
		}
		else {
			modelAndView.addObject("message", "Failed to register Patient. Please try again.");
			modelAndView.setViewName("Login");

		}
		return modelAndView;
	}

	@RequestMapping("/registerPatient")
	public ModelAndView registerPatientController(HttpServletRequest request,HttpSession session) {
		
		String password = request.getParameter("password");
		String userName = (String) session.getAttribute("userName");
		
		String message;
		Login login = loginService.saveLogin(userName, password);
		
		if(login != null) {
			if(login.getId().equals(userName)) {
				
				message = "Patient Registered to Database Successfully";
			}else {
				
				message = "Something went wrong. Please try again after some time.";
			}
		}else {
			message = "Failed to register Patient. Please try again after some time.";
		}
		
		return new ModelAndView("Output", "message", message);
	}
}
