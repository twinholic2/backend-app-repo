package com.oshyun.bbs.service;

import java.util.List;
import java.util.Optional;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.oshyun.bbs.domain.Employee;
import com.oshyun.bbs.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	//final EmployeeRepository employeeRepository;
	
//	@Autowired
//	public EmployeeService (EmployeeRepository employeeRepository) {
//		this.employeeRepository = employeeRepository;
//	}
	
	@Autowired
	@Qualifier("mybatisRepository")
	private EmployeeRepository  employeeRepository;
	
	public Employee join(Employee employee) {
		try {
			deplicatedEmployee(employee);
		}catch (Exception e) {
			//throw new Exception("중복된 사용자입니다.");
			System.out.println(e.getMessage());
		}
		employeeRepository.save(employee);
		return employee;
	}
	public Employee update(Employee employee) {

		employeeRepository.update(employee);
		return employee;
	}
	public void delete(Long id) {
		
		employeeRepository.delete(id);
	}

	private void deplicatedEmployee(Employee employee){
		employeeRepository.getFindByName(employee.getFirstName())
			.ifPresent(m -> {throw new IllegalStateException("중복된 사용자입니다.");});
	}
	
	public List<Employee> getEmployeeList() {
		return employeeRepository.getList();
	}
	
	public Optional<Employee> getFindOne(Long id) {
		return employeeRepository.getFindId(id);
	}
}
