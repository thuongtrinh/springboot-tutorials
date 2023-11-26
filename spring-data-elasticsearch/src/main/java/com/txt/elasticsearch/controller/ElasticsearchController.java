package com.txt.elasticsearch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.txt.elasticsearch.dto.RequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@Tag(name = "Elasticsearch API", description = "Elasticsearch API")
@Slf4j
@RequiredArgsConstructor
public class ElasticsearchController {

    final Environment env;
    final ObjectMapper objectMapper;
    final HttpServletRequest httpServletRequest;


    @PostMapping(path = "/elasticearch/test", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Create Submission")
    public ResponseEntity<?> submissionOnlinePayment(
            @RequestBody RequestDTO responseDto,
            @RequestHeader(value = "Authorization", required = false) String bearerToken) {

        String uri = httpServletRequest.getRequestURI();
        String exchangeId = UUID.randomUUID().toString();
        try {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            log.error("has been JsonProcessingException: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
