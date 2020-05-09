package org.ff4j.sample.security;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.TextConfigurationRealm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Create user/password and roles.
 *
 * @author Cedrick LUNVEN (@clunven)
 */
@Configuration
public class ApacheShiroSecurityConfig {
    
    @Bean
    public Realm realm() {
        TextConfigurationRealm realm = new TextConfigurationRealm();
        realm.setUserDefinitions(
                  // username=user, password=user, roles=user
                  "user=user,user\n" 
                  // username=superuser, password=superuser, roles=user,superuser
                + "superuser=superuser,user,superuser\n"
                  // username=admin, password=admin, roles=user,admin
                + "admin=admin,user,admin");
        // Associate user and permissions
        realm.setRoleDefinitions(
                 "admin=admin,user\n" + 
                 "superuser=user,superuser\n" + 
                 "user=user");
        realm.setCachingEnabled(true);
        return realm;
    }

    /**
     * Login/Logout
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/login.html", "authc");
        chainDefinition.addPathDefinition("/logout", "logout");
        return chainDefinition;
    }

}
