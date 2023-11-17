package com.txt.security.registration.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;

@Slf4j
public class CommonUtils {

    public static String generateMessage(String message, Object... listParams) {
        MessageFormat messageFormat = new MessageFormat(message);
        return messageFormat.format(listParams);
    }

    public static String getClientIP(HttpServletRequest request) {
        /*
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null || xfHeader.isEmpty() || !xfHeader.contains(request.getRemoteAddr())) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
        */

        // return "128.101.101.101"; // for testing United States
        // return "41.238.0.198"; // for testing Egypt
        return "2402:800:63a7:d5e6:b9d5:9802:86f6:ed9"; // for testing VN
    }

    public static String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
