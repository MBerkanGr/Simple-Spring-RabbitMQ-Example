package com.mehmetberkan.SimpleSpringRabbitMQExample.handler;

import com.mehmetberkan.SimpleSpringRabbitMQExample.model.Notification;
import com.mehmetberkan.SimpleSpringRabbitMQExample.producer.NotificationProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.UUID;

@Component
public class NotificationSender {

    @Autowired
    private NotificationProducer notificationProducer;

    public void getThread() {
        Thread thread = new Thread(() -> {
            while (true) {
                Notification notification = new Notification();
                notification.setId(UUID.randomUUID().toString());
                notification.setCreatedAt(new Date());
                notification.setMessage("MBG - Rabbit MQ");
                notification.setSeen(false);
                notificationProducer.sendToQueue(notification);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setName("Notification sender");
        thread.start();
    }

    @PostConstruct
    public void init() {
        getThread();
    }
}
