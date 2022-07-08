package com.luv2code.springdemo.dao;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.credentials.Credentials;
import com.luv2code.springdemo.entity.Appointment;
import com.luv2code.springdemo.entity.Doctor;
import com.luv2code.springdemo.entity.Patient;
import com.luv2code.springdemo.utils.SortUtils;

@Repository
public class PatientDAOImp implements PatientDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Patient> getPatients(int sortType) {
		Session session = sessionFactory.getCurrentSession();
		
		String sortFieldString = null;
		
		switch (sortType) {
		case SortUtils.FIRST_NAME:
			sortFieldString = "firstName";
			break;
			
		case SortUtils.LAST_NAME:
			sortFieldString = "lastName";
			break;
			
		case SortUtils.EMAIL:
			sortFieldString = "email";
			break;

		default:
			sortFieldString = "lastName";
			break;
		}
		
		String queryString = "from Patient order by " + sortFieldString;
		
		Query<Patient> query = session.createQuery(queryString, Patient.class);
		
		List<Patient> patients = query.getResultList();
		
		return patients;
	}

	@Override
	@Transactional
	public void savePatient(Patient patient) {
		Session session = sessionFactory.getCurrentSession();
		
		if (patient != null) {
			session.saveOrUpdate(patient);
		}
	}

	@Override
	@Transactional
	public boolean usernameAvailable(String username) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Query<Patient> query = session.createQuery("from Patient where username='" + username + "'", Patient.class);
		
			Patient patient = query.getSingleResult();
			return patient == null;
		} catch (NoResultException e) {
			return true;
		}
	}

	@Override
	@Transactional
	public Patient getPatient(int patientId) {
		Session session = sessionFactory.getCurrentSession();
		
		Patient patient = session.get(Patient.class, patientId);
		
		return patient;
	}

	@Override
	@Transactional
	public void deletePatient(int patientId) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Patient patient = session.get(Patient.class, patientId);
		
		if (patient != null) {
			session.delete(patient);
		}
	}

	@Override
	@Transactional
	public List<Patient> getSearchResults(String theSearchName) {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Patient> query = null;
		
		if (theSearchName != null && theSearchName.trim().length() > 0) {
			query = session.createQuery("from Patient where lower(firstName) like :theName or lower(lastName) like :theName or lower(location) like :theName", Patient.class);
			query.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
		} else {
			query = session.createQuery("from Patient", Patient.class);
		}
		
		List<Patient> patients = query.getResultList();
		
		return patients;
	}

	@Override
	@Transactional
	public Patient getPatient(Credentials patientCredentials) {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Patient> query = session.createQuery("from Patient where username=:theUsername and password=:thePassword", Patient.class);
		
		query.setParameter("theUsername", patientCredentials.getUsername());
		query.setParameter("thePassword", patientCredentials.getPassword());
		
		List<Patient> patients = query.getResultList();
		
		if (patients  == null || patients .isEmpty()) {
			return null;
		}
		
		return patients.get(0);
	}
	
	@Override
	@Transactional
	public void saveAppointment(Appointment appointment) {
		
		Session session = sessionFactory.getCurrentSession();
		
		if (appointment != null) {
			
			Doctor doctor = appointment.getDoctor();
			Patient patient = appointment.getPatient();
			
			if(doctor.getAppointments() == null) {
				doctor.setAppointments(new ArrayList<Appointment>());
				doctor.addAppointment(appointment);
			}
			
			if(patient.getAppointments() == null) {
				patient.setAppointments(new ArrayList<Appointment>());
				patient.addAppointment(appointment);
			}
				
			session.saveOrUpdate(appointment);			
		}
	}

	@Override
	@Transactional
	public List<Appointment> getAppointments(int patientId) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Appointment> query = session.createQuery("from Appointment where patient_id=:thePatientId", Appointment.class);
		
		query.setParameter("thePatientId", patientId);
		
		List<Appointment> appointments = query.getResultList();
		
		return appointments;
	}

}
