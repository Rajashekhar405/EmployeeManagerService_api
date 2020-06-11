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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.Exception.CustomException;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.RecordNotFoundException;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/employees")
@RestControllerAdvice
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping
	@ApiOperation(value = "Get All Employees List")
	public ResponseEntity<List<Employee>> getAllEmployee() throws CustomException{
		List<Employee> empList = employeeService.getAllEmployee();
		return new ResponseEntity<>(empList, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Get Employee By EmployeeID")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) throws CustomException {
		Employee employee = employeeService.getEmployeeById(id);
		return new ResponseEntity<Employee>(employee, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = "Creating new Employee")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee emp) throws CustomException {
		Employee updated = employeeService.createEmployee(emp);
		return new ResponseEntity<Employee>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Updating Employee Based on EmployeeID")
	public ResponseEntity<Object> updateEmployee(@PathVariable("id") int id, @RequestBody Employee emp) throws CustomException {
		employeeService.UpdateEmployee(emp);
		// return new ResponseEntity<Employee>(updated, new HttpHeaders(), HttpStatus.OK);
		return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
	}

	@DeleteMapping("/{id}") 
	@ApiOperation(value = "Deleting Employee Based on EmployeeID")
	public ResponseEntity<Employee> deleteEmployeeById(@PathVariable int id) throws CustomException {
		Optional<Employee> emp = employeeService.deleteEmployeeById(id);
		if(emp.isPresent() && null!=emp) {
		return new ResponseEntity<Employee>(emp.get(), new HttpHeaders(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping
	@ApiOperation(value = "Deleting All Employee")
	public HttpStatus deleteEmployees() {
		employeeService.deleteEmployees();
		return HttpStatus.OK;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/groupby")
	public List<Employee> getGroupByEmployees() throws CustomException {
		return (List<Employee>) employeeService.groupByEmployees();
	}

	@GetMapping("/pagination")
	@ApiOperation(value = "Get Employees by No of Records and sorting order per page")
	public ResponseEntity<List<Employee>> getEmployeePerPage(@RequestParam(defaultValue = "0") Integer pageNo, 
			@RequestParam(defaultValue = "5") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy) {
		List<Employee> empList = employeeService.getEmployeePerPage(pageNo, pageSize, sortBy);
		return new ResponseEntity<>(empList, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "Getting employee details by employee designation")
	@GetMapping("employee/{desig}")
	public ResponseEntity<List<Employee>> getByEmployeeDesignation(@PathVariable("desig") String desig) throws CustomException {
		List<Employee> employee = employeeService.getByEmployeeDesignation(desig);
		return new ResponseEntity<List<Employee>>(employee, new HttpHeaders(), HttpStatus.OK);

	}
}
