package com.txt.mongoredis.service.impl;

import com.txt.mongoredis.dto.CountryDTO;
import com.txt.mongoredis.dto.common.ResponseDTO;
import com.txt.mongoredis.entities.mongodb.dbfirst.Country;
import com.txt.mongoredis.repositories.mongodb.dbfirst.CountryRepository;
import com.txt.mongoredis.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
//    @Cacheable(value = "countries")
    public ResponseDTO findAll() {
        List<Country> queryResult = countryRepository.findAllByOrderByName();
        List<CountryDTO> countryDTOList =
                queryResult.stream()
                        .map(new Function<Country, CountryDTO>() {
                            @Override
                            public CountryDTO apply(Country country) {
                                CountryDTO countryDTO = new CountryDTO();
                                BeanUtils.copyProperties(country, countryDTO);
                                return countryDTO;
                            }
                        })
                        .collect(Collectors.toList());
        ResponseDTO<CountryDTO> response = new ResponseDTO<>();
        response.setData(countryDTOList);
        return response;
    }

}
