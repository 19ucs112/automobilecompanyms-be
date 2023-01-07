package com.automobileCompany.controllers;

import org.springframework.beans.factory.annotation.Autowired;

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

import com.automobileCompany.repository.EmployeeRepo;
import com.automobileCompany.exception.ResourceNotFoundException;
import com.automobileCompany.models.Employee;

import java.util.*;

@CrossOrigin(origins = { "http://localhost:3000/" , "http://192.168.0.9:3000/"})
@RestController
@RequestMapping("/api/v1/")
public class employeeController {
	
	@Autowired
	private EmployeeRepo EmployeeRepo;
	
	@GetMapping("/Employees")
	public List<Employee> getEmployee(){
		return EmployeeRepo.findAll();
	}
	
	@PostMapping("/employees_save")
	public Employee CreateEmployee(@RequestBody Employee e)
	{
		String last_id = EmployeeRepo.findLastId();
		String required_num = last_id.charAt(5)+""+last_id.charAt(6)+""+last_id.charAt(7)+""+last_id.charAt(8);
		int r_id = Integer.parseInt(required_num);
		r_id+=1;
		String required_id = String.format("%04d", r_id);
		required_id = e.getId()+"Ac_E_"+required_id;
		e.setId(required_id);
		return EmployeeRepo.save(e);
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity< Employee> getemployeebyId(@PathVariable String id) throws ResourceNotFoundException
	{
		Employee employee = EmployeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee do not exst"));
		return ResponseEntity.ok(employee);
	}
	
	@PutMapping("/employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable String id,@RequestBody Employee employeedetails)
	{
		Employee employee = EmployeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee do not exst"));
		employee.setFirstName(employeedetails.getFirstName());
		employee.setLastName(employeedetails.getLastName());
		employee.setEmailId(employeedetails.getEmailId());
		employee.setCity(employeedetails.getCity());
		employee.setSalary(employeedetails.getSalary());
		employee.setMobile(employeedetails.getMobile());
		Employee updatedEmployee = EmployeeRepo.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	@DeleteMapping("/employee/{id}")
	public ResponseEntity< Map<String, Boolean>> deleteEmployee(@PathVariable String id)
	{
		EmployeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee do not exst"));
	
		EmployeeRepo.deleteById(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
