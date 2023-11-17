package com.txt.security.registration.entity.authen;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "schedule_job_config")
public class ScheduleJobConfig {

    @Id
    @Column
    private String jobtype;

    @Column
    private String cronExpression;

    @Column
    private String timezone;

    @Column
    private Boolean enabled;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
