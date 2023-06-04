package com.txt.jjwt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.txt.jjwt.dto.UserDTO;
import com.txt.jjwt.entities.User;
import com.txt.jjwt.utils.JWTokenUtils;
import com.txt.jjwt.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Json Wen Token JJWT API", description = "Json Wen Token JJWT API")
@RestController
@RequestMapping(value = "/restjjwt")
public class JwtRestController {

    @Autowired
    private UserService userService;

    @Autowired
    JWTokenUtils jwTokenUtils;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(HttpServletRequest request, HttpServletResponse response, @RequestBody UserDTO user) {
        String result;
        HttpStatus httpStatus;
        try {
            if (userService.checkLogin(user)) {
                result = jwTokenUtils.addAuthentication(response, user.getUsername());
                httpStatus = HttpStatus.OK;
            } else {
                result = "Wrong userId and password";
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception ex) {
            result = "Server Error";
            ex.printStackTrace();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(result, httpStatus);
    }
}
