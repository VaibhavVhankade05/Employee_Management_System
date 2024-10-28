package com.system.management.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.management.employee.entity.Employee;
import com.system.management.employee.repository.EmployeeRepository;


@Service
public class EmployeeService 
{
	@Autowired
	private EmployeeRepository repository;

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return repository.findById(id);
    }

    public Employee addEmployee(Employee employee) {
        return repository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        return repository.findById(id) 
                .map(employee -> {
                    employee.setName(employeeDetails.getName());
                    employee.setPosition(employeeDetails.getPosition());
                    employee.setDepartment(employeeDetails.getDepartment());
                    employee.setSalary(employeeDetails.getSalary());
                    return repository.save(employee);
                }).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }

	public List<Employee> addMultipleEmployees(List<Employee> employees) {
		return repository.saveAll(employees);
	}
}
