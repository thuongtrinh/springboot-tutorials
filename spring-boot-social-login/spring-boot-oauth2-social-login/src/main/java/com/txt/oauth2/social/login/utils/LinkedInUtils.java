//package com.txt.oauth2.social.login.utils;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.fluent.Form;
//import org.apache.http.client.fluent.Request;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.txt.oauth2.social.login.entity.LinkedInPojo;
//
//@Component
//public class LinkedInUtils {
//
//    @Autowired
//    private Environment env;
//
//    public String getToken(final String code) throws ClientProtocolException, IOException {
//        String link = env.getProperty("linkedin.link.get.token");
//        String response = Request.Post(link)
//                .bodyForm(Form.form()
//                        .add("client_id", env.getProperty("linkedin.client.id"))
//                        .add("client_secret", env.getProperty("linkedin.client.secret"))
//                        .add("redirect_uri", env.getProperty("linkedin.redirect.uri"))
//                        .add("code", code)
//                        .add("grant_type", env.getProperty("linkedin.grant_type")).build())
//                .execute().returnContent().asString();
//
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode node = mapper.readTree(response).get("access_token");
//        return node.textValue();
//    }
//
//    public LinkedInPojo getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
//        String link = env.getProperty("linkedin.link.get.user_info") + accessToken;
//        System.out.println(link);
//        String response = Request.Get(link).execute().returnContent().asString();
//
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode jsonNode = mapper.readTree(response);
//        String id = jsonNode.get("id").textValue();
//        String firstName = jsonNode.get("firstName").textValue();
//        String lastName = jsonNode.get("lastName").textValue();
//        String name = firstName + " " + lastName;
//        LinkedInPojo user = new LinkedInPojo(id, name);
//
//        return user;
//    }
//
//    public UserDetails buildUser(LinkedInPojo user) {
//        boolean enabled = true;
//        boolean accountNonExpired = true;
//        boolean credentialsNonExpired = true;
//        boolean accountNonLocked = true;
//
//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//        UserDetails userDetail = new User(user.getName(), "", enabled,
//                accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
//
//        return userDetail;
//    }
//}
