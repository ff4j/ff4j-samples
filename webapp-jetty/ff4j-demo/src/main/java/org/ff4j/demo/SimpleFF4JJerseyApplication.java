package org.ff4j.demo;

import org.ff4j.FF4j;
import org.ff4j.audit.Event;
import org.ff4j.audit.EventConstants;
import org.ff4j.audit.repository.EventRepository;
import org.ff4j.core.Feature;
import org.ff4j.utils.TimeUtils;
import org.ff4j.utils.Util;
import org.ff4j.web.ApiConfig;
import org.ff4j.web.FF4jProvider;
import org.ff4j.web.api.FF4jApiApplicationJersey2x;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.ff4j.audit.EventConstants.*;

/**
 * Sample application
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
public class SimpleFF4JJerseyApplication extends FF4jApiApplicationJersey2x implements FF4jProvider {

    /** Spring Bean. */
    private static ApplicationContext ctx =  new ClassPathXmlApplicationContext("applicationContext.xml"); 
    
    /** current configuration. */
    private static ApiConfig conf;

    private List < String > sources = new ArrayList<String>();
    
    private List < String > hostNames = new ArrayList<String>();
    
    private List < String > users = new ArrayList<String>();    
    
    // Utility to generate event
    protected Event generateFeatureUsageEvent(String uid) {
        Event evt = new Event(SOURCE_JAVA, TARGET_FEATURE, uid, ACTION_CHECK_OK);
        if (System.currentTimeMillis() % 2 == 0) {
            evt.setHostName(Util.getRandomElement(hostNames));
            evt.setSource(Util.getRandomElement(sources));
            evt.setUser( Util.getRandomElement(users));
        }
        return evt;
    }
    
    // Generate a random event during the period
    protected Event generateFeatureUsageEvent(String uid, long timestamp) {
        Event event = generateFeatureUsageEvent(uid);
        event.setTimestamp(timestamp);
        return event;
    }
    
    // Generate a random event during the period
    protected Event generateFeatureUsageEvent(String uid, long from, long to) {
        return generateFeatureUsageEvent(uid, from + (long) (Math.random() * (to-from)));
    }
    
    // Generate a random event during the period
    protected Event generateRandomFeatureUsageEvent(List < Feature > features, long from, long to) {
        return generateFeatureUsageEvent(Util.getRandomElement(features).getUid(), from , to);
    }
    
    // Populate repository for test
    public void populateRepository(int totalEvent) {
        EventRepository repo      = getFF4j().getEventRepository(); 
        
        List < Feature > features = new ArrayList<Feature>(getFF4j().getFeatures().values());
        for (int i = 0; i < totalEvent; i++) {
            repo.saveEvent(generateRandomFeatureUsageEvent(features,
                    TimeUtils.getTodayMidnightTime(), 
                    TimeUtils.getTomorrowMidnightTime()));
        }

        List < String >  actions  = new ArrayList<String>(Arrays.asList(EventConstants.ACTION_CREATE, 
                EventConstants.ACTION_CREATE, EventConstants.ACTION_DELETE, EventConstants.ACTION_CONNECT));
        // Events for audit trail today
        for (int j = 0; j < 9; j++) {
            Event evt = generateRandomFeatureUsageEvent(features,
                    TimeUtils.getTodayMidnightTime(), 
                    TimeUtils.getTomorrowMidnightTime());
            evt.setAction(Util.getRandomElement(actions));
            repo.saveEvent(evt);
        }
        // Events for audit trail 100 days
        for (int k = 0; k < 100; k++) {
            Event evt = generateRandomFeatureUsageEvent(features,
                    TimeUtils.getTodayMidnightTime() - (1000 * 3600) * 24 * 100 * k, 
                    TimeUtils.getTodayMidnightTime());
            evt.setAction(Util.getRandomElement(actions));
            repo.saveEvent(evt);
        }
    }

    @Override
    public FF4j getFF4j() {
        if (conf == null) {
            getWebApiConfiguration();
        }
        return conf.getFF4j();
    }

    @Override
    protected ApiConfig getWebApiConfiguration() {
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

        sources = new ArrayList<String>();
        sources.add(SOURCE_JAVA);
        sources.add(EventConstants.SOURCE_SSH);
        sources.add(EventConstants.SOURCE_WEB);
        sources.add(EventConstants.SOURCE_WEBAPI);

        hostNames = new ArrayList<String>();
        hostNames.add("node1");
        hostNames.add("node2");

        users = new ArrayList<String>();
        users.add("Pierre");
        users.add("Paul");
        users.add("Jacques");
        System.out.println("initConf1");
        // Create random DATA for today
        populateRepository(100);

        return conf;
    }
}
