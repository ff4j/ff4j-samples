package org.ff4j.springboot.web;

/*
 * #%L
 * ff4j-sample-archaius
 * %%
 * Copyright (C) 2013 - 2016 FF4J
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomeServlet extends HttpServlet {
    
    /** Serial. */
    private static final long serialVersionUID = 8858197018307974406L;

    /** Default Value. */
    public WelcomeServlet() {
    }
    
    /** {@inheritDoc} */
    public void doGet(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        res.setContentType("text/html");
        res.getWriter().println("<html><body>"
                + "<li><a href=\"/ff4j-console\" /> Old Console </a>"
                + "<li><a href=\"/ff4j/\" /> New Console </a>"
                + "</body></html>");
    }

}
