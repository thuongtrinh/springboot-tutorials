package com.txt.mongoredis.track;

import com.txt.mongoredis.service.track.TrackActivityLogService;
import com.txt.mongoredis.utils.ActivityLogType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TrackActivityAspect {

    private final TrackActivityLogService trackBookActionServiceImpl;

    public TrackActivityAspect(@Qualifier("trackBookActionServiceImpl") TrackActivityLogService trackBookActionService
    ) {
        this.trackBookActionServiceImpl = trackBookActionService;
    }

    @Around("@annotation(trackActivity)")
    public Object trackActivityLog(ProceedingJoinPoint joinPoint, TrackActivity trackActivity) throws Throwable {
        TrackActivityLogService trackActivityLogService = getTrackActivityService(trackActivity.type());
        return trackActivityLogService.trackActivityLog(joinPoint);
    }

    private TrackActivityLogService getTrackActivityService(ActivityLogType type) {
        switch (type) {
            case USER_BOOK_ACTION:
                return trackBookActionServiceImpl;
            default:
                throw new RuntimeException("Un-support activity log type");
        }
    }

}
