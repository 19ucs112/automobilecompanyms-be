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

import com.automobileCompany.exception.ResourceNotFoundException;
//import com.automobileCompany.exception.ResourceNotFoundException;
import com.automobileCompany.models.Customer;
import com.automobileCompany.repository.CustomerRepo;

@CrossOrigin(origins = { "http://localhost:3000/" , "http://192.168.0.9:3000/","http://172.22.57.72:3000/"})
@RestController
@RequestMapping("/api/v2/")
public class CustomerController {
	
	@Autowired
	CustomerRepo customerRepo;
	@PostMapping("/customer_save")
	public String CreateCustomer(@RequestBody Customer c)
	{
		String last_id = customerRepo.findLastEntryid();
		String required_num = last_id.charAt(5)+""+last_id.charAt(6)+""+last_id.charAt(7)+""+last_id.charAt(8);
		int r_id = Integer.parseInt(required_num);
		r_id+=1;
		String required_id = String.format("%04d", r_id);
		required_id = c.getId()+"Ac_C_"+required_id;
		c.setId(required_id);
		customerRepo.save(c);
		return c.getId();
	}
	@GetMapping("/customer_authenticate/{id}/{password}")
	public boolean authenticateCustomer(@PathVariable String id, @PathVariable String password)
	{
		Customer c = customerRepo.authenticateCustomer(id, password);
		if(c != null)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	@GetMapping("/customer/{id}")
	public String getFirstName(@PathVariable String id)
	{
		return customerRepo.findFirstName(id);
	}
	@GetMapping("/customer_get/{id}")
	public boolean getEmployeeid(@PathVariable String id)
	{
		Customer c =  customerRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("customer not found"));
		if(c != null)
			return true;
		else
			return false;
	}
	@GetMapping("/customer_find/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable String id)
	{
		Customer c = customerRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Customer not found"));
		return ResponseEntity.ok(c);
	}
	@PutMapping("/update_mobile/{id}")
	public ResponseEntity<Customer> update_mobile(@PathVariable String id, @RequestBody Customer c)
	{
		Customer x = customerRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("customer not found"));
		x.setMobile(c.getMobile());
		Customer Updated_Customer = customerRepo.save(x);
		return ResponseEntity.ok(Updated_Customer);
	}
	@PutMapping("/change_password/{id}")
	public ResponseEntity<Customer> change_password(@PathVariable String id, @RequestBody Customer c)
	{
		Customer x = customerRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("customer not found"));
		x.setPassword(c.getPassword());
		Customer Updated_Customer = customerRepo.save(x);
		return ResponseEntity.ok(Updated_Customer);
	
	}
	@PutMapping("/update_city/{id}")
	public ResponseEntity<Customer> update_city(@PathVariable String id, @RequestBody Customer c)
	{
		Customer x = customerRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("customer not found"));
		x.setCity(c.getCity());
		Customer Updated_Customer = customerRepo.save(x);
		return ResponseEntity.ok(Updated_Customer);
	}
	
	@DeleteMapping("/delete/{id}")
	public Boolean delete_customer(@PathVariable String id)
	{
		Customer x = customerRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Customer not found"));
		if(x==null)
		{
			return false;
		}
		else
		{
			customerRepo.deleteById(id);
			return true;
		}
	}
}
