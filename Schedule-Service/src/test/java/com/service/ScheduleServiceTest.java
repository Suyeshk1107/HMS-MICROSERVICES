package com.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.schedule.bean.Schedule;
import com.schedule.persistence.ScheduleDao;
import com.schedule.service.ScheduleServiceImpl;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceTest {
	
	@InjectMocks
	@Autowired
	private ScheduleServiceImpl scheduleService;
	
	@Mock
	private ScheduleDao scheduleDao;

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
	void testGetAllSchedule() {
//		fail("Not yet implemented");
		List<Schedule> schedules = new ArrayList<>();
		schedules.add(new Schedule(1	,"D1000"	,"Dr Pullen"	,"Monday"	,Time.valueOf("10:00:00")	,Time.valueOf("12:00:00")));
		schedules.add(new Schedule(2	,"D1001"	,"Dr Qullen"	,"Tuesday"	,Time.valueOf("11:00:00")	,Time.valueOf("13:00:00")));
		Mockito.when(scheduleDao.findAll()).thenReturn(schedules);
		assertIterableEquals(schedules, scheduleService.getAllSchedule());
	}
	@Test
	void testGetAllScheduleNegative() {
//		fail("Not yet implemented");
		List<Schedule> schedules = new ArrayList<>();
		Mockito.when(scheduleDao.findAll()).thenReturn(schedules);
		assertIterableEquals(schedules, scheduleService.getAllSchedule());
	}

	@Test
	void testGetScheduleByIdDoctorId() {
//		fail("Not yet implemented");
		Schedule schedule = new Schedule(1	,"D1000"	,"Dr Pullen"	,"Monday"	,Time.valueOf("10:00:00")	,Time.valueOf("12:00:00"));
		Mockito.when(scheduleDao.findByDoctorId("D1000")).thenReturn(schedule);
		
		assertEquals(schedule, scheduleService.getScheduleByIdDoctorId("D1000"));
	}
	@Test
	void testGetScheduleByIdDoctorIdNegative() {
//		fail("Not yet implemented");
		Schedule schedule = new Schedule(1	,"D1000"	,"Dr Pullen"	,"Monday"	,Time.valueOf("10:00:00")	,Time.valueOf("12:00:00"));
		Mockito.when(scheduleDao.findByDoctorId("D10000")).thenReturn(null);
		
		assertEquals(null, scheduleService.getScheduleByIdDoctorId("D10000"));
	}

	@Test
	void testDeleteScheduleByDoctorId() {
//		fail("Not yet implemented");
		Schedule schedule = new Schedule(1	,"D1000"	,"Dr Pullen"	,"Monday"	,Time.valueOf("10:00:00")	,Time.valueOf("12:00:00"));
		Mockito.when(scheduleDao.findByDoctorId("D1000")).thenReturn(schedule);
		assertEquals(schedule, scheduleService.deleteScheduleByDoctorId("D1000"));
	}

	@Test
	void testGetAllAvailableDoctorsSchedule() {
//		fail("Not yet implemented");
		List<Schedule> schedules = new ArrayList<>();
		schedules.add(new Schedule(1	,"D1000"	,"Dr Pullen"	,"Monday"	,Time.valueOf("10:00:00")	,Time.valueOf("12:00:00")));
		schedules.add(new Schedule(2	,"D1001"	,"Dr Qullen"	,"Monday"	,Time.valueOf("11:00:00")	,Time.valueOf("13:00:00")));
		Mockito.when(scheduleDao.getAvailableDoctors("Monday")).thenReturn(schedules);
		assertIterableEquals(schedules, scheduleService.getAllAvailableDoctorsSchedule("Monday"));
	}
	@Test
	void testGetAllAvailableDoctorsScheduleNegative() {
//		fail("Not yet implemented");
		List<Schedule> schedules = new ArrayList<>();
		Mockito.when(scheduleDao.getAvailableDoctors("Monday")).thenReturn(schedules);
		assertIterableEquals(schedules, scheduleService.getAllAvailableDoctorsSchedule("Monday"));
	}

}
