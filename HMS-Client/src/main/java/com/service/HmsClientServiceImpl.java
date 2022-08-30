package com.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Appointment;
import com.bean.AppointmentList;
import com.bean.Login;
import com.bean.Patient;
import com.bean.Schedule;
import com.bean.ScheduleList;


@Service
public class HmsClientServiceImpl implements HmsClientService {

//	@Autowired
//	private RestTemplate restTemplate;
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private ScheduleService scheduleService;

	//	public List<MovieDetails> getMovieDetails(String userId) {
//		List<MovieDetails> movieDetailsList=new ArrayList<MovieDetails>();
//		
////		RatingList ratingList=restTemplate.getForObject("http://rating-service/rating/"+userId, RatingList.class);
//		RatingList ratingList=ratingService.getRatingList(userId);
//		
//		for(Rating rating:ratingList.getRatings()) {
////			Movie movie=restTemplate.getForObject("http://movie-service/movies/"+rating.getMovieId(), Movie.class);
//			Movie movie=movieService.getMovie(rating.getMovieId());
//			movieDetailsList.add(new MovieDetails(userId, movie.getMovieName(), rating.getRating()));
//		}
//		
//		return movieDetailsList;
//	}
	
//	login services
	@Override
	public boolean isValid(String id, String password) {
		
		Login login= loginService.getLogin(id);
		if(password.equals(login.getPassword())) {
			return true;
		}
		
		return false;
		
	}
	
	@Override
	public boolean registerUser(String userId, String password) {
		Login login = loginService.saveLogin(userId, password);
		
		if(login.getId().equals(userId)) {
			return true;
		}
		return false;
	}
	
//	Appointment service
	@Override
	public List<Appointment> showAllAppointmentsByPatientId(String pId){
		AppointmentList appointmentList = appointmentService.showAllAppointmentsByPatientId(pId);
		String patientId = appointmentList.getAppointments().get(0).getPatientId();
		if(patientId.equals(patientId))
			return appointmentList.getAppointments();
		return new ArrayList<>();
	}
	@Override
	public List<Appointment> showAllAppointmentsByDoctorId(String dId){
		AppointmentList appointmentList = appointmentService.showAllAppointmentsByDoctorId(dId);
		String doctorId = appointmentList.getAppointments().get(0).getDoctorId();
		if(doctorId.equals(dId))
			return appointmentList.getAppointments();
		return new ArrayList<>();
	}
	@Override
	public List<Appointment> showAllAppointments(){
		AppointmentList appointmentList = appointmentService.showAllAppointments();
		int appointmentId = appointmentList.getAppointments().get(0).getAppointmentId();
		if(appointmentId != 0)
			return appointmentList.getAppointments();
		return new ArrayList<>();
	}
	
	@Override
	public boolean requestAppointment(Appointment appointment) {
		Appointment newAppointment = appointmentService.addAppointment(appointment);
		if(newAppointment.getAppointmentId() != 0)
			return true;
		return false;
	}
	
	@Override
	public boolean rescheduleAppointment(Appointment appointment) {
		Appointment newAppointment = appointmentService.modifyAppointment(appointment);
		if(newAppointment.getAppointmentId() == appointment.getAppointmentId()) {
			return true;
		}
		return false;
	}
	
//	patient-service
	@Override
	public Patient addPatientToDatabase(Patient patient) {
		Patient newPatient = patientService.addPatient(patient);
		if(newPatient.getPatientName().equals(patient.getPatientId())) {
			return newPatient;
		}
		return null;
	}
	
	@Override
	public Patient getPatientById(String pId) {
		Patient patient = patientService.showPatientById(pId);
		if(patient.getPatientId().equals(patient.getPatientId())) {
			return patient;
		}
		return null;
	}
	
	
//	schedule-service
//	check service layer
	@Override
	public List<Schedule> getAvailableDoctorSchedule(Date date){
		
		ScheduleList scheduleList = scheduleService.showAvailableDoctorSchedule(date);
		if(scheduleList.getScheduleList().get(0).getScheduleId() != 0) {
			return scheduleList.getScheduleList();
		}
		
		return new ArrayList<Schedule>();
	}
}