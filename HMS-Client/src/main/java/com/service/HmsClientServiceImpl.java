package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Appointment;
import com.bean.AppointmentList;
import com.bean.Login;


@Service
public class HmsClientServiceImpl implements HmsClientService {

//	@Autowired
//	private RestTemplate restTemplate;
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private AppointmentService appointmentService;

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
	
}