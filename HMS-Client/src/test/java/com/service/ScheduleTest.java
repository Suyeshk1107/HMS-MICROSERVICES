package com.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

//import org.springframework.web.client.RestTemplate;
import com.bean.Schedule;
import com.bean.ScheduleList;


@ExtendWith(MockitoExtension.class)
class ScheduleTest {

	@Autowired
	@InjectMocks
	private ScheduleService scheduleService;
	
	@Mock
	private RestTemplate restTemplate;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testShowAllSchedule() {
		
		List<Schedule> schedules = new ArrayList<>();
		schedules.add(new Schedule(1	,"D1000"	,"Dr Pullen"	,"Monday"	,Time.valueOf("10:00:00")	,Time.valueOf("12:00:00")));
		schedules.add(new Schedule(2	,"D1001"	,"Dr Qullen"	,"Tuesday"	,Time.valueOf("11:00:00")	,Time.valueOf("13:00:00")));
		ScheduleList sList = new ScheduleList(schedules);
		Mockito.when(restTemplate.getForObject("http://schedule-service/schedules", ScheduleList.class)).thenReturn(sList);
		assertEquals(sList,scheduleService.showAllSchedule());
	}
	@Test
	void testShowAllScheduleNegative() {
		
		List<Schedule> schedules = new ArrayList<>();
		ScheduleList sList = new ScheduleList(schedules);
		Mockito.when(restTemplate.getForObject("http://schedule-service/schedules", ScheduleList.class)).thenReturn(sList);
		assertEquals(sList,scheduleService.showAllSchedule());
	}

	@Test
	void testShowScheduleByDoctorId() {
		Schedule schedule = new Schedule(1	,"D1000"	,"Dr Pullen"	,"Monday"	,Time.valueOf("10:00:00")	,Time.valueOf("12:00:00"));
		Mockito.when(restTemplate.getForObject("http://schedule-service/schedules/"+"D1000", Schedule.class)).thenReturn(schedule);
		assertEquals(schedule,scheduleService.showScheduleByDoctorId("D1000"));
	}
	@Test
	void testShowScheduleByDoctorIdNegative() {
		Schedule schedule = new Schedule();
		Mockito.when(restTemplate.getForObject("http://schedule-service/schedules/"+"D10000", Schedule.class)).thenReturn(schedule);
		assertEquals(schedule,scheduleService.showScheduleByDoctorId("D10000"));
	}

	@Test
	void testShowAvailableDoctorSchedule() {
		List<Schedule> s = new ArrayList<>();
		Schedule schedule = new Schedule(1	,"D1000"	,"Dr Pullen"	,"Monday"	,Time.valueOf("10:00:00")	,Time.valueOf("12:00:00"));
		s.add(schedule);
		ScheduleList schedules = new ScheduleList(s);
		Mockito.when(restTemplate.getForObject("http://schedule-service/schedules/availableDoctors/"+"Monday", ScheduleList.class)).thenReturn(schedules);
		assertEquals(schedules, scheduleService.showAvailableDoctorSchedule(Date.valueOf("2022-09-12")));
	}
	@Test
	void testShowAvailableDoctorScheduleNegative() {
		List<Schedule> s = new ArrayList<>();
		Schedule schedule = new Schedule();
		s.add(schedule);
		ScheduleList schedules = new ScheduleList(s);
		Mockito.when(restTemplate.getForObject("http://schedule-service/schedules/availableDoctors/"+"Monday", ScheduleList.class)).thenReturn(schedules);
		assertEquals(schedules, scheduleService.showAvailableDoctorSchedule(Date.valueOf("2022-09-12")));
	}

	@Test
	void testDeleteSchedule() {
		Schedule schedule = new Schedule(1	,"D1000"	,"Dr Pullen"	,"Monday"	,Time.valueOf("10:00:00")	,Time.valueOf("12:00:00"));
		restTemplate.delete("http://schedule-service/schedules/"+"D1000");
		Mockito.when(restTemplate.getForObject("http://schedule-service/schedules/"+"D1000", Schedule.class)).thenReturn(schedule);
		assertEquals(schedule, scheduleService.deleteSchedule("D1000"));
	}
	@Test
	void testDeleteScheduleNegative() {
//		fail("Not yet implemented");
		Schedule schedule = new Schedule();
		restTemplate.delete("http://schedule-service/schedules/"+"D1000");
		Mockito.when(restTemplate.getForObject("http://schedule-service/schedules/"+"D1000", Schedule.class)).thenReturn(schedule);
		assertEquals(schedule, scheduleService.deleteSchedule("D1000"));
	}

}
