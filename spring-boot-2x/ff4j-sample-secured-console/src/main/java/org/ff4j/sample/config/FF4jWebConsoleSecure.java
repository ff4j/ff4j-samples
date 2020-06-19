package org.ff4j.sample.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ConditionalOnProperty(value="ff4j.webconsole.secure", havingValue = "true", matchIfMissing = false)
public class FF4jWebConsoleSecure extends WebSecurityConfigurerAdapter {
	
    /** Logger for the class. */
    private static final Logger LOGGER = LoggerFactory.getLogger(FF4jWebConsoleSecure.class);
    
    @Value("${ff4j.webconsole.username:admin}")
    private String admin;
    
    @Value("${ff4j.webconsole.password:ff4j}")
    private String adminPasswd;
    
    @Value("${ff4j.webconsole.url:/ff4j-web-console}")
    private String webConsoleUrl;
    
	/** {@inheritDoc} */
	@Override
    protected void configure(AuthenticationManagerBuilder auth)
    throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
            .withUser(admin).password(encoder.encode(adminPasswd)).roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        LOGGER.info("Securing the webconsole access as property 'ff4j.webconsole.secure' is true");
        http.authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers(webConsoleUrl + "/**").hasRole("ADMIN")
            .and()
            .formLogin();
            //.httpBasic();
    }

}
