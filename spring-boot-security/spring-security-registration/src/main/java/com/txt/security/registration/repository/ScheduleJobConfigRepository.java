package com.txt.security.registration.repository;

import com.txt.security.registration.entity.authen.ScheduleJobConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleJobConfigRepository extends JpaRepository<ScheduleJobConfig, Long> {

    Optional<ScheduleJobConfig> findFirstByJobtype(String jobType);

}
