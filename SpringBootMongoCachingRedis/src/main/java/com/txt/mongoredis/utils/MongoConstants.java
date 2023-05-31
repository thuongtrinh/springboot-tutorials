package com.txt.mongoredis.utils;

public class MongoConstants {
    public static final String ID = "_id";
    public static final String STATUS = "status";
    public static final String CODE = "code";

    public static <T> String getCollectionName(Class<T> clazz) {
        return clazz.getSimpleName().toLowerCase();
    }
}
