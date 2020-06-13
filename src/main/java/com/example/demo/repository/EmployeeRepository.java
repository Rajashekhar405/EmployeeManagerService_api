package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	@Query(value = "select designation, count(1) from EMPLOYEE_DETAILS group by designation", nativeQuery = true)
	public List<?> groupByEmployees();
	
	/*
	 * @Query(value =
	 * "select designation, count(1) from Employee group by designation",
	 * nativeQuery = true) public List<?> GroupByDesignationOne();
	 */
	
	public List<Employee> findByDesignation(String desig);
	
	@Query(value="select * from EMPLOYEE_DETAILS u where u.first_name like %:keyword% or u.last_name like %:keyword% or u.id like %:keyword% or u.band like %:keyword% or u.designation like %:keyword% or u.email like %:keyword% or u.experience like %:keyword%", nativeQuery=true)
	List<Employee> findUsersByKeyword(@Param("keyword") String keyword);

}
