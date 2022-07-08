package com.luv2code.springdemo.dao;

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
import com.luv2code.springdemo.utils.SortUtils;

@Repository
public class DoctorDAOImp implements DoctorDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Doctor> getDoctors(int sortType) {
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
		
		String queryString = "from Doctor order by " + sortFieldString;
		
		Query<Doctor> query = session.createQuery(queryString, Doctor.class);
		
		List<Doctor> doctors = query.getResultList();
		
		return doctors;
	}

	@Override
	@Transactional
	public void saveDoctor(Doctor doctor) {
		Session session = sessionFactory.getCurrentSession();
		
		if (doctor != null) {
			session.saveOrUpdate(doctor);
		}
	}

	@Override
	@Transactional
	public boolean usernameAvailable(String username){
		Session session = sessionFactory.getCurrentSession();
		try {
			Query<Doctor> query = session.createQuery("from Doctor where username='" + username + "'", Doctor.class);
		
			Doctor doctor = query.getSingleResult();
			return doctor == null;
		} catch (NoResultException e) {
			return true;
		}
	}

	@Override
	@Transactional
	public Doctor getDoctor(int doctorId) {
		Session session = sessionFactory.getCurrentSession();
		
		Doctor doctor = session.get(Doctor.class, doctorId);
		
		return doctor;
	}

	@Override
	@Transactional
	public void deleteDoctor(int doctorId) {
		Session session = sessionFactory.getCurrentSession();
		
		Doctor doctor = session.get(Doctor.class, doctorId);
		
		if (doctor != null) {
			session.delete(doctor);
		}
	}

	@Override
	@Transactional
	public List<Doctor> getSearchResults(String theSearchName) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Doctor> query = null;
		
		if (theSearchName != null && theSearchName.trim().length() > 0) {
			query = session.createQuery("from Doctor where lower(firstName) like :theName or lower(lastName) like :theName or lower(location) like :theName", Doctor.class);
			query.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
		} else {
			query = session.createQuery("from Doctor", Doctor.class);
		}
		
		List<Doctor> doctors = query.getResultList();
		
		return doctors;
	}

	@Override
	@Transactional
	public Doctor getDoctor(Credentials doctorCredentials) {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Doctor> query = session.createQuery("from Doctor where username=:theUsername and password=:thePassword", Doctor.class);
		
		query.setParameter("theUsername", doctorCredentials.getUsername());
		query.setParameter("thePassword", doctorCredentials.getPassword());
		
		List<Doctor> doctors = query.getResultList();
		
		if (doctors  == null || doctors .isEmpty()) {
			return null;
		}
		
		return doctors.get(0);
	}

	@Override
	@Transactional
	public List<Appointment> getAppointments(int doctorId) {

		Session session = sessionFactory.getCurrentSession();
		
		Query<Appointment> query = session.createQuery("from Appointment where doctor_id=:theDoctorId", Appointment.class);
		
		query.setParameter("theDoctorId", doctorId);
		
		List<Appointment> appointments = query.getResultList();
		
		return appointments;
		
	}

}
