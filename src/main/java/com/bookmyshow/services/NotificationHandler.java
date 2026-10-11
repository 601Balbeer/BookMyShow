package com.bookmyshow.services;

import com.bookmyshow.enums.NotificationChannel;
import com.bookmyshow.models.Notification;
import com.bookmyshow.models.NotificationTemplate;

import java.util.HashMap;

public interface NotificationHandler {
    public NotificationChannel getChannel();
    public Notification sendNotification(Notification notification,
                                         HashMap<String, String> context, NotificationTemplate notificationTemplate);
}
