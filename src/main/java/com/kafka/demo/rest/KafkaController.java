package com.kafka.demo.rest;

import com.kafka.demo.dto.Employee;
import com.kafka.demo.service.KafkaPublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class KafkaController {

    private static final Logger logger = Logger.getLogger(KafkaPublishService.class.getName());
    @Autowired
    KafkaPublishService publishService;

    @GetMapping("publish/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable("message") String message){
        try{

            for(int i = 1 ; i <=10000 ; i ++){
                publishService.publishMessage(message + "-"+i);
            }
            return ResponseEntity.ok("Message Published Succesfully");

        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("publish/event")
    public ResponseEntity<?> eventPublisher(@RequestBody Employee employee){
        try{

            for(int i = 1 ; i <=5 ; i ++){
                employee.setName("Mahesh"+"-"+ i);
                publishService.publishEvent(employee);
            }
            return ResponseEntity.ok("Message Published Successfully");

        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
