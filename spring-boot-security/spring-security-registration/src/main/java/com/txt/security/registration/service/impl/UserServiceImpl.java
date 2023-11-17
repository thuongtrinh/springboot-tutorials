package com.txt.security.registration.service.impl;

import com.maxmind.geoip2.DatabaseReader;
import com.txt.security.registration.common.RoleConstant;
import com.txt.security.registration.dto.RegistrationRequest;
import com.txt.security.registration.dto.authen.UserDTO;
import com.txt.security.registration.entity.authen.*;
import com.txt.security.registration.exception.UserAlreadyExistException;
import com.txt.security.registration.repository.*;
import com.txt.security.registration.service.GroupService;
import com.txt.security.registration.service.UserService;
import com.txt.security.registration.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.net.InetAddress;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final Environment env;
    private final GroupService groupService;
    private final UserRepository userRepository;
    private final GroupMembersRespository groupMembersRespository;
    private final GroupRespository groupRespository;
    private final UserLocationRepository userLocationRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    @Qualifier("GeoIPCountry")
    private DatabaseReader databaseReader;


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
            /*if(userDTO.getStatus() == false) {
                throw new BusinessRuntimeException("Account inactive!");
            }*/
            List<String> roles = groupService.getRolesByUserGroups(userDTO.getUsername());
            return roles;
        }
        return null;
    }

    //------------------------------------
    private List<UserDTO> getListUserByEmail(String email) {
        List<Users> listUser = userRepository.findByEmail(email);
        if (!ObjectUtils.isEmpty(listUser)) {
            return listUser.stream().map(user -> UserDTO.builder()
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .build()).toList();
        }
        return null;
    }

    private List<UserDTO> getListUserByUsername(String username) {
        List<Users> listUser = userRepository.findByUsername(username);
        if (!ObjectUtils.isEmpty(listUser)) {
            return listUser.stream().map(user -> UserDTO.builder()
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .status(user.getEnabled())
                    .build())
                    .toList();
        }
        return null;
    }
    //------------------------------------

    @Override
    public Users registration(RegistrationRequest request) {
        if (emailExists(request.getEmail()) || usernameExists(request.getUsername())) {
            throw new UserAlreadyExistException("There is an account with this address: " + request.getEmail());
        }

        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setPassword(request.getPassword());
        user.setBirthdate(DateUtil.parseDate(request.getBirthdate(), DateUtil.YYYY_MM_DD));
        user.setEmail(request.getEmail());
        user.setUsing2FA(request.isUsing2FA());
        user.setEnabled(Boolean.FALSE);
        user.setEmailverified(Boolean.FALSE);
        Users userResponse = userRepository.save(user);

        //Role default
        Groups group = groupRespository.findFirstByGroupName(RoleConstant.SYS_USER.code);
        groupMembersRespository.save(GroupMembers.builder().
                username(userResponse.getUsername())
                .groupId(group.getId())
                .build());

        return userResponse;
    }

    @Override
    public Users findUserByEmail(String email) {
        List<Users> listUser = userRepository.findByEmail(email);
        if (!ObjectUtils.isEmpty(listUser)) {
            return listUser.get(0);
        }
        return null;
    }

    public void addUserLocation(Users user, String ip) {
        if (!isGeoIpLibEnabled()) {
            return;
        }

        try {
            // get default address
            final InetAddress ipAddress = InetAddress.getByName(ip);
            final String country = databaseReader.country(ipAddress)
                    .getCountry()
                    .getName();

            System.out.println(country);
            System.out.println(InetAddress.getLocalHost());

            UserLocation loc = new UserLocation();
            loc.setCountry(country);
            loc.setUsername(user.getUsername());
            loc.setEnabled(true);
            userLocationRepository.save(loc);

        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean usernameExists(String username) {
        return !ObjectUtils.isEmpty(userRepository.findByUsername(username));
    }

    private boolean emailExists(final String email) {
        return !ObjectUtils.isEmpty(userRepository.findByEmail(email));
    }

    private boolean isGeoIpLibEnabled() {
        return Boolean.parseBoolean(env.getProperty("geo.ip.lib.enabled"));
    }

}
