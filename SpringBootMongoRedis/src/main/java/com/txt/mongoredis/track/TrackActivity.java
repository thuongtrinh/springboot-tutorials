package com.txt.mongoredis.track;


import com.txt.mongoredis.utils.ActivityLogType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TrackActivity {
    ActivityLogType type();
}
