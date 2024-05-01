package com.oshyun.bbs.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oshyun.bbs.domain.Employee;
import com.oshyun.bbs.service.EmployeeService;
import com.oshyun.bbs.service.MyBatisEmployeeService;

@Controller
public class EmployeeController {
	
	/*
	private final EmployeeService service;
	
	public EmployeeController(EmployeeService service) {
		this.service = service;
	}*/
	
	
	private final MyBatisEmployeeService service;
	
	public EmployeeController(MyBatisEmployeeService service) {
		this.service = service;
	}

	@GetMapping("/")
	public String getEmployeeList(Model model) {
		model.addAttribute("employees", service.getEmployeeList());
		return "/employee/list";
	}
	
	@GetMapping("/employee/employeeForm")
	public String employeeForm() {
		return "/employee/employeeForm";
	}
	@GetMapping("/employee/updateForm")
	public String employeeForm(@RequestParam("id") String id, Model model) {
		model.addAttribute("employee", service.getFindOne(Long.parseLong(id)).get());
		return "/employee/updateForm";
	}
	
	@PostMapping("/employee/add")
	public String addEmployee(Employee employee) {
		service.join(employee);
		return "redirect:/";
	}
	
	@PostMapping("/employee/update")
	public String updateEmployee(Employee employee) {
		service.update(employee);
		return "redirect:/";
	}
	
	@GetMapping("/employee/delete")
	public String deleteEmployee(@RequestParam("id") String id) {
		service.delete(Long.parseLong(id));
		return "redirect:/";
	}
	
	@GetMapping("/employee/view")
	public String findEmployee(@RequestParam("id") Long id, Model model) {
		model.addAttribute("employee", service.getFindOne(id).get());
		return "/employee/employeeView";
	}
	@GetMapping("/employee/view/{id}")
	public String getEmployeeOne(@PathVariable("id") Long id, Model model) {
		model.addAttribute("employee", service.getFindOne(id).get());
		return "/employee/employeeForm";
	}
}
