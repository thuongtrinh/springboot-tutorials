package com.txt.josejwt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.txt.josejwt.dto.UserDTO;
import com.txt.josejwt.entities.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.txt.josejwt.service.JwtService;
import com.txt.josejwt.service.UserService;


@Tag(name = "Json Wen Token Nimbus Jose API", description = "Json Wen Token Nimbus Jose API")
@RestController
@RequestMapping("/rest")
public class UserRestController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUserById(@PathVariable int id) {
        User user = userService.findById(id);
        if (user != null) {
            return new ResponseEntity<Object>(user, HttpStatus.OK);
        }
        return new ResponseEntity<Object>("Not Found User", HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody User user) {
        if (userService.add(user)) {
            return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("User Existed!", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUserById(@PathVariable int id) {
        userService.delete(id);
        return new ResponseEntity<String>("Deleted!", HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(HttpServletRequest request, @RequestBody UserDTO user) {
        String result = "";
        HttpStatus httpStatus = null;
        try {
            if (userService.checkLogin(user)) {
                result = jwtService.generateTokenLogin(user.getUsername());
                httpStatus = HttpStatus.OK;
            } else {
                result = "Wrong userId and password";
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception ex) {
            result = "Server Error";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<String>(result, httpStatus);
    }

}
