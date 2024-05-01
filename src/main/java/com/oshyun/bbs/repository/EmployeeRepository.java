package com.oshyun.bbs.repository;

import java.util.List;
import java.util.Optional;

import com.oshyun.bbs.domain.Employee;

public interface EmployeeRepository {
	Employee save(Employee employee);
	Optional<Employee> update(Employee employee);
	void delete(Long id);
	Optional<Employee> getFindId(Long id);
	Optional<Employee> getFindByName(String name);
	List<Employee> getList();
}
