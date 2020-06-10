package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/employees")
@RestControllerAdvice
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping
	@ApiOperation(value = "Get All Employees List")
	public ResponseEntity<List<Employee>> getAllEmployee() {
		List<Employee> empList = employeeService.getAllEmployee();
		return new ResponseEntity<>(empList, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Get Employee By EmployeeID")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
		Employee employee = employeeService.getEmployeeById(id);
		return new ResponseEntity<Employee>(employee, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = "Creating new Employee")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee emp) {
		Employee updated = employeeService.createEmployee(emp);
        return new ResponseEntity<Employee>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Updating Employee Based on EmployeeID")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int id, @RequestBody Employee emp) {
		Employee updated = employeeService.UpdateEmployee(emp);
        return new ResponseEntity<Employee>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}") 
	@ApiOperation(value = "Deleting Employee Based on EmployeeID")
	public ResponseEntity<Employee> deleteEmployeeById(@PathVariable int id) {
		Optional<Employee> emp = employeeService.deleteEmployeeById(id);
		 return new ResponseEntity<Employee>(emp.get(), new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping
	@ApiOperation(value = "Deleting All Employee")
	public ResponseEntity<String> deleteEmployees() {
		return employeeService.deleteEmployees();
	}
}
