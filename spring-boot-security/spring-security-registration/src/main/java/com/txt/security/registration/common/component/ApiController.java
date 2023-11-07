package com.txt.security.registration.common.component;

import org.springframework.beans.factory.annotation.Value;

public abstract class ApiController {
    @Value("${application.service-name:default-service}")
    protected String serviceName;
}