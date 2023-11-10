package com.txt.security.registration.service.impl;

import com.txt.security.registration.common.RoleConstant;
import com.txt.security.registration.dto.RegistrationRequest;
import com.txt.security.registration.dto.authen.UserDTO;
import com.txt.security.registration.entity.authen.GroupMembers;
import com.txt.security.registration.entity.authen.Groups;
import com.txt.security.registration.entity.authen.Users;
import com.txt.security.registration.exception.UserAlreadyExistException;
import com.txt.security.registration.repository.GroupMembersRespository;
import com.txt.security.registration.repository.GroupRespository;
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
    private final GroupMembersRespository groupMembersRespository;
    private final GroupRespository groupRespository;

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

    //------------------------------------
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
    //------------------------------------

    @Override
    public boolean registration(RegistrationRequest registrationRequest) {
        if (emailExists(registrationRequest.getEmail()) || usernameExists(registrationRequest.getEmail())) {
            throw new UserAlreadyExistException("There is an account with this address: " + registrationRequest.getEmail());
        }

        Users user = new Users();
        user.setFirstname(registrationRequest.getFirstname());
        user.setLastname(registrationRequest.getLastname());
        user.setPassword(registrationRequest.getPassword());
        user.setEmail(registrationRequest.getEmail());
        user.setUsing2FA(registrationRequest.isUsing2FA());
        Users userResponse = userRespository.save(user);

        //Role default
        Groups group = groupRespository.findFirstByGroupName(RoleConstant.SYS_USER.name);
        groupMembersRespository.save(GroupMembers.builder().
                username(userResponse.getUsername())
                .groupId(group.getId())
                .build());
        return true;
    }

    private boolean usernameExists(String username) {
        return userRespository.findByUsername(username) != null;
    }

    private boolean emailExists(final String email) {
        return userRespository.findByEmail(email) != null;
    }

}
