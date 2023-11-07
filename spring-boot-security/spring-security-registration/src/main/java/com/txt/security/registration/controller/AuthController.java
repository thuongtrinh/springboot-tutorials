package com.txt.security.registration.controller;

import com.txt.security.registration.common.component.ApiController;
import com.txt.security.registration.dto.common.APIResponseDTO;
import com.txt.security.registration.service.GroupService;
import com.txt.security.registration.util.ApiUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Auth Controller")
@RequestMapping("api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController extends ApiController {

    private final GroupService groupService;

    @GetMapping(value = "roles", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Roles by username")
    public ResponseEntity<APIResponseDTO<List<String>>> queryTask(@RequestParam(value = "username") String username) {
        List<String> responseData = groupService.getRolesByUserGroups(username);
        APIResponseDTO<List<String>> responseDTO = ApiUtil.status200(serviceName, responseData);
        return ResponseEntity.ok(responseDTO);
    }
}
