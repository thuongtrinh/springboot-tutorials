package com.txt.security.registration.service;

import com.txt.security.registration.entity.authen.ScheduleJobConfig;

import java.util.Optional;

public interface ScheduleJobConfigService {

    Optional<ScheduleJobConfig> getConfig(String jobType);

}
