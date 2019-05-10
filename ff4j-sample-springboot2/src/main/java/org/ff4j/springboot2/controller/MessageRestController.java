package org.ff4j.springboot2.controller;


import org.ff4j.FF4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageRestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final FF4j ff4j;

    @Value("${message:Message default}")
    private String message;


    @Autowired
    public MessageRestController(FF4j ff4j) {
        this.ff4j = ff4j;
    }

    @GetMapping("/message")
    public String getMessage() {
        logger.debug("Message: " + message);

        if (ff4j.check("Friends")) {
            return "Hello FF4j friends";
        } else {
            return this.message;
        }
    }
}
