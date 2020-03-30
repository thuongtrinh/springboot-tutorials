package com.txt.repository;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.txt.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Modifying
	@Query("update Customer u set u.name = ?1, u.address = ?2 where u.id = ?3")
	void setCustomerInfoById(String name, String address, Integer id);

	@Query(value = "SELECT e.* FROM customer e ORDER BY e.name DESC", nativeQuery = true)
	List<Customer> findAllOrderByNameDescNative();

	@Query("SELECT e FROM Customer e WHERE e.name = ?1")
	List<Customer> findByName(String name);

	@Query("SELECT e FROM Customer e WHERE e.name = :name AND e.address = :address")
	List<Customer> findByNameAndAddress(@Param("name") String name, @Param("address") String address);

	@Query("SELECT e FROM Customer e WHERE e.name like ?1")
	List<Customer> findByNameLike(String name);

//	@Query("SELECT e FROM Customer e WHERE e.name = :name")
//	Customer findUniqueByName(@Param("name") String name); // when you sure result of return is unique object 

	@Query("SELECT e FROM Customer e WHERE e.name = :name")
	Stream<Customer> findByNameStream(@Param("name") String name);

	// Spring Data Query Creation
	List<Customer> findAllByOrderByNameDesc();

	List<Customer> findByIdIn(List<Integer> ids);
	
	List<Customer> findByIdLessThan(int index);

	//List<Customer> findByName(String name);

	//List<Customer> findByNameAndAddress(String name, String address);

	//List<Customer> findByNameLike(String name);

	//Paging
	@Query("SELECT e FROM Customer e")
	Page<Customer> findCustomers(Pageable pageable);
}
