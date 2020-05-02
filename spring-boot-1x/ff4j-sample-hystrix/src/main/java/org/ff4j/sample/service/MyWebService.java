package org.ff4j.sample.service;


import javax.ws.rs.QueryParam;

import org.ff4j.sample.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
@Description("A controller for handling requests for hello messages")
public class MyWebService {

    @Autowired
    private MyService myService;
    
    @RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseDTO ping(@QueryParam("id") String id ) {
        return myService.doSomething(id);
    }
}
