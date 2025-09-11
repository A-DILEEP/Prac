package com.hackerrank.configstyles.service;

import org.springframework.stereotype.Component;

@Component("emailNotificationService")
public class EmailNotificationService implements NotificationService {

    private final String serviceName = "EMAIL_SERVICE";

    @Override
    public ServiceResponse sendNotification(String notification) {
        return new ServiceResponse(serviceName, notification);
    }
}
