package com.bean;

import java.sql.Time;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
	
	private int scheduleId;
	private String doctorId;
	private String nameOfDoctor;
	private String availableDay;
	private Time slotStart;
	private Time slotEnd;

}
