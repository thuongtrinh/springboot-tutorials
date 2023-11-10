package com.txt.security.registration.service;

import com.txt.security.registration.dto.RegistrationRequest;
import com.txt.security.registration.dto.authen.UserDTO;

import java.util.List;

public interface UserService {

    List<String> getUserRolesByEmail(String email);

    List<String> getUserRolesByUsername(String username);

    UserDTO getUserByEmail(String email);

    UserDTO getUserByUsername(String username);

    boolean registration(RegistrationRequest registrationRequest);
}
