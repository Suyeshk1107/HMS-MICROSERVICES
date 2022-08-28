package com.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient{
	private String patientId;
	private String patientName;
	private String patientGender;
	private int patientAge;
	private String patientContact;
	private String patientAddress;
	private String patientSymptoms;
	public Patient(String patientName, String patientGender, int patientAge, String patientContact,
			String patientAddress, String patientSymptoms) {
		super();
		this.patientName = patientName;
		this.patientGender = patientGender;
		this.patientAge = patientAge;
		this.patientContact = patientContact;
		this.patientAddress = patientAddress;
		this.patientSymptoms = patientSymptoms;
	}
	
	

	

}
