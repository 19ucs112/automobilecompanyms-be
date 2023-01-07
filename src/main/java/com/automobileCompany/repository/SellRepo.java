package com.automobileCompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.automobileCompany.models.SaleCar;

public interface SellRepo extends JpaRepository<SaleCar,String> 
{
	
	@Query(value="select id from car_sale order by id desc limit 1 ",nativeQuery = true)
	public String findLastEntryid();

}
