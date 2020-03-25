package com.txt.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.txt.entity.Customer;

@Transactional
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Modifying
	@Query("update Customer u set u.name = ?1, u.address = ?2 where u.id = ?3")
	void setCustomerInfoById(String name, String address, Integer id);
}
