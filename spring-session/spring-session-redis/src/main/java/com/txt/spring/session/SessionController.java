package com.txt.spring.session;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@RestController
public class SessionController {
    @RequestMapping("/")
    public String helloAdmin() {
        return "hello admin";
    }

    @RequestMapping("/getSession")
    public String checkSession(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        Enumeration<String> enums = httpSession.getAttributeNames();

        String sesValues = "";
        while (enums.hasMoreElements()){
            sesValues += enums.nextElement();
        }

        return "Session value: " + sesValues;
    }
}
