package com.luv2code.springdemo.entity;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.luv2code.springdemo.utils.DateUtils;
import com.luv2code.springdemo.validation.UserNameDoctor;

@Entity
@Table(name = "doctor")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotNull(message = "is required")
	@Size(min = 1, max = 100, message = "is required")
	@Column(name = "first_name")
	private String firstName;
	
	@NotNull(message = "is required")
	@Size(min = 1, max = 100, message = "is required")
	@Column(name = "last_name")
	private String lastName;
	
	@NotNull(message = "is required")
	@UserNameDoctor(message = "username already taken")
	@Size(min = 1, max = 100, message = "is required")
	@Column(name = "username")
	private String username;
	
	@NotNull(message = "is required")
	@Size(min = 1, max = 100, message = "is required")
	@Email(message = "please enter a valid email")
	@Column(name = "email")
	private String email;
	
	@NotNull(message = "is required")
	@Size(min = 1, max = 100, message = "is required")
	@Column(name = "password")
	private String password;
	
	@NotNull(message = "is required")
	@Size(min = 1, max = 100, message = "is required")
	@Column(name = "gender")
	private String gender;
	
	@NotNull(message = "is required")
	@Size(min = 1, max = 100, message = "is required")
	@Column(name = "location")
	private String location;
	
	@NotNull(message = "is required")
	@Size(min = 1, max = 100, message = "is required")
	@Pattern(regexp = "^[0-9]{10}" , message = "must be a 10 digit number")
	@Column(name = "phone_no")
	private String phoneNo;
	
	@NotNull(message = "is required")
	@Size(min = 1, max = 100, message = "is required")
	@Column(name = "speciality")
	private String speciality;
	
	@NotNull(message = "is required and should be in correct format")
	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;
	
	@NotNull(message = "is required")
	@Size(min = 1, max = 100, message = "is required")
	@Column(name = "address")
	private String address;
	
	
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
	private List<DoctorSchedule> doctorSchedules;
	
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
	private List<Appointment> appointments;
	
	public Doctor() {
		
	}
	
	public Doctor(String firstName, String lastName, String username, String email, String password, String gender,
			String location, String phoneNo, String speciality, LocalDate dateOfBirth) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.location = location;
		this.phoneNo = phoneNo;
		this.speciality = speciality;
		this.dateOfBirth = dateOfBirth;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	
	public String getDateOfBirthString() {
		return DateUtils.formatDate(dateOfBirth);
	}
	
	public void setDateOfBirthString(String dateOfBirth) throws ParseException {
		this.dateOfBirth = DateUtils.parseDate(dateOfBirth);
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<DoctorSchedule> getDoctorSchedules() {
		return doctorSchedules;
	}

	public void setDoctorSchedules(List<DoctorSchedule> doctorSchedules) {
		this.doctorSchedules = doctorSchedules;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public void addDoctorSchedule(DoctorSchedule schedule) {
		if (doctorSchedules == null) {
			doctorSchedules = new ArrayList<>();
		}
		doctorSchedules.add(schedule);
	}
	
	public void addAppointment(Appointment appointment) {
		if (appointments == null) {
			appointments = new ArrayList<Appointment>();
		}
		appointments.add(appointment);
	}
	
}
