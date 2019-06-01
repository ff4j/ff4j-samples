package org.ff4j.hello;

import org.ff4j.web.ApiConfig;
import org.ff4j.web.api.FF4jApiApplicationJersey2x;

/**
 * Sample application
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
public class MyFF4jJerseyApplication extends FF4jApiApplicationJersey2x {

    /**
     * current configuration.
     */
    private static ApiConfig conf;

    /**
     * {@inheritDoc}
     */
    @Override
    protected ApiConfig getWebApiConfiguration() {
        if (conf == null) {
            conf = new ApiConfig();
            conf.setFF4j(new MyFF4jProvider().getFF4j());
            conf.setAuthenticate(false);
            conf.setAutorize(false);
            conf.setDocumentation(true);
            conf.setPort(8080);
            conf.setHost("localhost");
            conf.setWebContext("simple");
            log.info("Returning API Config");
        }
        return conf;
    }
}
