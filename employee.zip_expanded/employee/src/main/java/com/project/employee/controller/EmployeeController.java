package com.project.employee.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.employee.entity.EmployeeData;
import com.project.employee.repository.EmployeeRepository;
@RestController
@RequestMapping("/api")
public class EmployeeController {
@Autowired
private EmployeeRepository employeeRepository;
@Cacheable(cacheNames = "employees")
@GetMapping("/employees")
public List<EmployeeData> getAllEmployees()
{
	return employeeRepository.findAll();
}
@PostMapping("/employees")
public EmployeeData createEmployee(@RequestBody EmployeeData employee)
{
	return employeeRepository.save(employee);
}
@Cacheable(cacheNames = "employees",key="#id")
@GetMapping("/employees/{id}")
public ResponseEntity<EmployeeData> getEmployeeById(@PathVariable long id)
{
	System.out.println("getting data from database");
	EmployeeData employee=employeeRepository.findById(id).orElseThrow();
	return ResponseEntity.ok(employee);
}
@CachePut(cacheNames = "employees", key="#id")
@PutMapping("/employees/{id}")
public ResponseEntity<EmployeeData> updateEmployee(@ PathVariable Long id,@RequestBody EmployeeData employeeDetails)
{
	
	EmployeeData employee=employeeRepository.findById(id).orElseThrow();
	employee.setSalary(employeeDetails.getSalary());
	EmployeeData updatedEmployee = employeeRepository.save(employee);
	System.out.println("update salary");
	return ResponseEntity.ok(employee);
}
@CacheEvict(cacheNames = "employees", key="#id")
@DeleteMapping("/employees/{id}")
public void deleteEmployee(@PathVariable Long id,@RequestBody EmployeeData employeeDetails)
{
	System.out.println("deleted");
	EmployeeData employee=employeeRepository.findById(id).orElseThrow();
	employeeRepository.delete(employee);
}
}