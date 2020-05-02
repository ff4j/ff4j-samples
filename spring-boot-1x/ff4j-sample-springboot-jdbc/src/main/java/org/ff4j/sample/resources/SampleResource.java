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

package org.ff4j.sample.resources;

import org.ff4j.FF4j;
import org.ff4j.spring.autowire.FF4JFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Paul
 *
 * @author <a href="mailto:paul58914080@gmail.com">Paul Williams</a>
 */
@RestController
public class SampleResource {
  
    @FF4JFeature(value = "feature_X")
    private boolean feature_X;

    @Autowired
    private FF4j ff4j;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html")
    public String sayHello() {
        StringBuilder response = new StringBuilder("<html><body><ul>");
        response.append("<li> To access the <b>WebConsole</b> please go to <a href=\"./ff4j-web-console/home\">ff4j-web-console</a>");
        response.append("<li> To access the <b>REST API</b> please go to <a href=\"./api/ff4j\">api/ff4j</a>");
        response.append("<li> To access the <b>Swagger File </b> please go to <a href=\"./v2/api-docs\">/v2/api-docs</a></ul>");
        
        response.append("<p>Is <span style=\"color:red\">Awesome</span> feature activated ? from  ff4j.check(\"feature_X\") <span style=\"color:blue\">");
        response.append(ff4j.check("feature_X"));
        response.append("</span></body></html>");
        return response.toString();
    }
    
}
