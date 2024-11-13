package com.springboot.kafka.producer.kafka.producer.demo.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfiguration {

    @Bean
    public NewTopic createTopic(){
        return new NewTopic("kafka-message-topic-java-partition-3",5,(short)1);
    }

    @Bean
    public NewTopic createEventTopic(){
        return new NewTopic("kafka-message-topic-java-event-partition-5",5,(short)1);
    }
}
