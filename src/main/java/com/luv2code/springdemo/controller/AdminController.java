package com.luv2code.springdemo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.credentials.Credentials;
import com.luv2code.springdemo.dao.AdminDao;
import com.luv2code.springdemo.dao.AppointmentDao;
import com.luv2code.springdemo.entity.Admin;
import com.luv2code.springdemo.entity.Appointment;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private AppointmentDao appointmentDao;

	@RequestMapping("/login")
	public String login(Model model){
		Credentials adminCredentials = new Credentials();
		model.addAttribute("adminCredentials", adminCredentials);
		return "admins-panel";
	}
	
	@RequestMapping("/logout")
	public String logout(Model model){
		Credentials adminCredentials = new Credentials();
		model.addAttribute("adminCredentials", adminCredentials);
		return "logout/admins-panel";
	}
	
	@RequestMapping("/processLogin")
	public String loginSuccess(@Valid @ModelAttribute("adminCredentials") 
		Credentials adminCredentials, BindingResult bindingResult,
		Model model) {
		if (bindingResult.hasErrors()) {
			return "admins-panel";
		}
		Admin admin = adminDao.getAdmin(adminCredentials);
		if (admin != null) {
			model.addAttribute("admin", admin);
			return "dashboard/admin-dashboard";				
		} 
		return "invalidCredentials/admins-panel";
	}
	
	@RequestMapping("/dashboard")
	public String dashBoard() {
		return "dashboard/admin-dashboard";
	}
	
	@GetMapping("/showAppointments")
	public String showAppointments(Model model) {
		List<Appointment> appointments = appointmentDao.getAppointments();
		
		model.addAttribute("appointments", appointments);
		
		return "appointments/list-appointments";
	}
	
	@GetMapping("/deleteAppointment")
	public String deleteAppointment(@RequestParam("appointmentId") int appointmentId, Model model) {
		
		appointmentDao.deleteById(appointmentId);
		
		return "redirect:/admin/showAppointments";
	}
}
