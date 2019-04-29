package org.ff4j.sample;

import org.ff4j.FF4j;
import org.ff4j.audit.Event;
import org.ff4j.audit.EventConstants;
import org.ff4j.core.Feature;
import org.ff4j.web.ApiConfig;
import org.ff4j.web.FF4jProvider;
import org.ff4j.web.api.FF4jApiApplicationJersey2x;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;

import static org.ff4j.audit.EventConstants.SOURCE_JAVA;
import static org.ff4j.audit.EventConstants.TARGET_FEATURE;

/**
 * Sample application
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
public class SimpleFF4JJerseyApplication extends FF4jApiApplicationJersey2x implements FF4jProvider {

    /**
     * Spring Bean.
     */
    private static ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

    /**
     * current configuration.
     */
    private static ApiConfig conf;

    private static int getRandomOffset(int size) {
        return (int) (Math.random() * Math.abs(size));
    }

    private static Event generateRandomEvent(ArrayList<Feature> features, int nbHours) {
        // Any existing feature
        String randomUID = features.get(getRandomOffset(features.size())).getUid();
        // Anytime from 12H
        long randomTimeStamp = System.currentTimeMillis() - (long) (Math.random() * 1000 * 3600 * nbHours);
        // Type ok or ko
        String myType = (getRandomOffset(2) == 0) ? EventConstants.ACTION_CHECK_OK : EventConstants.ACTION_CHECK_OK;
        Event event = new Event(SOURCE_JAVA, TARGET_FEATURE, randomUID, myType);
        event.setTimestamp(randomTimeStamp);
        return event;
    }

    /**
     * {@inheritDoc}
     */
    public ApiConfig getApiConfig() {
        if (conf == null) {
            getWebApiConfiguration();
        }
        log.info("Returning API Config");
        return conf;
    }

    @Override
    public FF4j getFF4j() {
        if (conf == null) {
            getWebApiConfiguration();
        }
        log.info("Returning FF4J");
        return conf.getFF4j();
    }

    @Override
    protected ApiConfig getWebApiConfiguration() {
        conf = new ApiConfig();
        conf.setFF4j(ctx.getBean(FF4j.class));
        //conf.enableDocumentation();
        conf.setPort(8282);
        conf.setHost("localhost");
        conf.setWebContext("webapi");

//      // login/Password
//      conf.setEnableAuthentication(true);
//      conf.setEnableAuthorization(true);
//      conf.createUser("admin", "admin", true, true);
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

        log.info(nbEvents + " event(s) generated.");

        return conf;
    }
}
