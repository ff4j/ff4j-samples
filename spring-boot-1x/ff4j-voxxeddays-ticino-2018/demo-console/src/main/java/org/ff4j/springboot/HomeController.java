package org.ff4j.springboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html")
    public String sayHello() {
        StringBuilder response = new StringBuilder("<html><body>");
        response.append("<H1>Gwenn ad Du Rulez !!, Brezhoneg et fier de l'etre !</h1>");
        response.append("</span></body></html>");
        return response.toString();
    }

}
