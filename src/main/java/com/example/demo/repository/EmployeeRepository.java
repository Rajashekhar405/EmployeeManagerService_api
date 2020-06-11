package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	@Query(value = "select designation, count(1) from Employee group by designation", nativeQuery = true)
	public List<?> groupByEmployees();
	
	/*
	 * @Query(value =
	 * "select designation, count(1) from Employee group by designation",
	 * nativeQuery = true) public List<?> GroupByDesignationOne();
	 */
	
	public List<Employee> findByDesignation(String desig);

}
