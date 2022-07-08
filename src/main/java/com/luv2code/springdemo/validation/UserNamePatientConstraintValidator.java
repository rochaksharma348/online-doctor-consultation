package com.luv2code.springdemo.validation;

import javax.validation.ConstraintValidator;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luv2code.springdemo.dao.PatientDAO;

@Component
public class UserNamePatientConstraintValidator 
	implements ConstraintValidator<UserNamePatient, String> {
	
	@Autowired
	PatientDAO patientDAO;
	
	@Override
	public boolean isValid (String username, ConstraintValidatorContext arg1){
		if (username == null) {
			return true;
		}
		if (patientDAO == null) {
			return true;
		}
		return patientDAO.usernameAvailable(username);
	}

}
