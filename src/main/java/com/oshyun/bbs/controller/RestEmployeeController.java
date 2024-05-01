package com.oshyun.bbs.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oshyun.bbs.domain.Employee;
import com.oshyun.bbs.service.MyBatisEmployeeService;

@RestController
@RequestMapping("/api")
public class RestEmployeeController {
	
	private final MyBatisEmployeeService service;
	
	public RestEmployeeController(MyBatisEmployeeService service) {
		this.service = service;
	}
	
	@GetMapping("/v1/healthz")
	public String healthCheck() {
		return "SUCCESS1";
	}
	
	@GetMapping("/employee")
	public List<Employee> getEmployeeList() {
		return service.getEmployeeList();
	}
	
	
	@PostMapping("/employee/add")
	public String addEmployee(@RequestBody Employee employee) {
		service.join(employee);
		return "redirect:/";
	}
	
	@PostMapping("/employee/update")
	public String updateEmployee(@RequestBody Employee employee) {
		service.update(employee);
		return "redirect:/";
	}
	
	@GetMapping("/employee/delete")
	public String deleteEmployee(@RequestParam("id") String id) {
		service.delete(Long.parseLong(id));
		return "SUCCESS";
	}
	
	@GetMapping("/employee/view")
	public Employee findEmployee(@RequestParam("id") Long id) {
		return service.getFindOne(id).get();
	}
	@GetMapping("/employee/view/{id}")
	public Employee getEmployeeOne(@PathVariable("id") Long id) {
		return service.getFindOne(id).get();
	}
}
