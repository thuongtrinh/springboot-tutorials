package com.txt.security.registration.controller;

import com.txt.security.registration.common.ResultDTO;
import com.txt.security.registration.common.component.ApiController;
import com.txt.security.registration.dto.LoginRequest;
import com.txt.security.registration.dto.LoginResponse;
import com.txt.security.registration.dto.PasswordRequest;
import com.txt.security.registration.dto.RegistrationRequest;
import com.txt.security.registration.dto.common.APIResponseDTO;
import com.txt.security.registration.dto.common.ResponseCode;
import com.txt.security.registration.entity.authen.Users;
import com.txt.security.registration.entity.authen.VerificationToken;
import com.txt.security.registration.listener.OnRegistrationCompleteEvent;
import com.txt.security.registration.service.EmailService;
import com.txt.security.registration.service.GroupService;
import com.txt.security.registration.service.TokenVerifyService;
import com.txt.security.registration.service.UserService;
import com.txt.security.registration.util.ApiUtil;
import com.txt.security.registration.util.CommonUtils;
import com.txt.security.registration.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

@RestController
@Tag(name = "Auth Controller")
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController extends ApiController {

    private final MessageSource messages;
    private final GroupService groupService;
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;
    private final EmailService emailService;
    private final TokenVerifyService tokenVerifyService;

    @GetMapping(value = "roles", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Roles by username")
    public ResponseEntity<APIResponseDTO<List<String>>> queryTask(@RequestParam(value = "username") String username) {
        List<String> responseData = groupService.getRolesByUserGroups(username);
        APIResponseDTO<List<String>> responseDTO = ApiUtil.status200(serviceName, responseData);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Login account")
    public ResponseEntity<APIResponseDTO<?>> loginAccount(@RequestBody @Valid final LoginRequest loginRequest, final HttpServletRequest request) {
        APIResponseDTO<LoginResponse> responseDTO;
        try {
            LoginResponse loginResponse = userService.login(loginRequest);
            responseDTO = ApiUtil.status200(serviceName, loginResponse);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            log.error("ERROR while registrationAccount with message: {}", e.getLocalizedMessage());
            e.printStackTrace();
            responseDTO = ApiUtil.status500(serviceName, e.getMessage());
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "registration", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Registration account")
    @Transactional
    public ResponseEntity<APIResponseDTO<?>> registrationAccount(@RequestBody @Valid final RegistrationRequest registrationRequest,
                                                                 final HttpServletRequest request) {
        APIResponseDTO<?> responseDTO;
        try {
            Users user = userService.registration(registrationRequest);
            userService.addUserLocation(user, CommonUtils.getClientIP(request));
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), CommonUtils.getAppUrl(request)));

            responseDTO = ApiUtil.status200(serviceName, true);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            log.error("ERROR while registrationAccount with message: {}", e.getLocalizedMessage());
            e.printStackTrace();
            responseDTO = ApiUtil.status500(serviceName, e.getMessage());
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/registration-confirm")
    public ResponseEntity<APIResponseDTO<?>> confirmRegistration(WebRequest request, @RequestParam("token") String token) {
        APIResponseDTO<?> responseDTO;
        try {
            Locale locale = request.getLocale();
            System.out.println(locale);

            ResultDTO<VerificationToken> result = tokenVerifyService.validateVerificationToken(token);
            if (ObjectUtils.isEmpty(result.getBody()) && ObjectUtils.isEmpty(result.getStatus())) {
                responseDTO = ApiUtil.status500(serviceName, messages.getMessage("token.message.invalid", null, locale));
                return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            } else if (ObjectUtils.isNotEmpty(result.getStatus()) && ResponseCode.MS001.getCode().equals(result.getStatus().getCode())) {
                responseDTO = ApiUtil.status(serviceName, ResponseCode.MS001);
                return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            Users user = result.getBody().getUser();
            log.info("registration-confirm success user {}", user);
            responseDTO = ApiUtil.status200(serviceName, true);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            log.error("ERROR while confirmRegistration with message: {}", e.getLocalizedMessage());
            e.printStackTrace();
            responseDTO = ApiUtil.status500(serviceName, e.getMessage());
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<APIResponseDTO<?>> resetPassword(final HttpServletRequest request, @RequestParam("email") final String userEmail) {
        APIResponseDTO<?> responseDTO;
        try {
            final Users user = userService.findUserByEmail(userEmail);
            if (ObjectUtils.isEmpty(user) || !user.getEnabled()) {
                responseDTO = ApiUtil.status500(serviceName, messages.getMessage("user.message.invalid", null, request.getLocale()));
                return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            final String token = UUID.randomUUID().toString();
            tokenVerifyService.createPasswordResetTokenForUser(user, token);
            emailService.sendMailResetPassword(request, user, token);
            responseDTO = ApiUtil.status200(serviceName, true);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            log.error("ERROR while resetPassword with message: {}", e.getLocalizedMessage());
            e.printStackTrace();
            responseDTO = ApiUtil.status500(serviceName, e.getMessage());
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save-password")
    @Operation(summary = "Save password")
    public ResponseEntity<APIResponseDTO<?>> savePassword(final Locale locale, @RequestBody @Valid PasswordRequest passwordRequest) {
        APIResponseDTO<?> responseDTO;
        try {
            if (!tokenVerifyService.validatePasswordResetToken(passwordRequest.getToken())) {
                responseDTO = ApiUtil.status500(serviceName, messages.getMessage("token.message.invalid", null, locale));
                return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            Optional<Users> user = tokenVerifyService.getUserByPasswordResetToken(passwordRequest.getToken());
            if (user.isEmpty()) {
                responseDTO = ApiUtil.status500(serviceName, messages.getMessage("auth.message.invalidUser", null, locale));
                return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            tokenVerifyService.changeUserPassword(user.get(), passwordRequest.getPassword());
            responseDTO = ApiUtil.status200(serviceName, true);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            log.error("ERROR while savePassword with message: {}", e.getLocalizedMessage());
            e.printStackTrace();
            responseDTO = ApiUtil.status500(serviceName, messages.getMessage("message.savePassword", null, locale));
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update-password")
    public ResponseEntity<APIResponseDTO<?>> updatePassword(final Locale locale, @RequestBody @Valid PasswordRequest passwordDto,
                                                            @RequestHeader(value = "Authorization", required = false) String bearerToken) {
        APIResponseDTO<?> responseDTO;
        try {
            String username = JwtUtil.getEmail(bearerToken);
            if(StringUtils.isBlank(username)) {
                responseDTO = ApiUtil.status500(serviceName, "Token error");
                return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            final Users user = userService.findUserByUsername(username);
            if (!tokenVerifyService.checkIfValidOldPassword(user, passwordDto.getOldPassword())) {
                responseDTO = ApiUtil.status500(serviceName, "InvalidOldPasswordException");
                return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            tokenVerifyService.changeUserPassword(user, passwordDto.getPassword());
            responseDTO = ApiUtil.status200(serviceName, true);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            log.error("ERROR while updatePassword with message: {}", e.getLocalizedMessage());
            e.printStackTrace();
            responseDTO = ApiUtil.status500(serviceName, e.getMessage());
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/resend-registration-token")
    public ResponseEntity<APIResponseDTO<?>> resendRegistrationToken(final HttpServletRequest request,
                                                                     @RequestParam("token") final String existingToken) {
        APIResponseDTO<?> responseDTO;
        try {
            final VerificationToken newToken = tokenVerifyService.generateNewVerificationToken(existingToken);
            if (ObjectUtils.isEmpty(newToken)) {
                responseDTO = ApiUtil.status500(serviceName, messages.getMessage("token.message.invalid", null, request.getLocale()));
                return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            final Users user = tokenVerifyService.getUserFromVerificationToken(newToken.getToken());

            emailService.sendMailResendRegistrationToken(request, user, newToken.getToken());
            responseDTO = ApiUtil.status200(serviceName, true);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            log.error("ERROR while resendRegistrationToken with message: {}", e.getLocalizedMessage());
            e.printStackTrace();
            responseDTO = ApiUtil.status500(serviceName, e.getMessage());
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
