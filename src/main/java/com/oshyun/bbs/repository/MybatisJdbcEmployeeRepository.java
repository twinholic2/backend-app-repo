package com.oshyun.bbs.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.oshyun.bbs.domain.Employee;

@Repository
public class MybatisJdbcEmployeeRepository implements EmployeeRepository {
	
	protected static final String NAMESPACE = "com.oshyun.bbs.mapper.EmployeeMapper.";
	
	public MybatisJdbcEmployeeRepository() {}
	
	@Autowired
	@Qualifier("SessionTemplate")
	private SqlSessionTemplate  sqlSession;

	@Override
	public Employee save(Employee employee) {
		sqlSession.insert(NAMESPACE+"insertEmployee", employee);
		return employee;
	}

	@Override
	public Optional<Employee> update(Employee employee) {
		sqlSession.update(NAMESPACE + "updateEmployee",employee);
		return Optional.of(employee);
	}

	@Override
	public void delete(Long id) {
		sqlSession.delete(NAMESPACE+"deleteEmployee",id);
	}

	@Override
	public Optional<Employee> getFindId(Long id) {
		return Optional.ofNullable(sqlSession.selectOne(NAMESPACE + "findById", id));
	}

	@Override
	public Optional<Employee> getFindByName(String name) {
		Employee employee = sqlSession.selectOne(NAMESPACE + "findByName",name);
		return Optional.ofNullable(employee);
	}

	@Override
	public List<Employee> getList() {
		return sqlSession.selectList(NAMESPACE +"getEmployeeList"); 
	}

}
