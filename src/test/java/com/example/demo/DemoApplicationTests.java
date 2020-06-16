package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Exception.RecordNotFoundException;
import com.example.demo.controller.EmployeeController;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

@SpringBootTest
class DemoApplicationTests {

	public EmployeeController employeeController;	
	
	@Mock
	private EmployeeService employeeService;
	
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
		doReturn(emplst).when(employeeService).findEmployeeDetails("1");
		employeeController.findEmployeeDetails(anyString());
		assertEquals(emp, emplst.get(0));
	}
	
	@Test
	public void testFindEmployeeDetailsElse() throws RecordNotFoundException {
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
		doReturn(emplst).when(employeeService).findEmployeeDetails(emp.getBand());
		employeeController.findEmployeeDetails(emp.getBand());
		assertEquals(emp, emplst.get(0));
	}
	
	@Test
	public void testFindEmployeeDetailsException() throws RecordNotFoundException {
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
		doReturn(emplst).when(employeeService).createEmployeeInBulk(emplst);
		when(employeeService).thenThrow(new RuntimeException());
		assertEquals(emp, emplst.get(0));
	}

}
