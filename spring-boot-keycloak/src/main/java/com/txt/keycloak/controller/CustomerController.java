package com.txt.keycloak.controller;

import com.txt.keycloak.entities.Customer;
import com.txt.keycloak.entities.User;
import com.txt.keycloak.repositories.CustomerRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@Tag(name = "JWT authentication with Keycloak API", description = "JWT authentication with Keycloak API")
@RestController
@RequestMapping("api")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ResponseEntity<?> customers() {
        addCustomers();
        Iterable<Customer> customers = customerRepository.findAll();
        return new ResponseEntity<Iterable>(customers, HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<?> getUserInfo() {
        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext()
                .getAuthentication();

        final Principal principal = (Principal) authentication.getPrincipal();
        String email = "";

        if (principal instanceof KeycloakPrincipal) {
            KeycloakPrincipal<KeycloakSecurityContext> kPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
            KeycloakSecurityContext context = kPrincipal.getKeycloakSecurityContext();
            email = context.getToken().getEmail();
        }

        User user = new User();
        user.setUsername(principal.getName());
        user.setEmail(email);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // add customers for demonstration
    public void addCustomers() {
        Customer customer1 = new Customer();
        customer1.setAddress("HCM street");
        customer1.setName("Foo Industries");
        customer1.setServiceRendered("Important services 001");
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setAddress("DN street");
        customer2.setName("Bar LLP");
        customer2.setServiceRendered("Important services 002");
        customerRepository.save(customer2);

        Customer customer3 = new Customer();
        customer3.setAddress("HN street");
        customer3.setName("Big LLC");
        customer3.setServiceRendered("Important services 003");
        customerRepository.save(customer3);
    }
}
