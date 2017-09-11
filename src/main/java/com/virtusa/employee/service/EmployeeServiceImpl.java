package com.virtusa.employee.service;

import java.util.List;
import com.virtusa.employee.entity.Employee;
import com.virtusa.employee.repository.EmployeeDao;
import com.virtusa.employee.repository.EmployeeDaoImpl;

public class EmployeeServiceImpl implements EmployeeService {

	EmployeeDao employeeDao = new EmployeeDaoImpl(); 
	
	@Override
	public void insert(Employee employee) {
		employeeDao.insert(employee);
	}

	@Override
	public Employee read(int id) {
		return employeeDao.read(id);
	}

	@Override
	public List<Employee> readAll() {
		return employeeDao.readAll();
	}

	@Override
	public void update(Employee employee) {
		employeeDao.update(employee);
	}

	@Override
	public void delete(Employee employee) {
		employeeDao.delete(employee);
	}
}
