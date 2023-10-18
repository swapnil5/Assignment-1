package com.learning.employeeservice.service;

import org.springframework.stereotype.Service;

import com.learning.employeeservice.dtos.APIResponse;
import com.learning.employeeservice.dtos.EmployeeResponse;

@Service
public interface EmployeeService {
	
	EmployeeResponse save(EmployeeResponse employeeResponse);
	
	APIResponse getEmployee(Long id);
	
}
