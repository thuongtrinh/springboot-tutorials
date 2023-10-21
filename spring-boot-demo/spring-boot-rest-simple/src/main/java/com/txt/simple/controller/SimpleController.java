package com.txt.simple.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.txt.simple.dto.common.ResponseWithBody;
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
@Tag(name = "SimpleController", description = "SimpleController API")
@Slf4j
@RequiredArgsConstructor
public class SimpleController {

    //region INIT
    final Environment env;
    final ObjectMapper objectMapper;
    final HttpServletRequest httpServletRequest;
    final RestTemplate restTemplate;
    //endregion

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Indicates the requested data were returned."),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "500", description = "Wrong params")})
    @Operation(description = "Upload file to azure API")
    @PostMapping(path = "/simple", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWithBody> uploadFileToAzure(
            @RequestHeader(value = "Authorization", required = false) String bearerToken) {

        ResponseWithBody<List<?>> response = new ResponseWithBody<>();

        try {
            String uri = httpServletRequest.getRequestURI();
            String exchangeId = UUID.randomUUID().toString();
            MDC.put("traceId", exchangeId);
            response.setExchangeId(exchangeId);


            response.setResponseStatus(null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Upload file to azure has error encountered {}", e);
            response.setResponseStatus(null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
