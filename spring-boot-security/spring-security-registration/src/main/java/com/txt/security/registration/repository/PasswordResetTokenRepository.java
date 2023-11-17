package com.txt.security.registration.repository;

import com.txt.security.registration.entity.authen.PasswordResetToken;
import com.txt.security.registration.entity.authen.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.stream.Stream;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);

    PasswordResetToken findByUser(Users user);

    Stream<PasswordResetToken> findAllByExpirydateLessThan(Date now);

    void deleteByExpirydateLessThan(Date now);

    @Modifying
    @Transactional
    @Query("delete from PasswordResetToken t where t.expirydate <= ?1")
    void deleteAllExpiredSince(Date now);
}
