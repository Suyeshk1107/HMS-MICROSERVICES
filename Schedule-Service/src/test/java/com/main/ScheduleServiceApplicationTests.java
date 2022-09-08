package com.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Time;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.schedule.bean.Schedule;
import com.schedule.persistence.ScheduleDao;




@SpringBootTest
class ScheduleServiceApplicationTests {

	@Autowired
	private ScheduleDao scheduleDao;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	
		
	@Test
	public void testFindByDoctorId() {

		Schedule schedule = new Schedule(1	,"D1000"	,"Dr Pullen"	,"Monday"	,Time.valueOf("10:00:00")	,Time.valueOf("12:00:00"));
		
		assertEquals(schedule, scheduleDao.findByDoctorId("D1000"));
	}


	@Test
	public void testFindByDoctorIdNegativeCase() {

		assertEquals(null, scheduleDao.findByDoctorId("D123"));
	}

	@Test
	public void testDeleteScheduleByDoctorId() {

		Schedule schedule = new Schedule(1	,"D1000"	,"Dr Pullen"	,"Monday"	,Time.valueOf("10:00:00")	,Time.valueOf("12:00:00"));
		
		assert(scheduleDao.deleteScheduleByDoctorId("D1000"));
	}
	
}
