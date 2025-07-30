package com.txt.rest.simple.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.txt.rest.simple.dto.common.ResponseWithBody;
import com.txt.rest.simple.dto.data.DataRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "SimpleController", description = "Simple Controller API")
@Slf4j
@RequiredArgsConstructor
public class SimpleController {

    private final Environment env;
    private final ObjectMapper objectMapper;
    private final HttpServletRequest httpServletRequest;
    private final RestTemplate restTemplate;

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "description api"),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "500", description = "Server error")})
    @Operation(description = "simple test API")
    @PostMapping(path = "/simple-test", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWithBody> apiTest(
            @RequestHeader(value = "Authorization", required = false) String bearerToken,
            @RequestBody DataRequestDTO requestDTO) {

        ResponseWithBody<List<?>> response = new ResponseWithBody<>();
        try {
            String uri = httpServletRequest.getRequestURI();
            String exchangeId = UUID.randomUUID().toString();
            MDC.put("traceId", exchangeId);
            response.setExchangeId(exchangeId);

            response.setResponseStatus(null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("apiTest has error encountered {}", e.getMessage(), e);
            response.setResponseStatus(null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
