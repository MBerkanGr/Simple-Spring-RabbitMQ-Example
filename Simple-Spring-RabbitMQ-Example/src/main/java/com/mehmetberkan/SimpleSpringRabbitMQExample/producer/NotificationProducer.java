package com.mehmetberkan.SimpleSpringRabbitMQExample.producer;

import com.mehmetberkan.SimpleSpringRabbitMQExample.model.Notification;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {

    @Value("${sr.rabbit.routing.name}")
    private String routingName;

    @Value("${sr.rabbit.exchange.name}")
    private String exchangeName;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendToQueue(Notification notification) {
        System.out.println("Notification Sent ID : " + notification.getId());
        rabbitTemplate.convertAndSend(exchangeName, routingName, notification);
    }
}
