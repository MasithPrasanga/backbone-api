package com.virtusa.employee.controller;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.virtusa.employee.entity.Employee;
import com.virtusa.employee.service.EmployeeService;
import com.virtusa.employee.service.EmployeeServiceImpl;

@Path("/employee")
public class EmployeeController {
	
	EmployeeService employeeService = new EmployeeServiceImpl();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> readAll(){
		return employeeService.readAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Employee getEmployeeById(@PathParam("id") int id) {
		return employeeService.read(id);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Employee createEmployee(Employee employee) {
		employeeService.insert(employee);
		return employee;
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Employee updateEmployee(@PathParam("id") int id, Employee employee) {
		employeeService.update(employee);
		return employee;
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Employee deleteEmployee(@PathParam("id") int id) {
		Employee employee = employeeService.read(id);
		employeeService.delete(employee);
		return employee;
	}

}
