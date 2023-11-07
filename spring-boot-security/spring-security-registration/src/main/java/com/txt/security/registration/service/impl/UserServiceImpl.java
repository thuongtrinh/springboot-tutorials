package com.txt.security.registration.service.impl;

import com.txt.security.registration.dto.authen.UserDTO;
import com.txt.security.registration.entity.authen.Users;
import com.txt.security.registration.repository.UserRespository;
import com.txt.security.registration.service.GroupService;
import com.txt.security.registration.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final GroupService groupService;
    private final UserRespository userRespository;

    @Override
    public UserDTO getUserByEmail(String email) {
        List<UserDTO> users = getListUserByEmail(email);
        return CollectionUtils.isEmpty(users) ? null : users.get(0);
    }

    @Override
    public List<String> getUserRolesByEmail(String email) {
        UserDTO userDTO = getUserByEmail(email);
        if (!ObjectUtils.isEmpty(userDTO)) {
            List<String> roles = groupService.getRolesByUserGroups(userDTO.getUsername());
            return roles;
        }
        return null;
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        List<UserDTO> users = getListUserByUsername(username);
        return CollectionUtils.isEmpty(users) ? null : users.get(0);
    }

    @Override
    public List<String> getUserRolesByUsername(String username) {
        UserDTO userDTO = getUserByUsername(username);
        if (!ObjectUtils.isEmpty(userDTO)) {
            List<String> roles = groupService.getRolesByUserGroups(userDTO.getUsername());
            return roles;
        }
        return null;
    }

    private List<UserDTO> getListUserByEmail(String email) {
        List<Users> listUser = userRespository.findByEmail(email);
        if (!ObjectUtils.isEmpty(listUser)) {
            return listUser.stream().map(user -> UserDTO.builder()
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .build()).toList();
        }
        return null;
    }

    private List<UserDTO> getListUserByUsername(String username) {
        List<Users> listUser = userRespository.findByUsername(username);
        if (!ObjectUtils.isEmpty(listUser)) {
            return listUser.stream().map(user -> UserDTO.builder()
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .build())
                    .toList();
        }
        return null;
    }
}
