package com.schedule.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.schedule.bean.Schedule;
import com.schedule.bean.ScheduleList;
import com.schedule.service.ScheduleService;
@RestController
public class ScheduleResource {

	@Autowired
	private ScheduleService scheduleService;
	
	@GetMapping(path = "/schedules", produces = MediaType.APPLICATION_JSON_VALUE)
	public ScheduleList getAllSchedules() {
		return new ScheduleList(scheduleService.getAllSchedule());
	}
	
	@GetMapping(path = "/schedules/{dId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Schedule getScheduleByDoctorId(@PathVariable("dId") String doctorId) {
		return scheduleService.getScheduleByIdDoctorId(doctorId);
	}
	
	@GetMapping(path = "/schedules/availableDoctors/{day}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ScheduleList getAllAvailableDoctorsSchedules(@PathVariable("day") String day) {
		return new ScheduleList(scheduleService.getAllAvailableDoctorsSchedule(day));
	}
	
	@DeleteMapping(path = "/schedules/{dId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Schedule removeScheduleByDoctorId(@PathVariable("dId") String doctorId) {
		return scheduleService.deleteScheduleByDoctorId(doctorId);
	}
	
	
}
