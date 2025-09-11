package com.hackerrank.configstyles.service;

public class CallNotificationService implements NotificationService {
    private String serviceName;

    public CallNotificationService(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public ServiceResponse sendNotification(String notification) {
        return new ServiceResponse(serviceName, notification);
    }

}
