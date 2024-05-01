package com.oshyun.bbs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oshyun.bbs.domain.Employee;
import com.oshyun.bbs.mapper.EmployeeMapper;

@Service
public class MyBatisEmployeeService {
	
	@Autowired
	EmployeeMapper totalMapper;
	
	public Employee join(Employee employee) {
		try {
			deplicatedEmployee(employee);
		}catch (Exception e) {
			//throw new Exception("중복된 사용자입니다.");
			System.out.println(e.getMessage());
		}
		totalMapper.insertEmployee(employee);
		return employee;
	}
	public Employee update(Employee employee) {

		totalMapper.updateEmployee(employee);
		return employee;
	}
	public void delete(Long id) {
		
		totalMapper.deleteEmployee(id);
	}

	private void deplicatedEmployee(Employee employee){
		Optional.ofNullable(totalMapper.findByName(employee.getFirstName()))
			.ifPresent(m -> {throw new IllegalStateException("중복된 사용자입니다.");});
	}
	
	public List<Employee> getEmployeeList() {
		return totalMapper.getEmployeeList();
	}
	
	public Optional<Employee> getFindOne(Long id) {
		return Optional.ofNullable(totalMapper.findById(id));
	}
}
