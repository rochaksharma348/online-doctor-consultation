package com.luv2code.springdemo.controller;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.credentials.Credentials;
import com.luv2code.springdemo.dao.AppointmentDao;
import com.luv2code.springdemo.dao.DoctorDAO;
import com.luv2code.springdemo.dao.PatientDAO;
import com.luv2code.springdemo.entity.Appointment;
import com.luv2code.springdemo.entity.Doctor;
import com.luv2code.springdemo.entity.Patient;
import com.luv2code.springdemo.utils.SortUtils;

@Controller
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	private PatientDAO patientDAO;
	
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
	public String patientsPanel(Model model){
		Credentials patientCredentials = new Credentials();
		model.addAttribute("patientCredentials", patientCredentials);
		return "patients-panel";
	}
	
	@GetMapping("/logout")
	public String patientsPanelLogout(Model model){
		Credentials patientCredentials = new Credentials();
		model.addAttribute("patientCredentials", patientCredentials);
		return "logout/patients-panel";
	}
	
	@RequestMapping("/processLogin")
	public String loginSuccess(@Valid @ModelAttribute("patientCredentials") 
		Credentials patientCredentials, BindingResult bindingResult,
		Model model) {
		if (bindingResult.hasErrors()) {
			return "patients-panel";
		}
		Patient patient = patientDAO.getPatient(patientCredentials);
		if (patient != null) {
			model.addAttribute("patient", patient);
			return "dashboard/patient-dashboard";				
		} 
		return "invalidCredentials/patients-panel";
	}

	@RequestMapping("/list")
	public String customersList(@RequestParam(required = false)String sort, Model model) {
		
		List<Patient> patients = null;
		
		if (sort != null) {
			int sortType = Integer.parseInt(sort);
			
			patients = patientDAO.getPatients(sortType);
			
		} else {
			patients = patientDAO.getPatients(SortUtils.LAST_NAME);
		}
		
		model.addAttribute("patients", patients);
		return "list-patients";
	}
	
	@RequestMapping("/register")
	public String registerPatient(Model model) {
		Patient patient = new Patient();
		
		model.addAttribute("patient", patient);
		
		return "patient-register-form";
	}
	
	@PostMapping("/registerPatient")
	public String registerPatient(@Valid @ModelAttribute("patient") Patient patient,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "patient-register-form";
		} else {
			patientDAO.savePatient(patient);
			return "patient-registration-confirmation";
		}
	}
	
	
	@RequestMapping("/showFormForAddPatient")
	public String addPatientForm(Model model) {
		Patient patient = new Patient();
		
		model.addAttribute("patient", patient);
		
		return "patient-form";
	}
	
	@PostMapping("/savePatient")
	public String savePatient(@Valid @ModelAttribute("patient") Patient patient,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "patient-form";
		} else {
			patientDAO.savePatient(patient);
			return "redirect:/patient/list";
		}
	}
	
	
	
	@GetMapping("/showUpdateForm")
	public String updatePatientForm(@RequestParam("patientId") int patientId, Model model) {
		
		Patient patient = patientDAO.getPatient(patientId);
		
		model.addAttribute("patient", patient);
		
		return "patient-edit-form";
	}
	
	@GetMapping("/showEditForm")
	public String editPatientForm(@RequestParam("patientId") int patientId, Model model) {
		
		Patient patient = patientDAO.getPatient(patientId);
		
		model.addAttribute("patient", patient);
		
		return "edit-profile/patient-edit-form";
	}
		
	
	@PostMapping("/updatePatient")
	public String updatePatient(@Valid @ModelAttribute("patient") Patient patient,
			BindingResult bindingResult) {
		System.out.println("|" + patient.getUsername() + "|");
		
		System.out.println("Binding result: " + bindingResult);
		if (bindingResult.hasErrors()) {
			patientDAO.savePatient(patient);
			return "redirect:/patient/list";
		} 
		patientDAO.savePatient(patient);
		return "redirect:/patient/list";
	}
	
	@PostMapping("/editPatient")
	public String editPatient(@Valid @ModelAttribute("patient") Patient patient,
			BindingResult bindingResult) {
		System.out.println("|" + patient.getUsername() + "|");
		
		System.out.println("Binding result: " + bindingResult);
		if (bindingResult.hasErrors()) {
			patientDAO.savePatient(patient);
			return "redirect:/patient/dashboard?patientId=" + patient.getId();
		} 
		patientDAO.savePatient(patient);
		return "redirect:/patient/dashboard?patientId=" + patient.getId();
	}
	
	@GetMapping("/delete")
	public String deletePatient(@RequestParam("patientId") int patientId, Model model) {
		patientDAO.deletePatient(patientId);
		
		return "redirect:/patient/list";
	}
	
	@GetMapping("/search")
	public String searchPatients(@RequestParam(required = false) String theSearchName, Model model) {
		
		List<Patient> patients = patientDAO.getSearchResults(theSearchName);
		
		model.addAttribute("patients", patients);
		
		return "list-patients";
	}
	
	@GetMapping("/searchDoctor/{patientId}")
	public String searchDoctors(@RequestParam(required = false) String theSearchName,
								@PathVariable("patientId") int patientId, Model model) {
		
		List<Doctor> doctors = doctorDAO.getSearchResults(theSearchName);
		
		Patient patient = patientDAO.getPatient(patientId);
		
		model.addAttribute("patient", patient);
		model.addAttribute("doctors", doctors);
		
		return "appointments/list-doctors";
	}
	
	@GetMapping("/bookAppointment")
	public String bookAppointment(@RequestParam("patientId") int id, Model model) {
		
		Patient patient = patientDAO.getPatient(id);
		
		List<Doctor> doctors = doctorDAO.getDoctors(SortUtils.LAST_NAME);
		
		model.addAttribute("patient", patient);
		model.addAttribute("doctors", doctors);
		
		return "appointments/list-doctors";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(@RequestParam("patientId") int id, Model model) {
		
		Patient patient = patientDAO.getPatient(id);
		
		model.addAttribute("patient", patient);
		
		return "dashboard/patient-dashboard";
	}
	
	@GetMapping("/showAppointmentForm")
	public String appointmentForm(@RequestParam Map<String, String> requestParams,
								  Model model) {
		
		int patientId = Integer.parseInt(requestParams.get("patientId"));
		int doctorId = Integer.parseInt(requestParams.get("doctorId"));
		
		Patient patient = patientDAO.getPatient(patientId);
		Doctor doctor = doctorDAO.getDoctor(doctorId);
		
		Appointment appointment = new Appointment();
		appointment.setDoctor(doctor);
		appointment.setPatient(patient);
		
		model.addAttribute("appointment", appointment);
		
		return "appointments/appointment-form";
				
	}
	
	@GetMapping("/showUpdateAppointmentForm")
	public String updateAppointmentForm(@RequestParam("appointmentId") int appointmentId, Model model) {
		
		Appointment appointment = appointmentDao.getAppointment(appointmentId);
		
		model.addAttribute("appointment", appointment);
		
		return "appointments/appointment-update-form";
				
	}
	
	
	@PostMapping("/saveAppointment/{patientId}/{doctorId}")
	public String saveAppointment(@Valid @ModelAttribute("appointment") Appointment appointment,
								  @PathVariable("patientId") int patientId,
								  @PathVariable("doctorId") int doctorId,
								  BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "appointments/appointment-form";
		} else {
			appointment.setDoctor(doctorDAO.getDoctor(doctorId));
			appointment.setPatient(patientDAO.getPatient(patientId));
			
			System.out.println(appointment.getDoctor().getFirstName());
			System.out.println(appointment.getPatient().getFirstName());
			patientDAO.saveAppointment(appointment);
			return "redirect:/patient/viewAppointments?patientId=" + patientId;
		}
	}
	
	@GetMapping("/viewAppointments")
	public String viewAppointments(@RequestParam("patientId") int patientId, Model model) {
		
		List<Appointment> appointments = patientDAO.getAppointments(patientId);
		Patient patient = patientDAO.getPatient(patientId);
		
		model.addAttribute("appointments", appointments);
		model.addAttribute("patient", patient);
		
		return "appointments/patient-appointments";
	}
	
	@GetMapping("/deleteAppointment")
	public String deleteAppointmet(@RequestParam("appointmentId") int appointmentId, Model model) {
		
		Appointment appointment = appointmentDao.getAppointment(appointmentId);
		
		appointmentDao.deleteById(appointmentId);
		
		return "redirect:/patient/viewAppointments?patientId=" + appointment.getPatient().getId();
	}

}
	










