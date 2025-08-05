package com.txt.rest.simple.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.txt.rest.simple.constant.Constants;
import com.txt.rest.simple.dto.common.APIStandardRequestDTO;
import com.txt.rest.simple.dto.common.APIStandardResponseDTO;
import com.txt.rest.simple.dto.data.DataRequestDTO;
import com.txt.rest.simple.utils.APIUtils;
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

@RestController
@Tag(name = "Simple Controller", description = "Simple API")
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
    public ResponseEntity<APIStandardResponseDTO> apiTest(
            @RequestHeader(value = "Authorization", required = false) String bearerToken,
            @RequestBody APIStandardRequestDTO<DataRequestDTO> requestDTO) {

        APIStandardResponseDTO<?> response = new APIStandardResponseDTO<>();
        String exchangeId = APIUtils.getExchangeId(requestDTO.getExchangeId());
        response.setExchangeId(exchangeId);
        try {
            String uri = httpServletRequest.getRequestURI();
            MDC.put(Constants.TRACK_ID, exchangeId);
            log.info("URI: {}", httpServletRequest.getRequestURI());

            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("apiTest has error encountered {}", e.getMessage(), e);
            return new ResponseEntity<>(APIUtils.statusError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
