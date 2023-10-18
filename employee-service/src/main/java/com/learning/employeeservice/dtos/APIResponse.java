package com.learning.employeeservice.dtos;

public class APIResponse {

	private EmployeeResponse employeeResponse;
	
	private DepartmentResponse departmentResponse;

	private OrganizationResponse organizationResponse;
	
	public EmployeeResponse getEmployeeResponse() {
		return employeeResponse;
	}

	public void setEmployeeResponse(EmployeeResponse employeeResponse) {
		this.employeeResponse = employeeResponse;
	}

	public DepartmentResponse getDepartmentResponse() {
		return departmentResponse;
	}

	public void setDepartmentResponse(DepartmentResponse departmentResponse) {
		this.departmentResponse = departmentResponse;
	}

	public OrganizationResponse getOrganizationResponse() {
		return organizationResponse;
	}

	public void setOrganizationResponse(OrganizationResponse organizationResponse) {
		this.organizationResponse = organizationResponse;
	}
}
