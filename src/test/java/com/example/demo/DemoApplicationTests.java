package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Exception.RecordNotFoundException;
import com.example.demo.controller.EmployeeController;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

@SpringBootTest
class DemoApplicationTests {

	@Mock
	public EmployeeController employeeCOntroller;	
	
	@Mock
	private EmployeeService employeeService;
	
	
	@Test
	public void getEmployeeById() throws RecordNotFoundException {
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
		assertEquals(emp, emplst.get(0));
	}

}
