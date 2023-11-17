package com.txt.security.registration.service.impl;

import com.txt.security.registration.entity.authen.ScheduleJobConfig;
import com.txt.security.registration.repository.ScheduleJobConfigRepository;
import com.txt.security.registration.service.ScheduleJobConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleJobConfigServiceImpl implements ScheduleJobConfigService {

    private final ScheduleJobConfigRepository scheduleJobConfigRepository;

    @Override
    public Optional<ScheduleJobConfig> getConfig(String jobType) {
        return scheduleJobConfigRepository.findFirstByJobtype(jobType);
    }

}
