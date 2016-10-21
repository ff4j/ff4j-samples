package org.ff4j.demo;

import java.util.ArrayList;

import org.ff4j.FF4j;
import org.ff4j.audit.Event;
import org.ff4j.audit.EventType;
import org.ff4j.core.Feature;
import org.ff4j.web.ApiConfig;
import org.ff4j.web.FF4JProvider;
import org.ff4j.web.api.FF4JApiApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Sample application
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
public class SimpleFF4JJerseyApplication extends FF4JApiApplication implements FF4JProvider {

    /** Spring Bean. */
    private static ApplicationContext ctx =  new ClassPathXmlApplicationContext("applicationContext.xml"); 
    
    /** current configuration. */
    private static ApiConfig conf;

    private static int getRandomOffset(int size) {
        return (int) (Math.random() * Math.abs(size));
    }

    private static Event generateRandomEvent(ArrayList<Feature> features, int nbHours) {
        // Any existing feature
        String randomUID = features.get(getRandomOffset(features.size())).getUid();
        // Anytime from 12H
        long randomTimeStamp = System.currentTimeMillis() - (long) (Math.random() * 1000 * 3600 * nbHours);
        if (Math.random() > 0.5d) {
            randomTimeStamp = System.currentTimeMillis() + (long) (Math.random() * 1000 * 3600 * nbHours);
        }
        // Type ok or ko
        EventType myType = (getRandomOffset(2) == 0) ? EventType.FEATURE_CHECK_ON : EventType.FEATURE_CHECK_OFF;
        return new Event(randomUID, myType, randomTimeStamp);
    }

    private void initConf() {
        conf = new ApiConfig(ctx.getBean(FF4j.class));
        conf.setDocumentation(true);
      
        // login/Password
        //conf.setEnableAuthentication(true);
        //conf.setEnableAuthorization(true);
        //conf.createUser("admin", "admin", true, true, null);
//      conf.createUser("user", "user", true, false);
//      conf.createUser("clu", "clu", true, false);
//
//         // ApiKeys (identification only)
        // conf.createApiKey("12345", false, false);
        // conf.createApiKey("abcde", true, true);

        // Initialization of events
        int nbEvents = (int) (Math.random() * 100000);
        int nbHours = 12;
        ArrayList<Feature> features = new ArrayList<Feature>(conf.getFF4j().getFeatures().values());
        for (int i = 0; i < nbEvents; i++) {
            conf.getFF4j().getEventRepository().saveEvent(generateRandomEvent(features, nbHours));
        }
    }

    /** {@inheritDoc} */
    @Override
    public ApiConfig getApiConfig() {
        if (conf == null) {
            initConf();
        }
        return conf;
    }

    @Override
    public FF4j getFF4j() {
        if (conf == null) {
            initConf();
        }
        return conf.getFF4j();
    }

}
