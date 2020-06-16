package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@SpringBootTest
public class EmployeeServiceTest {
	
	@InjectMocks
	private EmployeeService employeeService;
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	List<Employee> emplst;
	
	@Before
	public void setups() {
		MockitoAnnotations.initMocks(employeeService);
	}
	
	@Before
	public void dataSetups() {
		emplst = new ArrayList<Employee>();
		Employee emp = new Employee();
		emp.setBand("E6");
		emp.setDesignation("MANAGER");
		emp.setEmail("");
		emp.setFirstName("abhishek");
		emp.setLastName("aaladahalli");
		emp.setExperience("15");
		emp.setId(1);
		emplst.add(emp);
	}

	
	@Test
	public void testGetAllEmployee() {
		doReturn(emplst).when(employeeRepository).findAll();
		List<Employee> result =  employeeService.getAllEmployee(0,5,"id");
		assertEquals(1, result.size());
	}
	
	@Test
	public void testGetEmployeeById() {
		
	}
	
	@Test
	public void testCreateEmployeeInBulk() {
		
	}
	
	@Test
	public void testUpdateEmployee() {
		
	}
	
	@Test
	public void testDeleteEmployeeById() {
		
	}
	
	@Test
	public void testGroupByEmployees() {
		
	}
	
	@Test
	public void testFindEmployeeDetails() {
		
	}
}
