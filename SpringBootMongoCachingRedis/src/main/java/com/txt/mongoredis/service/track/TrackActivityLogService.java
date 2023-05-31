package com.txt.mongoredis.service.track;

import org.aspectj.lang.ProceedingJoinPoint;

public interface TrackActivityLogService {
    Object trackActivityLog(ProceedingJoinPoint joinPoint) throws Throwable;
}
