package org.ff4j.sample.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/")
@Api(value = "greeting", tags = "operations pertaining to greeting")
public class Greeting {

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html")
    public String sayHello() {
        StringBuilder response = new StringBuilder("<html><body><ul>");
        response.append("<li> To access the <b>WebConsole</b> please go to <a href=\"./ff4j-web-console/home\">ff4j-web-console</a>");
        response.append("<li> To access the <b>REST API</b> please go to <a href=\"./api/ff4j\">api/ff4j</a>");
        response.append("<p>Is <span style=\"color:red\">Awesome</span> feature activated ? from  ff4j.check(\"AwesomeFeature\") <span style=\"color:blue\">");
        response.append("</span></body></html>");
        return response.toString();
    }
}
