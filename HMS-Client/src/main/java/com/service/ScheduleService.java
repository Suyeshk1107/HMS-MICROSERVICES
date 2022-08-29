package com.service;

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
	
	@CircuitBreaker(name = "allSchedule",fallbackMethod ="showAllScheduleFallBack" )
	public ScheduleList showAllSchedule() {
		return restTemplate.getForObject("http://schedule-service/schedules", ScheduleList.class);
	}
	public ScheduleList showAllScheduleFallBack(Exception e) {
		List<Schedule> scheduleList = new ArrayList<Schedule>();
		scheduleList.add(new Schedule(0,"no id","no name","no day",null,null));
		return new ScheduleList(scheduleList);
	}
	
	
	@CircuitBreaker(name = "Schedule",fallbackMethod ="showScheduleByDoctorIdFallBack" )
	public Schedule showScheduleByDoctorId(String doctorId) {
		return restTemplate.getForObject("http://schedule-service/schedules/"+doctorId, Schedule.class);
	}
	public Schedule showDoctorByIdFallBack(Exception e) {
		return new Schedule(0,"no id","no name","no day",null,null);
	}

	
	@CircuitBreaker(name = "availableDoctorSchedule",fallbackMethod ="showAvailableDoctorScheduleFallBack" )
	public ScheduleList showAvailableDoctorSchedule(String day) {
		return restTemplate.getForObject("http://schedule-service/schedules/availableDoctors/"+day, ScheduleList.class);
	}
	public ScheduleList showAvailableDoctorScheduleFallBack(Exception e) {
		List<Schedule> scheduleList = new ArrayList<Schedule>();
		scheduleList.add(new Schedule(0,"no id","no name","no day",null,null));
		return new ScheduleList(scheduleList);
	}
	
	
	@CircuitBreaker(name = "removeSchedule",fallbackMethod ="deleteScheduleFallBack" )
	public Schedule deleteSchedule(String doctorId) {
		restTemplate.delete("http://schedule-service/schedules/"+doctorId);
		return restTemplate.getForObject("http://schedule-service/schedules/"+doctorId, Schedule.class);
	}
	public Schedule deleteDoctorFallBack(Exception e) {
		return new Schedule(0,"no id","no name","no day",null,null);
	}
}
