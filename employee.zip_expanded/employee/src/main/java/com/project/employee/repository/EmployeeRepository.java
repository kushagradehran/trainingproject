package com.project.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.employee.entity.EmployeeData;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeData,Long>{
	
}
