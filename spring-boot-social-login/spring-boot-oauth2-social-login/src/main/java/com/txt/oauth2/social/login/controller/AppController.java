//package com.txt.oauth2.social.login.controller;
//
//import java.io.IOException;
//
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.apache.hc.client5.http.ClientProtocolException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.txt.oauth2.social.login.entity.GooglePojo;
//import com.txt.oauth2.social.login.entity.LinkedInPojo;
//import com.txt.oauth2.social.login.utils.GoogleUtils;
//import com.txt.oauth2.social.login.utils.LinkedInUtils;
//import com.txt.oauth2.social.login.utils.RestFBUtils;
//
//@Controller
//public class AppController {
//
//    @Autowired
//    private GoogleUtils googleUtils;
//
//    @Autowired
//    private RestFBUtils restFBUtils;
//
//    @Autowired
//    private LinkedInUtils linkedInUtils;
//
//    @RequestMapping(value = {"/", "/login"})
//    public String login() {
//        return "login";
//    }
//
//    @RequestMapping("/login-google")
//    public String loginGoogle(HttpServletRequest request) throws ClientProtocolException, IOException {
//        String code = request.getParameter("code");
//
//        if (code == null || code.isEmpty()) {
//            return "redirect:/login?google=error";
//        }
//
//        String accessToken = googleUtils.getToken(code);
//        GooglePojo googlePojo = googleUtils.getUserInfo(accessToken);
//        UserDetails userDetail = googleUtils.buildUser(googlePojo);
//
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
//                userDetail.getAuthorities());
//        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        return "redirect:/user";
//    }
//
//    @RequestMapping("/login-facebook")
//    public String loginFacebook(HttpServletRequest request) throws ClientProtocolException, IOException {
//        String code = request.getParameter("code");
//
//        if (code == null || code.isEmpty()) {
//            return "redirect:/login?facebook=error";
//        }
//
//        String accessToken = restFBUtils.getToken(code);
////        com.restfb.types.User user = restFBUtils.getUserInfo(accessToken);
////        UserDetails userDetail = restFBUtils.buildUser(user);
//
////        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
////                userDetail.getAuthorities());
////        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        return "redirect:/user";
//    }
//
//    @RequestMapping("/login-linkedin")
//    public String loginLinkedIn(HttpServletRequest request) throws ClientProtocolException, IOException {
//        String code = request.getParameter("code");
//        if (code == null || code.isEmpty()) {
//            return "redirect:/login?message=linkedin_error";
//        }
//
//        String accessToken = linkedInUtils.getToken(code);
//        LinkedInPojo user = linkedInUtils.getUserInfo(accessToken);
//        UserDetails userDetail = linkedInUtils.buildUser(user);
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
//                userDetail.getAuthorities());
//        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return "redirect:/user";
//    }
//
//    @RequestMapping("/user")
//    public String user() {
//        return "user";
//    }
//
//    @RequestMapping("/admin")
//    public String admin() {
//        return "admin";
//    }
//
//    @RequestMapping("/403")
//    public String accessDenied() {
//        return "403";
//    }
//}
