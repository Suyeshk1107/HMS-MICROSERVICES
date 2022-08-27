package com.schedule.service;

import java.util.List;

import com.schedule.bean.Schedule;

public interface ScheduleService {
	
	List<Schedule> getAllSchedule();
	
	Schedule getScheduleByIdDoctorId(String doctorId);
	
	Boolean deleteScheduleByDoctorId(String doctorId);
	
	List<Schedule> getAllAvailableDoctorsSchedule(String day);
	
	

}
