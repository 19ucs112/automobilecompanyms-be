package com.automobileCompany.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.automobileCompany.exception.ResourceNotFoundException;
import com.automobileCompany.models.RegisteredCar;
import com.automobileCompany.repository.RegisteredCarRepo;

@RestController
@CrossOrigin(origins = { "http://localhost:3000/" , "http://192.168.0.9:3000/"})
@RequestMapping("/api/v3/")
public class RegisteredCarController {

		@Autowired
		RegisteredCarRepo carRepo;
		
		@GetMapping("/getcar/{id}")
		public List<RegisteredCar> getRegisteredCar(@PathVariable String id)
		{
			return carRepo.getRegisteredCar(id);
		}
		
		@PostMapping("/savecar")
		public String saveCar(@RequestBody RegisteredCar rc)
		{
			String lastId = carRepo.findLastid();
			if(lastId == null)
			{
				rc.setId("Ac_Rc_0001");
				carRepo.save(rc);
				return rc.getId();
			}
			else
			{
				String required_num = lastId.charAt(6)+""+lastId.charAt(7)+""+lastId.charAt(8)+""+lastId.charAt(9);
				int r_id = Integer.parseInt(required_num);
				r_id+=1;
				String required_id = String.format("%04d", r_id);
				required_id = rc.getId()+"Ac_Rc_"+required_id;
				rc.setId(required_id);
				carRepo.save(rc);
				return rc.getId();
			}
		}
		@DeleteMapping("/deletecar/{id}")
		public Boolean DeleteRegisteredCar(@PathVariable String id)
		{
			RegisteredCar r = carRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Car not found"));
			if(r == null)
			{
				return false;
			}
			else
			{
				carRepo.deleteById(id);
				return true;
			}
		}
		@GetMapping("/getcardetails/{id}")
		public ResponseEntity<RegisteredCar> getCarById(@PathVariable String id)
		{
			RegisteredCar rc = carRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("car not found"));
			return ResponseEntity.ok(rc);
		}
		@GetMapping("/getalldetails/{id}")
		public List<RegisteredCar> getallregisteredCar(@PathVariable String id)
		{
			return carRepo.getallRegisteredcar(id);
		}
}
