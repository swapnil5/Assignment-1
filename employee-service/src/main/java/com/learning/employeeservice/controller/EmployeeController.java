package com.learning.employeeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.employeeservice.dtos.APIResponse;
import com.learning.employeeservice.dtos.EmployeeResponse;
import com.learning.employeeservice.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping
	public ResponseEntity<EmployeeResponse> save(@RequestBody EmployeeResponse employeeResponse) {
		
		return new ResponseEntity<>(employeeService.save(employeeResponse), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse> getEmployee(@PathVariable Long id) {
		
		return ResponseEntity.ok(employeeService.getEmployee(id));
	}
}
