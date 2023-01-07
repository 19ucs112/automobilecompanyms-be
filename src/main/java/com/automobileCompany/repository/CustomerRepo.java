package com.automobileCompany.repository;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.automobileCompany.models.Customer;

public interface CustomerRepo  extends JpaRepository<Customer, String> 
{
	@Query(value="select id from customers order by id desc limit 1 ",nativeQuery = true)
	public String findLastEntryid();
	
	@Query(value="select * from customers  where id=:i and password=:p",nativeQuery = true)
	public Customer authenticateCustomer(@Param("i")String id,@Param("p") String password);
	
	@Query(value="select first_name from customers where id=:i", nativeQuery = true)
	public String findFirstName(@Param("i") String id);
	
	@Transactional
	@Modifying
	@Query(value="update customers set mobile =:m where id =:i", nativeQuery = true)
	public void update_mobile(@Param("i") String id, @Param("m") String mobile);
}
