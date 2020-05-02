package org.ff4j.spring.cloudconfig.app;

import javax.annotation.PostConstruct;

import org.ff4j.property.Property;
import org.ff4j.utils.json.PropertyJsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TestController {

	final String name = "AgeOfCedrick";
	
	@Value("${AgeOfCedrick}")
	private String ageOfCedrick;
	
	private Property<?> ageOfCedrick2;
	
	@PostConstruct
	public void initP() {
	    ageOfCedrick2 =PropertyJsonParser.parseProperty(ageOfCedrick);
	}

	@Autowired
	private ConfigurableEnvironment configurableEnvironment;

	@RequestMapping("/myenv")
	protected String getEnv() {
		StringBuilder result = new StringBuilder();
		MutablePropertySources sources = configurableEnvironment.getPropertySources();
		for (PropertySource<?> propertySource : sources) {
		    result.append(propertySource.getName())
            .append("\t->\t")
            .append(name)
            .append(" = ")
            .append(propertySource.getProperty(name))
            .append("<br>");
        }
		result.append("======================").append("<br>");
		result.append("Env: ").append(configurableEnvironment.getProperty(name)).append("<br>");
		result.append("Autowired: ").append(ageOfCedrick2.getClass());
		return result.toString();
	}
}
