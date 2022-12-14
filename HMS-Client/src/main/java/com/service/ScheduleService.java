package com.service;

import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bean.Doctor;
import com.bean.Schedule;
import com.bean.ScheduleList;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class ScheduleService {
	@Autowired
	private RestTemplate restTemplate;
	
	@CircuitBreaker(name = "schedule",fallbackMethod ="showAllScheduleFallBack" )
	public ScheduleList showAllSchedule() {
		return restTemplate.getForObject("http://schedule-service/schedules", ScheduleList.class);
	}
	public ScheduleList showAllScheduleFallBack(Exception e) {
		List<Schedule> scheduleList = new ArrayList<Schedule>();
		scheduleList.add(new Schedule(0,"no id","no name","no day",null,null));
		return new ScheduleList(scheduleList);
	}
	
	
	@CircuitBreaker(name = "schedule",fallbackMethod ="showScheduleByDoctorIdFallBack" )
	public Schedule showScheduleByDoctorId(String doctorId) {
		return restTemplate.getForObject("http://schedule-service/schedules/"+doctorId, Schedule.class);
	}
	public Schedule showScheduleByDoctorIdFallBack(Exception e) {
		return new Schedule(0,"no id","no name","no day",null,null);
	}

	
	@CircuitBreaker(name = "schedule",fallbackMethod ="showAvailableDoctorScheduleFallBack" )
	public ScheduleList showAvailableDoctorSchedule(Date date) {
		Format f = new SimpleDateFormat("EEEE");  
		String day = f.format(date);
		return restTemplate.getForObject("http://schedule-service/schedules/availableDoctors/"+day, ScheduleList.class);
	}
	public ScheduleList showAvailableDoctorScheduleFallBack(Exception e) {
		List<Schedule> scheduleList = new ArrayList<Schedule>();
		scheduleList.add(new Schedule(0,"no id","no name","no day",null,null));
		return new ScheduleList(scheduleList);
	}
	
	
	@CircuitBreaker(name = "schedule",fallbackMethod ="deleteScheduleFallBack" )
	public Schedule deleteSchedule(String doctorId) {
		restTemplate.delete("http://schedule-service/schedules/"+doctorId);
		return restTemplate.getForObject("http://schedule-service/schedules/"+doctorId, Schedule.class);
	}
	public Schedule deleteDoctorFallBack(Exception e) {
		return new Schedule(0,"no id","no name","no day",null,null);
	}
}
