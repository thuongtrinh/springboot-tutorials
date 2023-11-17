package com.txt.security.registration.repository;

import com.txt.security.registration.entity.authen.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLocationRepository extends JpaRepository<UserLocation, Long> {

    UserLocation findByCountryAndUsername(String country, String username);

}
