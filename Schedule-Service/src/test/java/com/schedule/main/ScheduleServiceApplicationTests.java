package com.schedule.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Time;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.schedule.bean.Schedule;
import com.schedule.persistence.ScheduleDao;




@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ScheduleServiceApplicationTests {

	@Autowired
	private ScheduleDao scheduleDao;
	
	@Test
	void contextLoads() {
	}
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	
	@Order(1)	
	@Test
	public void testFindByDoctorId() {
		
		Schedule schedule = new Schedule(1	,"D1000"	,"Dr Pullen"	,"Monday"	,Time.valueOf("10:00:00")	,Time.valueOf("12:00:00"));
//		scheduleDao.save(schedule);
		assertEquals(schedule, scheduleDao.findByDoctorId("D1000"));
	}


	@Test
	public void testFindByDoctorIdNegativeCase() {

		assertEquals(null, scheduleDao.findByDoctorId("D123"));
	}
	
	@Order(2)	
	@Test
	public void testDeleteScheduleByDoctorId() {

		Schedule schedule = new Schedule(1	,"D1000"	,"Dr Pullen"	,"Monday"	,Time.valueOf("10:00:00")	,Time.valueOf("12:00:00"));
		scheduleDao.deleteScheduleByDoctorId("D1000");
		
		assertEquals(null,scheduleDao.findByDoctorId("D1000"));
	}
	
}
