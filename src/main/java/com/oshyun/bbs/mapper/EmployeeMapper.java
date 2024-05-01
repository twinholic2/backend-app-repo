package com.oshyun.bbs.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.oshyun.bbs.domain.Employee;

@Mapper
@Repository
public interface EmployeeMapper {
	int insertEmployee(Employee employee);
	int updateEmployee(Employee employee);
	void deleteEmployee(Long id);
	Employee findById(Long id);
	Employee findByName(String name);
	List<Employee> getEmployeeList();
}
