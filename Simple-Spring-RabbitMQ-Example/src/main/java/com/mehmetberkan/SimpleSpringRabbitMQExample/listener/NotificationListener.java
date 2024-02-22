package com.mehmetberkan.SimpleSpringRabbitMQExample.listener;

import com.mehmetberkan.SimpleSpringRabbitMQExample.model.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    @RabbitListener(queues = "mbg-queue")
    public void handleMessage(Notification notification) {
        System.out.println("Message received...");
        System.out.println(notification.toString());
    }
}
