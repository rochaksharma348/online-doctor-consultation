package com.luv2code.springdemo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luv2code.springdemo.dao.DoctorDAO;

@Component
public class UserNameDoctorConstraintValidator 
	implements ConstraintValidator<UserNameDoctor, String> {
	
	@Autowired
	DoctorDAO doctorDAO;
	
	@Override
	public boolean isValid (String username, ConstraintValidatorContext arg1) throws NullPointerException{
		if (username == null) {
			return true;
		}
		if (doctorDAO == null) {
			return true;
		}
		return doctorDAO.usernameAvailable(username);
	}

}
