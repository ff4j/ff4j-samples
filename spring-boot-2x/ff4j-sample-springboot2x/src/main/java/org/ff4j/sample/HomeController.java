package org.ff4j.sample;

import javax.servlet.http.HttpServletRequest;

import org.ff4j.FF4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Having a home page for our app allowing.
 * 
 * - To provide a link to web console
 * - To provide a link to REST API
 * - To check for features
 */
@Controller
@RequestMapping("/")
public class HomeController {
    
    /** Some logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
    
    /** Constants from the dataset. */
    private static final String FEATURE_AWESOME = "AwesomeFeature";
    private static final String FEATURE1      = "first";
    private static final String PROPERTY1     = "a";
    
    @Autowired
    public FF4j ff4j;
    
    @GetMapping("/")
    public String home(HttpServletRequest req, Model model) {
        
        // if not exist, create and evaluate
        model.addAttribute("featureAwesomeFlag", ff4j.check(FEATURE_AWESOME));
        
        // Have a behaviour based on the state of a feature
        if (ff4j.check(FEATURE1)) {
            LOGGER.info(FEATURE1 + " should be activated");
        }
        
        // You could make available Feature in the UI as an Object
        if (ff4j.getFeatureStore().exist(FEATURE_AWESOME)) {
            model.addAttribute("ff4j_features_Awesomefeature", ff4j.getFeature(FEATURE_AWESOME));
        }
        
        // You could make available Feature in the UI as an Object
        if (ff4j.getPropertiesStore().existProperty(PROPERTY1)) {
            model.addAttribute("ff4j_properties_a", ff4j.getProperty(PROPERTY1));
        } 
        
        return "index";
    }
}
