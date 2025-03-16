package com.ejada.product.service.controller;

import com.ejada.product.service.model.request.UserRequest;
import com.ejada.product.service.model.response.UserResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.UUID;

@Controller
public class SocketController {

    @GetMapping("/greeting")
    public String greeting() {
        return "greeting";
    }

    @MessageMapping("/hello") // /app/hello
    @SendTo("/topic/greetings")
    public UserResponse greeting(UserRequest message) throws Exception {
        Thread.sleep(500); // Simulate a processing delay
        return UserResponse.builder()
                .id(UUID.randomUUID().toString())
                .name("Hello, " + message.getName() + "!")
                .job(message.getJob())
                .createdAt(LocalDateTime.now().toString())
                .build();
    }

}
