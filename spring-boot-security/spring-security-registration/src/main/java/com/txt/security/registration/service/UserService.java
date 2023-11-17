package com.txt.security.registration.service;

import com.txt.security.registration.dto.RegistrationRequest;
import com.txt.security.registration.dto.authen.UserDTO;
import com.txt.security.registration.entity.authen.Users;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<String> getUserRolesByEmail(String email);

    List<String> getUserRolesByUsername(String username);

    UserDTO getUserByEmail(String email);

    UserDTO getUserByUsername(String username);

    Users registration(RegistrationRequest registrationRequest);

    Users findUserByEmail(String email);

    void addUserLocation(Users user, String ip);

}
