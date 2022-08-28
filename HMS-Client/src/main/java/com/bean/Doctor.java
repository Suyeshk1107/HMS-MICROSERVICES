package com.bean;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
	private String doctorId;
	private String doctorName;
	private String doctorDepartment;
	private int doctorExperience;
	private String doctorGender;
	private int doctorAge;
	private String doctorContact;
	private String doctorAddress;
	public Doctor(String doctorName, String doctorDepartment, int doctorExperience, String doctorGender, int doctorAge,
			String doctorContact, String doctorAddress) {
		super();
		this.doctorName = doctorName;
		this.doctorDepartment = doctorDepartment;
		this.doctorExperience = doctorExperience;
		this.doctorGender = doctorGender;
		this.doctorAge = doctorAge;
		this.doctorContact = doctorContact;
		this.doctorAddress = doctorAddress;
	}
}
