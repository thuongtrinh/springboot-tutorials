package com.txt.security.registration.service;

import com.txt.security.registration.entity.authen.Users;
import com.txt.security.registration.entity.authen.VerificationToken;

import java.util.Optional;

public interface TokenVerifyService {

    VerificationToken createVerificationTokenForUser(Users user, String token);

    VerificationToken validateVerificationToken(String token);

    void createPasswordResetTokenForUser(Users user, String token);

    boolean validatePasswordResetToken(String token);

    Optional<Users> getUserByPasswordResetToken(String token);

    void changeUserPassword(Users users, String newPassword);

    boolean checkIfValidOldPassword(Users user, String oldPassword);

    VerificationToken generateNewVerificationToken(String token, String existingToken);

    Users getUserFromVerificationToken(String token);
}
