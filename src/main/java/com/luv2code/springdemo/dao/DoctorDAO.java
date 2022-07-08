package com.luv2code.springdemo.dao;

import java.util.List;

import javax.validation.Valid;

import com.luv2code.springdemo.credentials.Credentials;
import com.luv2code.springdemo.entity.Appointment;
import com.luv2code.springdemo.entity.Doctor;

public interface DoctorDAO {
		
	public List<Doctor> getDoctors(int sortType);

	public void saveDoctor(Doctor doctor);
	
	public boolean usernameAvailable(String username);

	public Doctor getDoctor(int doctorId);

	public void deleteDoctor(int doctorId);

	public List<Doctor> getSearchResults(String theSearchName);

	public Doctor getDoctor(@Valid Credentials doctorCredentials);

	public List<Appointment> getAppointments(int doctorId);

}
