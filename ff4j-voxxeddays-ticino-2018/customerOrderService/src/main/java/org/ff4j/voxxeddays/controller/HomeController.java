package org.ff4j.voxxeddays.controller;

import java.util.Map;

import org.ff4j.FF4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    
    @Autowired
    private FF4j ff4j;
    
    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("message", "OK");
        model.put("ff4j_feature_TEST",  ff4j.check("TEST"));
       
        return "welcome";
    }

}
