package com.luv2code.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.credentials.Credentials;
import com.luv2code.springdemo.entity.Admin;

@Repository
public class AdminDaoImpl implements AdminDao{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public Admin getAdmin(Credentials adminCredentials) {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Admin> query = session.createQuery("from Admin where username=:theUsername and password=:thePassword", Admin.class);
		
		query.setParameter("theUsername", adminCredentials.getUsername());
		query.setParameter("thePassword", adminCredentials.getPassword());
		
		List<Admin> admins = query.getResultList();
		
		if (admins == null || admins.isEmpty()) {
			return null;
		}
		
		return admins.get(0);
	}

}
