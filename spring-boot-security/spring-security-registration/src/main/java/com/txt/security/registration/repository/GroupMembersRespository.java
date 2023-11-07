package com.txt.security.registration.repository;

import com.txt.security.registration.entity.authen.GroupMembers;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupMembersRespository extends CrudRepository<GroupMembers, Long> {

    List<GroupMembers> findAllByUsername(String username);

}