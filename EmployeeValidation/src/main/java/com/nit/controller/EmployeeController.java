package com.nit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.nit.Repository.EmployeeRepository;
import com.nit.entity.Employee;
import com.nit.exception.EmployeeNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
      throws EmployeeNotFoundException {
       Employee employee = employeeRepository.findById(employeeId)
          .orElseThrow(() -> new EmployeeNotFoundException("Employee not found for this id :: " + employeeId));
      double salary= employee.getSalary();
      double tax=0;
      if(salary<=250000) 
    	 tax=0; 
      else if(salary<=500000 ) 
    	  tax=0.05*(salary-250000);
      else if(salary <=1000000) 
    	  tax=0.1*(salary-500000)+(0.02*250000);
      else if (salary>1000000)
    	  tax=0.2*(salary-1000000);
      else
    	  tax=0.3*(salary-1000000);
      System.out.println(tax);
      
        return ResponseEntity.ok().body(employee);
    }
}
