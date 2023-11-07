package com.txt.security.registration.repository;

import com.txt.security.registration.entity.authen.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRespository extends CrudRepository<Users, Long> {

    List<Users> findByEmail(String email);

    List<Users> findByUsername(String username);

}