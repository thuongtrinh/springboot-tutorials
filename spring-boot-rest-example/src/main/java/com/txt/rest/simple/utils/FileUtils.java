package com.txt.rest.simple.utils;


import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class FileUtils {

    public static String getFileExtension(String fileName) {
        if (Objects.nonNull(fileName) && fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }

    public static String getFileName(String name) {
        if (StringUtils.isNotEmpty(name)) {
            return name.substring(name.lastIndexOf("/") + 1);
        } else {
            return "";
        }
    }

}
