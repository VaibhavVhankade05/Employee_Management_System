package com.system.management.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.management.employee.entity.Employee;
import com.system.management.employee.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController 
{
	@Autowired
	private EmployeeService service;
	
	 @GetMapping
	    public List<Employee> getAllEmployees() {
	        return service.getAllEmployees();
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
	        return service.getEmployeeById(id)
	                .map(ResponseEntity::ok)
	                .orElse(ResponseEntity.notFound().build());
	    }

	    @PostMapping
	    public Employee createEmployee(@RequestBody Employee employee) {
	        return service.addEmployee(employee);
	    }
	    
	    @PostMapping("/bulk")
	    public ResponseEntity<List<Employee>> createMultipleEmployees(@RequestBody List<Employee> employees) {
	        List<Employee> createdEmployees = service.addMultipleEmployees(employees);
	        return new ResponseEntity<>(createdEmployees, HttpStatus.CREATED);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
	        return ResponseEntity.ok(service.updateEmployee(id, employeeDetails));
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
	        service.deleteEmployee(id);
	        return ResponseEntity.noContent().build();
	    }

}
