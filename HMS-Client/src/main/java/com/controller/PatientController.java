package com.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bean.Appointment;
import com.bean.AppointmentList;
import com.bean.Patient;
import com.bean.Schedule;
import com.bean.ScheduleList;
import com.service.AppointmentService;
import com.service.DoctorService;
import com.service.HmsClientService;
import com.service.PatientService;
import com.service.ScheduleService;

@Controller
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private ScheduleService scheduleService;
	@Autowired
	private HmsClientService hmsClientService;
	
//	 Patient Functionalities --------------------------------------------------------------------------------------------------------------------
	

//	1. view patient profile
	@RequestMapping("/showPatient")
	public ModelAndView showPatientController(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		
//		editing for hms
		String userId = (String)session.getAttribute("userName");
		Patient patient = patientService.showPatientById(userId);
		
		
		if (patient != null) {
			if(patient.getPatientId().equals(userId)) {
				
				modelAndView.addObject("patient", patient);
				modelAndView.setViewName("ShowPatient");
			}else {
				String message="Failed to reach patient service. Please try again after some time.";
				modelAndView.addObject("message", message);
				modelAndView.setViewName("Output");
				
			}
		}
		else {
			String message="Patient with ID "+(String)session.getAttribute("userName")+" does not exist!";
			modelAndView.addObject("message", message);
			modelAndView.setViewName("Output");
		}
		return modelAndView;
	}
	
//	2. request appointment
	@RequestMapping("/requestAppointment")
	public ModelAndView requestAppointmentController() {
			return new ModelAndView("requestAppointmentPage");
	}
	
//	2.1 generate appointment 
	
	
	@RequestMapping("/generateAppointment")
	public ModelAndView generateAppointmentController(HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		Date date = Date.valueOf(request.getParameter("appointmentDate"));
		session.setAttribute("date", date);
		List<Schedule> availableDoctorsSchedule = scheduleService.showAvailableDoctorSchedule(date).getScheduleList();
//		 availableDoctorsSchedule = hmsClientService.getAvailableDoctorSchedule(date);
		if(!availableDoctorsSchedule.isEmpty()) {
			if(availableDoctorsSchedule.get(0).getScheduleId() != 0) {
				
			
			modelAndView.addObject( "availableScheduleList", availableDoctorsSchedule);
			modelAndView.addObject("command3",new Schedule());
			modelAndView.setViewName("ShowAvailableDoctorsSchedulePage");
			}else {
				String message="Failed to reach Schedule service. Please try again after some time.";
				modelAndView.addObject("message", message);
				modelAndView.setViewName("Output");
			}
		}
		else {
			String message="No available Doctor schedules to display";
			modelAndView.addObject("message", message);
			modelAndView.setViewName("Output");
		}
		return modelAndView;
	}
	
//	@ModelAttribute("availableDoctorIds")
	public List<String> getAvailableDoctorIds(HttpSession session){
		ScheduleList availableDoctorsSchedule = scheduleService.showAvailableDoctorSchedule(Date.valueOf((String)(session.getAttribute("date"))));
		
		return availableDoctorsSchedule.getScheduleList().
				stream().
				map(Schedule::getDoctorId).
				collect(Collectors.toList());
	}
	
	@RequestMapping("/bookAppointment")
	public ModelAndView bookAppointmentController(HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		
//		String doctorId = request.getParameter("dId");
		Appointment appointment = new Appointment();
		appointment.setPatientId((String)session.getAttribute("userName"));
		appointment.setDoctorId(request.getParameter("dId"));
		appointment.setDate((Date)session.getAttribute("date"));
		
		Appointment latestAppointment = appointmentService.addAppointment(appointment);
		
		//changing code here
		if(latestAppointment!=null) {
			if(latestAppointment.getPatientId().equals((String)session.getAttribute("userName"))) {
			// Appointment bookedAppointment = appointmentService.showLatestAppointment();
				List<Appointment> lA = new ArrayList<>();
				lA.add(latestAppointment);
			modelAndView.addObject( "myAppointmentList",lA);
			modelAndView.setViewName("ShowMyAppointments");
			}else {
				String message="Something went wrong. Please try again after some time.";
				modelAndView.addObject("message", message);
				modelAndView.setViewName("Output");
			}
		}
		else {
			String message="No appointment to display";
			modelAndView.addObject("message", message);
			modelAndView.setViewName("Output");
		}
		
		return modelAndView;
	}
	
	
	
