package com.txt.security.registration.controller;

import com.txt.security.registration.common.component.ApiController;
import com.txt.security.registration.dto.RegistrationRequest;
import com.txt.security.registration.dto.common.APIResponseDTO;
import com.txt.security.registration.service.GroupService;
import com.txt.security.registration.service.UserService;
import com.txt.security.registration.util.ApiUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Auth Controller")
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController extends ApiController {

    private final GroupService groupService;
    private final UserService userService;

    @GetMapping(value = "roles", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Roles by username")
    public ResponseEntity<APIResponseDTO<List<String>>> queryTask(@RequestParam(value = "username") String username) {
        List<String> responseData = groupService.getRolesByUserGroups(username);
        APIResponseDTO<List<String>> responseDTO = ApiUtil.status200(serviceName, responseData);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "registration", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Registration account")
    public ResponseEntity<APIResponseDTO<?>> registrationAccount(@RequestParam @Valid RegistrationRequest registrationRequest) {
        userService.registration(registrationRequest);
        userService.addUserLocation(registered, getClientIP(request));
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));

        APIResponseDTO<?> responseDTO = ApiUtil.status200(serviceName, true);
        return ResponseEntity.ok(responseDTO);
    }
}
