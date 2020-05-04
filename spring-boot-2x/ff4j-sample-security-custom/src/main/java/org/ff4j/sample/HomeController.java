package org.ff4j.sample;

import org.ff4j.FF4j;
import org.ff4j.core.Feature;
import org.ff4j.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Having a home page for our app allowing.
 * 
 * - To provide a link to web console
 * - To provide a link to REST API
 * - To check for features
 */
@RestController
@RequestMapping("/")
public class HomeController {
    
    /** Some logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
    
    private static final String FEATURE_ALL        = "feature_public";
    private static final String FEATURE_SUPERUSER  = "feature_superuser";
    private static final String FEATURE_ADMIN_ONLY = "feature_admin";
    
    @Autowired
    public FF4j ff4j;
    
    private boolean init = false;
    
    /**
     * Rendering an HTML output for the home page.
     * 
     * We use ff4j to check what to display, very dummy.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html")
    public String get() {
        LOGGER.info(" + Rendering home page...");
        if (!init) populateDummyFeatureForMySample();
        
        StringBuilder htmlPage = new StringBuilder("<html><body><ul>");
        htmlPage.append("<h2>This is home page.</h2>");
        htmlPage.append("<p>Displaying the links below is driven by permissions of the connected user. </p>");
        htmlPage.append("<p>You are connected with user <span style=\"color:blue\">");
        htmlPage.append(ff4j.getAuthorizationsManager().getCurrentUserName());
        htmlPage.append("</span></p>");
        htmlPage.append("<p>Current user has the following permissions <span style=\"color:orange\">");
        htmlPage.append(ff4j.getAuthorizationsManager().getCurrentUserPermissions());
        htmlPage.append("</span></p>");
        htmlPage.append("<p<b>List of resources for you :</b><br/><ul>");
        if (ff4j.check(FEATURE_ALL)) {
            htmlPage.append("<li>THIS LINE IS SHOWN FOR ALL USERS</li>");
        }
        if (ff4j.check(FEATURE_SUPERUSER)) {
            htmlPage.append("<li>THIS LINE IS SHOWN ONLY FOR PEOPLE WITH ROLE <b>SUPERUSER</b> or <b>ADMIN</b></li>");
        }
        if (ff4j.check(FEATURE_ADMIN_ONLY)) {
            htmlPage.append("<li>THIS LINE IS SHOWN ONLY FOR PEOPLE WITH ROLE <b>ADMIN</b></li>");
        }
        htmlPage.append("</ul></body></html>");
        return htmlPage.toString();
    }
    
    private void populateDummyFeatureForMySample() {
        // Everybody can see it
        if (!ff4j.exist(FEATURE_ALL)) {
            ff4j.createFeature(new Feature(FEATURE_ALL, true));
        }
        if (!ff4j.exist(FEATURE_SUPERUSER)) {
            Feature feature_superuser = new Feature(FEATURE_SUPERUSER, true);
            feature_superuser.setPermissions(Util.set("superuser", "admin"));
            ff4j.createFeature(feature_superuser);
        }
        if (!ff4j.exist(FEATURE_ADMIN_ONLY)) {
            Feature feature_admin= new Feature(FEATURE_ADMIN_ONLY, true);
            feature_admin.setPermissions(Util.set("admin"));
            ff4j.createFeature(feature_admin);
        }
        LOGGER.info(" + Features and properties have been created for the sample.");
        init = true;
    }
}
