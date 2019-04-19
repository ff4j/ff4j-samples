/*
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2013-2016 the original author or authors.
 */

package org.ff4j.springboot2.controller;

import org.ff4j.FF4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Home page taken from Springboot sample
 */
@RestController
public class HomeResource {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final FF4j ff4j;

    @Autowired
    public HomeResource(FF4j ff4j) {
        this.ff4j = ff4j;
    }

    @GetMapping(value = "/", produces = "text/html")
    public String sayHello() {
        StringBuilder response = new StringBuilder("<html><body><ul>");
        response.append("<li> To access the <b>FF4j Web Console</b> please go to <a href=\"./ff4j-web-console/\">/ff4j-web-console/</a>");
        response.append("<li> To access the <b>REST API</b> please go to <a href=\"./api/ff4j\">/api/ff4j</a>");
        response.append("<li> To access the <b>Swagger API Documentation</b> please go to <a href=\"./swagger/index.html\">/swagger/index.html</a>");
        response.append("<li> To access the <b>Swagger API Docs</b> please go to <a href=\"./v2/api-docs\">/v2/api-docs</a>");
        response.append("<p>Is <span style=\"color:red\">Awesome</span> feature activated ? from  ff4j.check(\"AwesomeFeature\") <span style=\"color:blue\">");
        response.append(ff4j.check("AwesomeFeature"));
        response.append("</span></body></html>");
        return response.toString();
    }
}
