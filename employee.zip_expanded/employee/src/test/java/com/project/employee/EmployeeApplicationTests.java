package com.project.employee;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.employee.entity.EmployeeData;
import com.project.employee.repository.EmployeeRepository;



@SpringBootTest
class EmployeeApplicationTests {

	@Autowired
EmployeeRepository employeeRepository;
@Test
public void saveEmployee()
{
	EmployeeData employee =new EmployeeData();
	employee.setName("sachin sirohi");
	employee.setSalary(25000);
	employeeRepository.save(employee);
}
@Test
public void deleteEmployee()
{
	EmployeeData employee = new EmployeeData();
	employeeRepository.deleteById(2L);
}
@Test
public void updateSalary()
{
	EmployeeData employee = employeeRepository.findById(3L).orElseThrow();
	employee.setSalary(27000);
	EmployeeData updatedEmployee = employeeRepository.save(employee);	
}
@Test
public ResponseEntity<EmployeeData> getEmployeeById(@PathVariable long id)
{
	EmployeeData employee=employeeRepository.findById(id).orElseThrow();
	return ResponseEntity.ok(employee);
}
}