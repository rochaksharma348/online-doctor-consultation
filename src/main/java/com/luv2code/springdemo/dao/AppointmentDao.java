package com.luv2code.springdemo.dao;

import java.util.List;

import com.luv2code.springdemo.entity.Appointment;

public interface AppointmentDao {
	
	public Appointment getAppointment(int id);

	public void deleteById(int appointmentId);

	public List<Appointment> getAppointments();

}
