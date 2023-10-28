package com.txt.mongoredis.controller.api;

import com.txt.mongoredis.dto.CountryDTO;
import com.txt.mongoredis.dto.common.ResponseDTO;
import com.txt.mongoredis.service.CountryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping("/api/country")
@Tag(name = "Country Controller", description = "Country")
public class CountryController  {

    @Autowired
    private  CountryService countryService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<ResponseDTO<CountryDTO>> findAll() {
        ResponseDTO<CountryDTO> responseDTO = countryService.findAll();
        return ResponseEntity.ok(responseDTO);
    }

}