//	3. cancel appointment
	@RequestMapping("/cancelAppointment")
	public ModelAndView cancelAppointmentController(HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		
		String userId = (String)session.getAttribute("userName");
		List<Appointment> appointments = appointmentService.showAllAppointmentsByPatientId(userId).getAppointments(); 
//				hmsClientService.showAllAppointmentsByPatientId(userId);
		
		
		if(!appointments.isEmpty() ) {
			if(appointments.get(0).getPatientId().equals(userId)) {
				
			modelAndView.addObject("appointmentList", appointments);
			modelAndView.setViewName("cancelAppointment");
			}else {

				String message="Failed to reach appointment service. Please try again after some time.";
				modelAndView.addObject("message", message);
				modelAndView.setViewName("Output");
			}
		}
		else {
			String message="No appointments to delete";
			modelAndView.addObject("message", message);
			modelAndView.setViewName("Output");
		}
		
		return modelAndView;
	}
	
//	3.1
	@RequestMapping("/deleteAppointment")
	public ModelAndView deleteAppointmentController(HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		int id = Integer.parseInt(request.getParameter("appointmentId"));

		String message = "";
//		appointmentService.deleteAppointmentById(id);
		if(appointmentService.deleteAppointmentById(id).getAppointmentId() == 0) { 
			message="Appointment with ID "+id+" deleted successfully!";
			
		}else {
			message="Appointment with ID "+id+" does not exist!";
		}
		
		modelAndView.addObject("message", message);
		modelAndView.setViewName("Output");
		return modelAndView;
	}
	
//	4. view all appointments
	@RequestMapping("/viewAllAppointments")
	public ModelAndView viewAllAppointmentsController(HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		
		String pId = (String) session.getAttribute("userName");
		List<Appointment> appointments = appointmentService.showAllAppointmentsByPatientId(pId).getAppointments();
//				hmsClientService.showAllAppointmentsByPatientId(pId);
		
		
//		may fail due to circuit breaker -- won't fail now
		if(!appointments.isEmpty()) {
			if(appointments.get(0).getPatientId().equals(pId)) {
				
				modelAndView.addObject( "myAppointmentList", appointments);
				modelAndView.setViewName("ShowMyAppointments");
			}else {
				String message="Failed to reach appointment-service. Please try again after some time.";
				modelAndView.addObject("message", message);
				modelAndView.setViewName("Output");
			}
		}
		else {
			String message="No appointments to display";
			modelAndView.addObject("message", message);
			modelAndView.setViewName("Output");
		}
		
		return modelAndView;
		
	}
	
//	5. reschedule appointment
	@RequestMapping("/rescheduleAppointment")
	public ModelAndView rescheduleAppointmentController(HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		
		String pId = (String) session.getAttribute("userName");
		List<Appointment> appointments = appointmentService.showAllAppointmentsByPatientId(pId).getAppointments();
				hmsClientService.showAllAppointmentsByPatientId(pId);
		
		
		if(!appointments.isEmpty()) {
			if(appointments.get(0).getPatientId().equals(pId)) {
			modelAndView.addObject( "appointmentList", appointments);
			modelAndView.setViewName("rescheduleAppointment");
			}else {
				String message="Failed to reach appointment service. Please try again after some time.";
				modelAndView.addObject("message", message);
				modelAndView.setViewName("Output");
					
			}
		}
		else {
			String message="No appointments to display";
			modelAndView.addObject("message", message);
			modelAndView.setViewName("Output");
		}
		
		return modelAndView;
		
	}
	
	@RequestMapping("/rescheduleAppointmentTo")
	public ModelAndView rescheduleAppointmentToController(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		
		int aid = Integer.parseInt(request.getParameter("appointmentId"));
		Date appointmentDate =Date.valueOf(request.getParameter("appointmentDate"));
		
		Appointment appointment = appointmentService.showAppointmentsById(aid);
		appointment.setDate(appointmentDate);
		Appointment newAppointment = appointmentService.modifyAppointment(appointment);
		if(newAppointment !=null) {
			if(newAppointment.getAppointmentId() == aid) {
			String message="Appointment Rescheduled successfully.";
			modelAndView.addObject("message", message);
			modelAndView.setViewName("Output");
			}else {
				String message=" Failed to reach Appointment service. Please try again.";
				modelAndView.addObject("message", message);
				modelAndView.setViewName("Output");
					
			}
		}else{
			String message="Failed to reschedule Appointment. Please try again.";
			modelAndView.addObject("message", message);
			modelAndView.setViewName("Output");
		};
		
		return modelAndView;
	}
}
