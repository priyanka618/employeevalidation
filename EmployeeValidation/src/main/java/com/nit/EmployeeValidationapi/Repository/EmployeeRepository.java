package com.nit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nit.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
