package com.learning.employeeservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learning.employeeservice.dtos.DepartmentResponse;


@FeignClient(name="DEPARMENT-SERVICE")
public interface APIClient {

	@GetMapping("api/department/{code}")
	DepartmentResponse getByDepartmentCode(@PathVariable("code") String code);
	
}
