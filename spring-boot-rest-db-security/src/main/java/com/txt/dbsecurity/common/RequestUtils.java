package com.txt.dbsecurity.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class RequestUtils {

    public static Map<String, String> getRequestInformation(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put("header: " + key, value);
        }
        Enumeration parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String key = (String) parameterNames.nextElement();
            String value = request.getParameter(key);
            map.put("parameter: " + key, value);
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                map.put("cookie: " + cookie.getName(), cookie.getValue());

            }
        }

        while (parameterNames.hasMoreElements()) {
            String key = (String) parameterNames.nextElement();
            String value = request.getParameter(key);
            map.put("parameter: " + key, value);
        }
        map.put("getRequestIPAdrress", getClientIpAddress(request));
        map.put("getRemoteUser", request.getRemoteUser());
        map.put("getMethod", request.getMethod());
        map.put("getQueryString", request.getQueryString());
        map.put("getAuthType", request.getAuthType());
        map.put("getContextPath", request.getContextPath());
        map.put("getPathInfo", request.getPathInfo());
        map.put("getPathTranslated", request.getPathTranslated());
        map.put("getRequestedSessionId", request.getRequestedSessionId());
        map.put("getRequestURI", request.getRequestURI());
        map.put("getRequestURL", request.getRequestURL().toString());
        map.put("getMethod", request.getMethod());
        map.put("getServletPath", request.getServletPath());
        map.put("getContentType", request.getContentType());
        map.put("getLocalName", request.getLocalName());
        map.put("getProtocol", request.getProtocol());
        map.put("getRemoteAddr", request.getRemoteAddr());
        map.put("getServerName", request.getServerName());
        return map;
    }

    public static String getClientIpAddress(HttpServletRequest request) {
        String xForwardedForHeader = request.getHeader("X-Forwarded-For");
        if (xForwardedForHeader == null) {
            return request.getRemoteAddr();
        } else {
            // As of https://en.wikipedia.org/wiki/X-Forwarded-For
            // The general format of the field is: X-Forwarded-For: client, proxy1, proxy2 ...
            // we only want the client
            return new StringTokenizer(xForwardedForHeader, ",").nextToken().trim();
        }
    }
}
