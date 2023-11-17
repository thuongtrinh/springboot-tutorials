package com.txt.security.registration.repository;

import com.txt.security.registration.entity.authen.Users;
import com.txt.security.registration.entity.authen.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.stream.Stream;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(Users user);

    Stream<VerificationToken> findAllByExpirydateLessThan(Date now);

    void deleteByExpirydateLessThan(Date now);

    @Modifying
    @Query("delete from VerificationToken t where t.expirydate <= ?1")
    void deleteAllExpiredSince(Date now);
}
