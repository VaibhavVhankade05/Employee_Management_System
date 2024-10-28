package com.system.management.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.management.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>
{

}
