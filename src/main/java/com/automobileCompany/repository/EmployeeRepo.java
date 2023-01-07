package com.automobileCompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.automobileCompany.models.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, String> {
	@Query(value="select id from employees order by id desc limit 1", nativeQuery=true)
	public String findLastId();
}
