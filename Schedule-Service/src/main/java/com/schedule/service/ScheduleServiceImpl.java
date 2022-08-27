package com.schedule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schedule.bean.Schedule;
import com.schedule.persistence.ScheduleDao;
@Service
public class ScheduleServiceImpl implements ScheduleService{

	@Autowired
	private ScheduleDao scheduleDao;
	
	@Override
	public List<Schedule> getAllSchedule() {
		// TODO Auto-generated method stub
		return scheduleDao.findAll();
	}

	@Override
	public Schedule getScheduleByIdDoctorId(String doctorId) {
		// TODO Auto-generated method stub
		return scheduleDao.findByDoctorId(doctorId);
	}

	@Override
	public Boolean deleteScheduleByDoctorId(String doctorId) {
		// TODO Auto-generated method stub
		scheduleDao.deleteScheduleByDoctorId(doctorId);
		if(getScheduleByIdDoctorId(doctorId) != null)
			return false;
		return true;
	}

	@Override
	public List<Schedule> getAllAvailableDoctorsSchedule(String day) {
		// TODO Auto-generated method stub
		return scheduleDao.getAvailableDoctors(day);
	}

}
