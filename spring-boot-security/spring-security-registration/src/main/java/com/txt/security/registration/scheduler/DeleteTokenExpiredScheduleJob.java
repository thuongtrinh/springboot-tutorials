package com.txt.security.registration.scheduler;

import com.txt.security.registration.common.JobType;
import com.txt.security.registration.entity.authen.ScheduleJobConfig;
import com.txt.security.registration.repository.PasswordResetTokenRepository;
import com.txt.security.registration.repository.VerificationTokenRepository;
import com.txt.security.registration.service.ScheduleJobConfigService;
import com.txt.security.registration.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteTokenExpiredScheduleJob {

    private final String jobType = JobType.DELETE_TOKEN_EXPIRED;
    private final ScheduleJobConfigService scheduleJobConfigService;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final VerificationTokenRepository verificationTokenRepository;

    @Scheduled(cron = "#{@deleteTokenExpiredCronExpression}", zone = "#{@deleteTokenExpiredTimeZone}")
    public void deleteTokenExpired() {
        executeJob();
    }

    public void executeJob() {
        try {
            log.info("========START DELETE TOKEN EXPIRED JOB========");
            Optional<ScheduleJobConfig> optional = scheduleJobConfigService.getConfig(jobType);
            if (optional.isEmpty()) {
                log.error("Cannot find config for JobType {}", jobType);
                return;
            }
            if (!optional.get().getEnabled()) {
                log.info("Schedule job is disabled");
                return;
            }

            Date now = DateUtil.getCurrentDate();
            passwordResetTokenRepository.deleteAllExpiredSince(now);
            verificationTokenRepository.deleteAllExpiredSince(now);

        } catch (Exception e) {
            log.error("delete Token Expired has error encountered {}", e.getMessage());
            e.printStackTrace();
        } finally {
            log.info("========END DELETE TOKEN EXPIRED JOB========");
        }
    }
}
