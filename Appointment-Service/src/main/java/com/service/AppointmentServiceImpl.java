package com.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Appointment;
import com.bean.ProcedureAppointment;
import com.persistence.AppointmentDao;
import com.persistence.ProcedureAppointmentDao;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	
	@Autowired
	private AppointmentDao appointmentDao;
	@Autowired
	private ProcedureAppointmentDao procedureAppointmentDao;

	@Override
	public List<Appointment> getAllAppointments(){
		return appointmentDao.findAll();
	}
	@Override
	public List<Appointment> getAllAppointmentsByPatientId(String pId){
		return appointmentDao.getAllAppointmentsByPatientId(pId);
	}
	
	@Override
	public List<Appointment> getAllAppointmentsByDoctorId(String dId){
		return appointmentDao.getAllAppointmentsByDoctorId(dId);
	}
	
	@Override
	public Appointment getAppointmentById(int id) {
		return appointmentDao.getReferenceById(id);
	}

	@Override
	public Appointment addAppointment(String patientId, String doctorId, Date date) {
		int rows = appointmentDao.callProcedure(patientId, doctorId);
		if (rows<1) {
			return null;
		}
		ProcedureAppointment pA = procedureAppointmentDao.findTopByOrderByProcedureIdDesc();
		if(pA == null) {
			return null;
		}
		
		
		Time slot = pA.getStartSlot();
		LocalTime endSlot = LocalTime.parse(pA.getEndSlot().toString());
		
		List<Appointment> lastAppointments = appointmentDao.findAppointmentByDoctorIdAndDate(doctorId, date);
		Appointment lastAppointment;
		if(lastAppointments.isEmpty()) {
			lastAppointment = null;
			
		}else {
		lastAppointment = lastAppointments.get(lastAppointments.size()-1);
		}
		LocalTime newSlot;
		if(lastAppointment!=null) {
			Time lastSlot = lastAppointment.getSlot();
			newSlot = LocalTime.parse(lastSlot.toString());
			newSlot = newSlot.plusMinutes(20);
		}else {
			newSlot = LocalTime.parse(slot.toString());
		}
		
		
		

		int a = newSlot.compareTo(endSlot);
		
		Appointment appointment = new Appointment();
		
		if(a != 0) {
			appointment.setDate(date);
			appointment.setDepartment(pA.getDept());
			appointment.setDoctorId(doctorId);
			appointment.setPatientId(patientId);
			appointment.setDoctorName(pA.getDName());
			appointment.setPatientName(pA.getPName());
			appointment.setSlot(Time.valueOf(newSlot));
			
			appointmentDao.save(appointment);
			
			return appointmentDao.findById(appointmentDao.findTopByOrderByAppointmentIdDesc().getAppointmentId()).get();
			
		}	
		return appointment;
	}

	@Override
	public Appointment deleteAppointment(int appointmentId) {

		Optional<Appointment> appointmentOptional = appointmentDao.findById(appointmentId);
		
		if(appointmentOptional.isPresent()) {
			appointmentDao.deleteById(appointmentId);
			return appointmentOptional.get();
		}
		
		return new Appointment();
	}

	@Override
	public Appointment modifyAppointment(int appointmentId, Date date) {
		Optional<Appointment> appointmentOptional = appointmentDao.findById(appointmentId);
		
		if(appointmentOptional.isPresent()) {
			
			int rows = appointmentDao.updateAppointmentByIdAndDate(appointmentId,date);
			
			if(rows>0) {
				return appointmentDao.getReferenceById(appointmentId);
			}
		}
		
		return new Appointment();
	}

}