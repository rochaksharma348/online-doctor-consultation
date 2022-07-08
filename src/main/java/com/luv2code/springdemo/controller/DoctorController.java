package com.luv2code.springdemo.controller;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.credentials.Credentials;
import com.luv2code.springdemo.dao.AppointmentDao;
import com.luv2code.springdemo.dao.DoctorDAO;
import com.luv2code.springdemo.entity.Appointment;
import com.luv2code.springdemo.entity.Doctor;
import com.luv2code.springdemo.utils.SortUtils;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorDAO doctorDAO;
	
	@Autowired
	private AppointmentDao appointmentDao;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		StringTrimmerEditor editor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, editor);
	}
	
	
	@GetMapping("/panel")
	public String doctorsPanel(Model model){
		Credentials doctorCredentials = new Credentials();
		model.addAttribute("doctorCredentials", doctorCredentials);
		return "doctors-panel";
	}
	
	@GetMapping("/logout")
	public String doctorsPanelLogout(Model model){
		Credentials doctorCredentials = new Credentials();
		model.addAttribute("doctorCredentials", doctorCredentials);
		return "logout/doctors-panel";
	}
	
	@RequestMapping("/processLogin")
	public String loginSuccess(@Valid @ModelAttribute("doctorCredentials") 
		Credentials doctorCredentials, BindingResult bindingResult,
		Model model) {
		if (bindingResult.hasErrors()) {
			return "doctors-panel";
		}
		Doctor doctor = doctorDAO.getDoctor(doctorCredentials);
		if (doctor != null) {
			model.addAttribute("doctor", doctor);
			return "dashboard/doctor-dashboard";				
		} 
		return "invalidCredentials/doctors-panel";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(@RequestParam("doctorId") int id, Model model) {
		
		Doctor doctor = doctorDAO.getDoctor(id);
		
		model.addAttribute("doctor", doctor);
		
		return "dashboard/doctor-dashboard";
	}
	
	@RequestMapping("/list")
	public String doctorsList(@RequestParam(required = false) String sort, Model model) {
		
		List<Doctor> doctors = null;
		
		if (sort != null) {
			int sortType = Integer.parseInt(sort);
			
			doctors = doctorDAO.getDoctors(sortType);
			
		} else {
			doctors = doctorDAO.getDoctors(SortUtils.LAST_NAME);
		}
		
		model.addAttribute("doctors", doctors);
		return "list-doctors";
	}
	
	@RequestMapping("/register")
	public String registerDoctor(Model model) {
		Doctor doctor = new Doctor();
		
		model.addAttribute("doctor", doctor);
		
		return "doctor-register-form";
	}
	
	@PostMapping("/registerDoctor")
	public String registerPatient(@Valid @ModelAttribute("doctor") Doctor doctor,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "doctor-register-form";
		} else {
			doctorDAO.saveDoctor(doctor);
			return "doctor-registration-confirmation";
		}
	}
	
	@PostMapping("/saveDoctor")
	public String saveDoctor(@Valid @ModelAttribute("doctor") Doctor doctor,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "doctor-form";
		} 
		doctorDAO.saveDoctor(doctor);
		return "redirect:/doctor/list";
	}
	
	@RequestMapping("/showFormForAddDoctor")
	public String addDoctorForm(Model model) {
		Doctor doctor = new Doctor();
		
		model.addAttribute("doctor", doctor);
		
		return "doctor-form";
	}
	
	@GetMapping("/showUpdateForm")
	public String updateDoctorForm(@RequestParam("doctorId") int doctorId, Model model) {
		
		Doctor doctor = doctorDAO.getDoctor(doctorId);
		
		model.addAttribute("doctor", doctor);
		
		return "doctor-edit-form";
	}
	
	@PostMapping("/updateDoctor")
	public String updateDoctor(@Valid @ModelAttribute("doctor") Doctor doctor,
			BindingResult bindingResult) {
		System.out.println("|" + doctor.getUsername() + "|");
		
		System.out.println("Binding result: " + bindingResult);
		if (bindingResult.hasErrors()) {
			doctorDAO.saveDoctor(doctor);
			return "redirect:/doctor/list";
		} 
		doctorDAO.saveDoctor(doctor);
		return "redirect:/doctor/list";
	}
	
	@GetMapping("/showEditForm")
	public String editDoctorForm(@RequestParam("doctorId") int doctorId, Model model) {
		
		Doctor doctor = doctorDAO.getDoctor(doctorId);
		
		model.addAttribute("doctor", doctor);
		
		return "edit-profile/doctor-edit-form";
	}
	
	@PostMapping("/editDoctor")
	public String editDoctor(@Valid @ModelAttribute("doctor") Doctor doctor,
			BindingResult bindingResult) {
		System.out.println("|" + doctor.getUsername() + "|");
		
		System.out.println("Binding result: " + bindingResult);
		if (bindingResult.hasErrors()) {
			doctorDAO.saveDoctor(doctor);
			return "redirect:/doctor/dashboard?doctorId=" + doctor.getId();
		} 
		doctorDAO.saveDoctor(doctor);
		return "redirect:/doctor/dashboard?doctorId=" + doctor.getId();
	}
	
	@GetMapping("/delete")
	public String deleteDoctor(@RequestParam("doctorId") int doctorId, Model model) {
		doctorDAO.deleteDoctor(doctorId);
		
		return "redirect:/doctor/list";
	}
	
	@GetMapping("/search")
	public String searchCustomers(@RequestParam(required = false) String theSearchName, Model model) {
		
		List<Doctor> doctors = doctorDAO.getSearchResults(theSearchName);
		
		model.addAttribute("doctors", doctors);
		
		return "list-doctors";
	}
	
	@GetMapping("/viewAppointments")
	public String viewAppointments(@RequestParam("doctorId") int doctorId, 
									Model model) {
		
		List<Appointment> appointments = doctorDAO.getAppointments(doctorId);
		Doctor doctor = doctorDAO.getDoctor(doctorId);
		
		model.addAttribute("appointments", appointments);
		model.addAttribute("doctor", doctor);
		
		return "appointments/doctor-appointments";
		
	}
	
	@GetMapping("/deleteAppointment")
	public String deleteAppointment(@RequestParam("appointmentId") int appointmentId, Model model) {
		
		Appointment appointment = appointmentDao.getAppointment(appointmentId);
		
		appointmentDao.deleteById(appointmentId);
		
		return "redirect:/doctor/viewAppointments?doctorId=" + appointment.getDoctor().getId();
		
	}
	
	
	
	
}
