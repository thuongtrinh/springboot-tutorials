package com.txt.security.registration.config;

import com.txt.security.registration.common.JobType;
import com.txt.security.registration.entity.authen.ScheduleJobConfig;
import com.txt.security.registration.exception.BusinessRuntimeException;
import com.txt.security.registration.service.ScheduleJobConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
@EnableScheduling
public class ScheduleJobResolverConfig {

    private final ScheduleJobConfigService scheduleJobConfigService;

    @Bean
    public String deleteTokenExpiredCronExpression() {
        Optional<ScheduleJobConfig> optional = scheduleJobConfigService.getConfig(JobType.DELETE_TOKEN_EXPIRED);
        if (optional.isEmpty()) {
            throw new BusinessRuntimeException("Cannot found schedule config cron expression for delete token expired");
        }
        return optional.get().getCronExpression();
    }

    @Bean
    public String deleteTokenExpiredTimeZone() {
        Optional<ScheduleJobConfig> optional = scheduleJobConfigService.getConfig(JobType.DELETE_TOKEN_EXPIRED);
        if (optional.isEmpty()) {
            throw new BusinessRuntimeException("Cannot found schedule config timezone for delete token expired");
        }
        return optional.get().getTimezone();
    }

}
