package com.txt.mongoredis.service.track.impl;

import com.txt.mongoredis.dto.track.TrackBookRequest;
import com.txt.mongoredis.entities.mongodb.dbfirst.ActivityLog;
import com.txt.mongoredis.repositories.mongodb.dbfirst.ActivityLogRepository;
import com.txt.mongoredis.service.track.TrackActivityLogService;
import com.txt.mongoredis.utils.Constants;
import com.txt.mongoredis.utils.JsonHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrackBookActionServiceImpl implements TrackActivityLogService {

    private final ActivityLogRepository activityLogRepository;

    @Override
    public Object trackActivityLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object methodResponse;
        List<ActivityLog> activityLogs = null;
        try {
            TrackBookRequest trackBookRequest = getTrackingBookRequest(joinPoint);
            activityLogs = getUserTrackingBookActivityLogs(trackBookRequest);
        } catch (Exception e) {
            log.error("Error while track user process book action", e.getMessage());
            e.printStackTrace();
        } finally {
            methodResponse = joinPoint.proceed();
            if (activityLogs != null) {
                activityLogRepository.saveAll(activityLogs);
            }
            System.out.println(JsonHelper.getInstance().objectToJson(methodResponse));
        }
        return methodResponse;
    }

    private TrackBookRequest getTrackingBookRequest(ProceedingJoinPoint joinPoint) {
        Method method = MethodSignature.class.cast(joinPoint.getSignature()).getMethod();
        Object[] args = joinPoint.getArgs();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int argIndex = 0; argIndex < args.length; argIndex++) {
            for (Annotation paramAnnotation : parameterAnnotations[argIndex]) {
                if (paramAnnotation instanceof RequestParam) {
//                    if (args[argIndex] instanceof TrackBookRequest) {
                        Object object = args[argIndex];
                        return TrackBookRequest.builder().data(JsonHelper.getInstance().objectToJson(object)).build();
//                    }
                }

//                if (paramAnnotation instanceof RequestBody) {
//                    if (args[argIndex] instanceof TrackBookRequest) {
//                        return (TrackBookRequest) args[argIndex];
//                    }
//                }
            }
        }
        throw new RuntimeException("Invalid joinPoint, cannot track process user action");
    }

    private List<ActivityLog> getUserTrackingBookActivityLogs(TrackBookRequest trackBookRequest) {
        List<ActivityLog> activityLogs = new ArrayList<>();
        String user = Constants.SYSTEM_NAME;
        if (StringUtils.isNotBlank(trackBookRequest.getAuthor())) {
            user = trackBookRequest.getAuthor();
        }
        ActivityLog activityLog = ActivityLog.builder()
                .user(user)
                .data(trackBookRequest.getData())
                .build();
        activityLogs.add(activityLog);
        return activityLogs;
    }

}
