package org.ff4j.sample;

import javax.servlet.http.HttpServletRequest;

import org.ff4j.FF4j;
import org.ff4j.core.Feature;
import org.ff4j.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    
    /** Some logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
    
    private static final String FEATURE_ALL        = "feature_public";
    private static final String FEATURE_SUPERUSER  = "feature_superuser";
    private static final String FEATURE_ADMIN_ONLY = "feature_admin";
    
    @Autowired
    public FF4j ff4j;
    
    private boolean init = false;

    @RequestMapping("/")
    public String home(HttpServletRequest request, Model model) {
        LOGGER.info(" + Rendering home page...");
        if (!init) populateDummyFeatureForMySample();
        model.addAttribute(FEATURE_ALL, ff4j.check(FEATURE_ALL));
        model.addAttribute(FEATURE_SUPERUSER, ff4j.check(FEATURE_SUPERUSER));
        model.addAttribute(FEATURE_ADMIN_ONLY, ff4j.check(FEATURE_ADMIN_ONLY));
        return "home";
    }
    
    /**
     * FILL FeatureStore with a few features.
     */
    private void populateDummyFeatureForMySample() {
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
