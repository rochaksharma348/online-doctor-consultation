package com.luv2code.springdemo.dao;

import com.luv2code.springdemo.credentials.Credentials;
import com.luv2code.springdemo.entity.Admin;

public interface AdminDao {
	
	public Admin getAdmin(Credentials adminCredentials);

}
