package com.txt.security.registration.repository;

import com.txt.security.registration.entity.authen.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, String> {

    List<Users> findByEmail(String email);

    List<Users> findByUsername(String username);

}