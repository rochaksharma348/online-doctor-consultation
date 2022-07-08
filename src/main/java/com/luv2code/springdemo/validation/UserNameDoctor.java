package com.luv2code.springdemo.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = UserNameDoctorConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserNameDoctor {
	public String[] values() default {""};
	
	public String message() default "User name already taken";
	
	//adding groups
	
	public Class<?>[] groups() default {};
	
	//adding payload
	public Class<? extends Payload>[] payload() default {};

}
