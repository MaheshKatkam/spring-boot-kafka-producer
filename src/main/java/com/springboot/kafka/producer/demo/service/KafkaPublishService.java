package com.springboot.kafka.producer.demo.service;

import com.springboot.kafka.producer.demo.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

@Service
public class KafkaPublishService {

    private static final Logger logger = Logger.getLogger(KafkaPublishService.class.getName());


    @Autowired
    KafkaTemplate<String,Object> kafkaTemplate;

    public void publishMessage(String message){
        CompletableFuture<SendResult<String, Object>> future =  kafkaTemplate.send("kafka-message-topic-partition-3",message);
        future.whenComplete((result,ex)->{
            if (ex == null) {
                logger.info("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                logger.info("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });
    }

    public void publishEvent(Employee  employee){
        CompletableFuture<SendResult<String, Object>> future =  kafkaTemplate.send("kafka-message-topic-java-event-partition-5",employee);
        future.whenComplete((result,ex)->{
            if (ex == null) {
                logger.info("Sent message=[" + employee.toString() +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                logger.info("Unable to send message=[" +
                        employee.toString() + "] due to : " + ex.getMessage());
            }
        });
    }
}

