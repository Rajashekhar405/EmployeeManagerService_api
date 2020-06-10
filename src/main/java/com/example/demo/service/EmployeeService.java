package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.RecordNotFoundException;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployee() {
		try {
			return employeeRepository.findAll();
		} catch (Exception e) {
			return new ArrayList<Employee>();
		}
	}

	public Employee getEmployeeById(int id) {
		Optional<Employee> emp =  employeeRepository.findById(id);
		if(emp.isPresent()) {
			Employee empp = emp.get();
			return empp;
		}else {
			return new Employee();
		}
	}

	public Employee createEmployee(Employee emp) {
			Employee emp1 = employeeRepository.save(emp);
			return emp1;
	}

	public Employee UpdateEmployee(Employee emp) {
		Optional<Employee> empDetails = employeeRepository.findById(emp.getId());
			Employee employee = empDetails.get();
			employee.setDesignation(emp.getDesignation());
			employee.seteBand(emp.geteBand());
			employee.seteName(emp.geteName());
			employee.setExperience(emp.getExperience());
			return employeeRepository.save(employee);
	}

	public Optional<Employee> deleteEmployeeById(int id) {
		Optional<Employee> emp1 = null;
		try {
			emp1 = employeeRepository.findById(id);
			if(null!=emp1 && emp1.isPresent()) 
	        {
				employeeRepository.deleteById(id);
				return emp1;
	        } else {
	            throw new RecordNotFoundException("No employee record exist for given id");
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp1;
		
	}

	public ResponseEntity<String> deleteEmployees() {
		try {
			employeeRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

}
