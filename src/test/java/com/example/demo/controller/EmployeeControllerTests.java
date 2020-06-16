package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.Exception.RecordNotFoundException;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;



@SpringBootTest
@SpringJUnitConfig
@SpringJUnitWebConfig
public class EmployeeControllerTests {

	public EmployeeController employeeController;	

	@InjectMocks
	private EmployeeService employeeService;
	
	@Mock
	private Employee employee;

	
	List<Employee> emplst;

	@Before
	public void setUpInits(){
		MockitoAnnotations.initMocks(employeeController);
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
	public void testFindEmployeeDetails() throws RecordNotFoundException {
		System.out.println("inside method");
		List<Employee> emplst = new ArrayList<Employee>();
		Employee emp = new Employee();
		emp.setBand("E6");
		emp.setDesignation("MANAGER");
		emp.setEmail("");
		emp.setFirstName("abhishek");
		emp.setLastName("aaladahalli");
		emp.setExperience("15");
		emp.setId(1);
		emplst.add(emp);
		doReturn(emplst).when(employeeService).findEmployeeDetails(anyString());
		employeeController.findEmployeeDetails("1");
		assertEquals(emp, emplst.get(0));
	}

	@Test
	public void testFindEmployeeDetailsElse() throws RecordNotFoundException {
		System.out.println("inside method");
		List<Employee> emplst = new ArrayList<Employee>();
		doReturn(emplst).when(employeeService).findEmployeeDetails(anyString());
		employeeController.findEmployeeDetails("E1");
		assertEquals("E6", emplst.get(0).getBand());
	}

	@Test
	public void testFindEmployeeDetailsException() throws RecordNotFoundException {
		try {
			System.out.println("inside method");
			List<Employee> emplst = new ArrayList<Employee>();
			doReturn(null).when(employeeService).createEmployeeInBulk(anyList());
			employeeController.findEmployeeDetails("MANAGER");
			assertEquals(null, emplst.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAllEmployee() {
		try {
			List<Employee> empList = new ArrayList<>();
			Employee emp = new Employee();
			emp.setBand("E6");
			emp.setDesignation("MANAGER");
			emp.setEmail("");
			emp.setFirstName("abhishek");
			emp.setLastName("aaladahalli");
			emp.setExperience("15");
			emp.setId(1);
			empList.add(emp);
			
			Employee emp1 = new Employee();
			emp1.setBand("E6");
			emp1.setDesignation("MANAGER");
			emp1.setEmail("");
			emp1.setFirstName("abhishek");
			emp1.setLastName("aaladahalli");
			emp1.setExperience("15");
			emp1.setId(1);
			
			empList.add(emp1);
			
			doReturn(empList).when(employeeService).getAllEmployee(0, 3, "id");
			employeeController.getAllEmployee(0, 3, "id");
			assertEquals(1, empList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@ExceptionHandler(Exception.class)
	public void testGetAllEmployeeElse() {
		try {
			List<Employee> empList = new ArrayList<>();
			doReturn("").when(employeeService).getAllEmployee(0, 3, "id");
			employeeController.getAllEmployee(0, 3, "id");
			assertEquals("", empList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	public void testGetGroupByEmployees() {
		
	}
}
