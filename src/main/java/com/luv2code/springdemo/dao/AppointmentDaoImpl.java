package com.luv2code.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Appointment;

@Repository
public class AppointmentDaoImpl implements AppointmentDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	@Override
	@Transactional
	public Appointment getAppointment(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		Appointment appointment = session.get(Appointment.class, id);
		
		return appointment;
	}

	@Override
	@Transactional
	public void deleteById(int appointmentId) {

		Session session = sessionFactory.getCurrentSession();
		
		Appointment appointment = session.get(Appointment.class, appointmentId);
		
		if (appointment != null) {
			session.delete(appointment);
		}
				
	}

	@Override
	@Transactional
	public List<Appointment> getAppointments() {

		Session session = sessionFactory.getCurrentSession();
		
		Query<Appointment> query = session.createQuery("from Appointment", Appointment.class);
		
		List<Appointment> appointments = query.getResultList();
	
		return appointments;
	}

}
