package com.virtusa.employee.repository;

import java.util.List;
import com.virtusa.employee.entity.Employee;

public interface EmployeeDao {
	
	public void insert(Employee employee);
	public Employee read(int id);
	public List<Employee> readAll();
	public void update(Employee employee);
	public void delete(Employee employee);

}
