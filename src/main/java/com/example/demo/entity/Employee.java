package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class Employee {
	
	@Id
	@GeneratedValue
	private int id;
	private String eName;
	private String eBand;
	private String experience;
	private String designation;
	
	public Employee() {
		//System.out.println("Default constructor called");
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public String geteBand() {
		return eBand;
	}
	public void seteBand(String eBand) {
		this.eBand = eBand;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", eName=" + eName + ", eBand=" + eBand + ", experience=" + experience
				+ ", designation=" + designation + "]";
	}
}
