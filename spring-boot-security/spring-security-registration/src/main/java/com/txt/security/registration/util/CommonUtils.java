package com.txt.security.registration.util;

import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;

@Slf4j
public class CommonUtils {

    public static String generateMessage(String message, Object... listParams) {
        MessageFormat messageFormat = new MessageFormat(message);
        return messageFormat.format(listParams);
    }

}
