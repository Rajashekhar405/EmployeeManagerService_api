package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.CustomException;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployee(Integer pageNo, Integer pageSize, String sortBy) throws CustomException{
		/*List<Employee> empList = employeeRepository.findAll();
		if(null!=empList && !empList.isEmpty()) {
			return  empList;
		}else {
			throw new CustomException("No Records Found");
		}*/
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Employee> pageReslt = employeeRepository.findAll(pageable);
		System.out.println(pageReslt.getTotalElements());
		if(pageReslt.hasContent()) {
			return pageReslt.getContent();
		}else{
			return new ArrayList<Employee>();
		}
	}

	public Employee getEmployeeById(int id) throws CustomException {
		Optional<Employee> emp =  employeeRepository.findById(id);
		return emp.get();
	}

	public Employee createEmployee(Employee emp) {
		Employee emp1 = employeeRepository.save(emp);
		return emp1;
	}

	public Employee UpdateEmployee(Employee emp) throws CustomException{
		Optional<Employee> empDetails = employeeRepository.findById(emp.getId());
		Employee employee = empDetails.get();
		employee.setDesignation(emp.getDesignation());
		employee.setBand(emp.getBand());
		employee.setFirstName(emp.getFirstName());
		employee.setExperience(emp.getExperience());
		return employeeRepository.save(employee);
	}

	public Optional<Employee> deleteEmployeeById(int id) throws CustomException {
		Optional<Employee> emp1 = employeeRepository.findById(id);
		if(null!=emp1 && emp1.isPresent()) 
		{
			employeeRepository.deleteById(id);
		} else {
			new CustomException("Employee ID Does Not Exist"+id);
		}
		return emp1;
	}

	public String deleteEmployees() throws CustomException{
		try {
			employeeRepository.deleteAll();
		} catch (Exception e) {
			throw new CustomException("While Deleting, Something Went wrong");
		}
		return "Deleted All the records";
	}

	public List<?> groupByEmployees() throws CustomException {
		return employeeRepository.groupByEmployees();

	}

	public List<Employee> getEmployeePerPage(Integer pageNo, Integer pageSize, String sortBy) throws CustomException{
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Employee> pageReslt = employeeRepository.findAll(pageable);
		System.out.println(pageReslt.getTotalElements());
		if(pageReslt.hasContent()) {
			return pageReslt.getContent();
		}else{
			return new ArrayList<Employee>();
		}
	}

	public List<Employee> getByEmployeeDesignation(String desig) throws CustomException {
		return employeeRepository.findByDesignation(desig);
	}

	public List<Employee> createEmployeeInBulk(List<Employee> emplst){
		return employeeRepository.saveAll(emplst);
	}
	
	public List<?> findEmployeeDetails(String keyword) {
		return employeeRepository.findUsersByKeyword(keyword);
	}

}
