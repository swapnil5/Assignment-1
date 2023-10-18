package com.learning.employeeservice.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.learning.employeeservice.dtos.APIResponse;
import com.learning.employeeservice.dtos.DepartmentResponse;
import com.learning.employeeservice.dtos.EmployeeResponse;
import com.learning.employeeservice.dtos.OrganizationResponse;
import com.learning.employeeservice.model.Employee;
import com.learning.employeeservice.repository.EmployeeRepository;
import com.learning.employeeservice.service.EmployeeService;

import io.github.resilience4j.retry.annotation.Retry;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	

	@Autowired 
	private WebClient webClient;
	 
	
	//@Autowired
	//private APIClient apiClient;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public EmployeeResponse save(EmployeeResponse employeeResponse) {
		return modelMapper.map(
				employeeRepository.save(modelMapper.map(employeeResponse, Employee.class))
				, EmployeeResponse.class);
	}

	@Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
	//@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
	@Override
	public APIResponse getEmployee(Long id) {
		Employee employee = employeeRepository.findById(id).get();
		
		
		DepartmentResponse departmentResponse = webClient.get().uri("/api/department/" + employee.getDepartmentCode())
				.retrieve().bodyToMono(DepartmentResponse.class).block();
		
		OrganizationResponse organizationResponse = webClient.get().uri("/api/organization/" + employee.getOrganizationCode())
															 .retrieve().bodyToMono(OrganizationResponse.class).block();
		
		APIResponse apiResponse = new APIResponse();
		//apiResponse.setDepartmentResponse(apiClient.getByDepartmentCode( employee.getDepartmentCode()));
		apiResponse.setDepartmentResponse(departmentResponse);
		apiResponse.setOrganizationResponse(organizationResponse);
		
		apiResponse.setEmployeeResponse(modelMapper.map(employee, EmployeeResponse.class));
		
		return apiResponse;
	}
	
	public APIResponse getDefaultDepartment(Long id, Exception exception) {
		
		Employee employee = employeeRepository.findById(id).get();
		
		
		APIResponse apiResponse = new APIResponse();
		apiResponse.setDepartmentResponse(new DepartmentResponse());
		
		apiResponse.setEmployeeResponse(modelMapper.map(employee, EmployeeResponse.class));
		
		return apiResponse;
	}

}
