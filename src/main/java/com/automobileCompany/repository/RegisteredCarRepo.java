package com.automobileCompany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.automobileCompany.models.RegisteredCar;

public interface RegisteredCarRepo extends JpaRepository<RegisteredCar, String> {
	
	
	@Query(value="select * from registeredcars where customer_id=:i LIMIT 2", nativeQuery = true)
	public List<RegisteredCar> getRegisteredCar(@Param("i") String id);
	
	@Query(value="select id from registeredcars order by id desc limit 1 ",nativeQuery = true)
	public String findLastid();
	
	@Query(value = "select * from registeredcars where customer_id = :i", nativeQuery = true)
	public List<RegisteredCar> getallRegisteredcar(@Param("i") String id);
}
