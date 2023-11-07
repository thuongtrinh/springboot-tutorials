package com.txt.security.registration.repository;

import com.txt.security.registration.entity.authen.Groups;
import org.springframework.data.repository.CrudRepository;

public interface GroupRespository extends CrudRepository<Groups, Long> {

}