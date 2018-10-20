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
        model.put("featureCallUs",  ff4j.check("CallUS"));
        model.put("openIssue",  ff4j.check("openIssue"));
        
        if (ff4j.getPropertiesStore().existProperty("phoneNumber")) {
            model.put("phoneNumber",  ff4j.getProperty("phoneNumber").asString());
        }
       
        return "welcome";
    }

}
