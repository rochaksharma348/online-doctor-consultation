package com.luv2code.springdemo.dao;

import java.util.List;

import javax.validation.Valid;

import com.luv2code.springdemo.credentials.Credentials;
import com.luv2code.springdemo.entity.Appointment;
import com.luv2code.springdemo.entity.Patient;

public interface PatientDAO {
	
	public List<Patient> getPatients(int sortType);

	public void savePatient(Patient patient);

	public boolean usernameAvailable(String username);

	public Patient getPatient(int patientId);

	public void deletePatient(int patientId);

	public List<Patient> getSearchResults(String theSearchName);

	public Patient getPatient(@Valid Credentials patientCredentials);

	void saveAppointment(Appointment appointment);

	public List<Appointment> getAppointments(int patientId);

}
