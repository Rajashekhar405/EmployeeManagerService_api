package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.example.demo.Exception.RecordNotFoundException;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/employees")
@RestControllerAdvice
public class EmployeeController {

	private static final Logger LOGGER=LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@SuppressWarnings("unchecked")
	@GetMapping("/{searchkey}")
	@ApiOperation("common search")
	public ResponseEntity<List<Employee>> findEmployeeDetails(@PathVariable("searchkey") String searchkey) throws RecordNotFoundException{
		LOGGER.info("Inside findEmployeeDetails " + searchkey);
		List<Employee> employeeList =  (List<Employee>) employeeService.findEmployeeDetails(searchkey);
		if(!employeeList.isEmpty()) {
			return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
		}
		return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
	}

	@GetMapping
	@ApiOperation(value = "Get All Employees List")
	public ResponseEntity<List<Employee>> getAllEmployee(@RequestParam(defaultValue = "0") Integer pageNo, 
			@RequestParam(defaultValue = "1000", required = false) Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy) throws CustomException{
		LOGGER.debug("************Inside getAllEmployee... ****" + sortBy);
		List<Employee> empList = employeeService.getAllEmployee(pageNo, pageSize, sortBy);
		LOGGER.info("Employee Rsponse=== " + empList.toString());
		if(null!=empList && !empList.isEmpty()) {
			return new ResponseEntity<>(empList, new HttpHeaders(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>( HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	@ApiOperation(value = "Creating new Employee/s")
	public ResponseEntity<List<Employee>> createEmployeeInBulk(@RequestBody List<Employee> emplst) {
		LOGGER.debug("############ Inside createEmployeeInBulk########## " + emplst);
		List<Employee> emplist = null;
		emplist = employeeService.createEmployeeInBulk(emplst);
		if(null!=emplist && emplist.isEmpty()) {
			return new ResponseEntity<List<Employee>>(emplist, new HttpHeaders(), HttpStatus.OK);
		}else {
			LOGGER.error("Exception occured while creating employee/s");
			return new ResponseEntity<List<Employee>>(null, new HttpHeaders(), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Updating Employee Based on EmployeeID")
	public ResponseEntity<Object> updateEmployee(@PathVariable("id") int id, @RequestBody Employee emp) throws CustomException {
		employeeService.UpdateEmployee(emp);
		LOGGER.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~UPDATED SUCCESSFULLY~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		// return new ResponseEntity<Employee>(updated, new HttpHeaders(), HttpStatus.OK);
		return new ResponseEntity<>("Employee is updated successfully", HttpStatus.OK);
	}

	@DeleteMapping("/{id}") 
	@ApiOperation(value = "Deleting Employee Based on EmployeeID")
	public ResponseEntity<Object> deleteEmployeeById(@PathVariable int id) throws CustomException {
		Optional<Employee> emp = employeeService.deleteEmployeeById(id);
		if(emp.isPresent() && null!=emp) {
			LOGGER.debug("!!!!!!! Employee list is !!!!!!!!" + emp);
			//return new ResponseEntity<Employee>(emp.get(), new HttpHeaders(), HttpStatus.OK);
			return new ResponseEntity<>("Deleted Employee successsfully", HttpStatus.OK);
		}else {
			LOGGER.debug("Employee List is empty"+ emp.get());
			return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
		}
	}

	/*@DeleteMapping
	@ApiOperation(value = "Deleting All Employee")
	public ResponseEntity<Object> deleteEmployees() {
		employeeService.deleteEmployees();
		return new ResponseEntity<>("Deleted All the Employees successsfully", HttpStatus.OK);
	}*/

	/*@GetMapping("/{id}")
	@ApiOperation(value = "Get Employee By EmployeeID")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) throws RecordNotFoundException {
		Employee employee = employeeService.getEmployeeById(id);
		if(employee ==null) {
			throw new RecordNotFoundException("Employee id not found"+ id);
		}
		return new ResponseEntity<Employee>(employee, new HttpHeaders(), HttpStatus.OK);
	}*/

	/*@PostMapping
	@ApiOperation(value = "Creating new Employee")
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee emp) throws CustomException {
		if(StringUtils.isBlank(emp.getFirstName())) {
			throw new InvalidFiledException("Employee Name is required");
		}
		Employee updated = employeeService.createEmployee(emp);
		return new ResponseEntity<Employee>(updated, new HttpHeaders(), HttpStatus.OK);
	}
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("/groupby")
	public List<Employee> getGroupByEmployees() throws CustomException {
		return (List<Employee>) employeeService.groupByEmployees();
	}

	/*@GetMapping("/pagination")
	@ApiOperation(value = "Get Employees by No of Records and sorting order per page")
	public ResponseEntity<List<Employee>> getEmployeePerPage(@RequestParam(defaultValue = "0") Integer pageNo, 
			@RequestParam(defaultValue = "5") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy) {
		List<Employee> empList = employeeService.getEmployeePerPage(pageNo, pageSize, sortBy);
		return new ResponseEntity<>(empList, new HttpHeaders(), HttpStatus.OK);
	}*/

	/*@ApiOperation(value = "Getting employee details by employee designation")
	@GetMapping("employee/{desig}")
	public ResponseEntity<List<Employee>> getByEmployeeDesignation(@PathVariable("desig") String desig) throws CustomException {
		List<Employee> employee = employeeService.getByEmployeeDesignation(desig);
		return new ResponseEntity<List<Employee>>(employee, new HttpHeaders(), HttpStatus.OK);

	}

	@PostMapping
	@ApiOperation(value = "Creating new Employee/s")
	public ResponseEntity<List<Employee>> createEmployeeInBulk(@RequestBody List<Employee> emplst) {
		List<Employee> emplist = null;
			emplist = employeeService.createEmployeeInBulk(emplst);
			if(null!=emplist && emplist.isEmpty()) {
				return new ResponseEntity<List<Employee>>(emplist, new HttpHeaders(), HttpStatus.OK);
			}else {
				return new ResponseEntity<List<Employee>>(emplist, new HttpHeaders(), HttpStatus.EXPECTATION_FAILED);
			}
	}*/

	/*@ExceptionHandler
	public String handleInvalidFiledException(InvalidFileNameException exception) {
		return exception.getMessage();
	}*/
}
