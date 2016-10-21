package org.ff4j.springboot.web;

import java.util.Date;
import java.util.Map;

import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Description("A controller for handling requests for hello messages")
public class HomePage {
    
    @RequestMapping("/")
    public String home(Map<String, Object> model) {
        model.put("message", "Hello World");
        model.put("title", "FF4J White Application");
        model.put("date", new Date());
        return "home";
    }
    
}