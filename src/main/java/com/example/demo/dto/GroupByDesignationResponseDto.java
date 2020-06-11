package com.example.demo.dto;

public class GroupByDesignationResponseDto {

	private String desig;
	private int count;
	private int totalCount;
	
	public GroupByDesignationResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getDesig() {
		return desig;
	}
	public void setDesig(String desig) {
		this.desig = desig;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
