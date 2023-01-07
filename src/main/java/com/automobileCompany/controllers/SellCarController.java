package com.automobileCompany.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.automobileCompany.models.SaleCar;
import com.automobileCompany.repository.SellRepo;

@CrossOrigin(origins = { "http://localhost:3000/" , "http://192.168.0.9:3000/"})
@RestController
@RequestMapping("/api/v4/")
public class SellCarController {
	
	@Autowired
	private SellRepo sellRepo;
	
	@PostMapping("/sellCar/")
	public String savecar(@RequestBody SaleCar c)
	{
		String lastId = sellRepo.findLastEntryid();
		if(lastId == null)
		{
			c.setId("Ac_Sc_0001");
			sellRepo.save(c);
			return c.getId();
		}
		else
		{
			
			String required_num = lastId.charAt(6)+""+lastId.charAt(7)+""+lastId.charAt(8)+""+lastId.charAt(9);
			int r_id = Integer.parseInt(required_num);
			r_id+=1;
			String required_id = String.format("%04d", r_id);
			required_id = c.getId()+"Ac_Sc_"+required_id;
			c.setId(required_id);
			sellRepo.save(c);
			return c.getId();
		}
	}

}
