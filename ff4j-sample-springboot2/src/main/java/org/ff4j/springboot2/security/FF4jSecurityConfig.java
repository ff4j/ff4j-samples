package org.ff4j.springboot2.security;

/*
 * #%L
 * ff4j-spring-boot-web-api
 * %%
 * Copyright (C) 2013 - 2017 FF4J
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.ff4j.web.ApiConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer.UserDetailsBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Security configuration
 *
 * @author Cedrick LUNVEN (@clunven)
 * @author <a href="mailto:drizztguen77@gmail.com">Curtis White</a>
 */
@Configuration
@EnableWebSecurity
public class FF4jSecurityConfig extends WebSecurityConfigurerAdapter {

    private ApiConfig apiConfig;

    private FF4jBasicAuthenticationEntryPoint authenticationEntryPoint;

    /**
     * API REST fined grained tests.
     */
    public FF4jSecurityConfig(ApiConfig apiConfig, FF4jBasicAuthenticationEntryPoint authenticationEntryPoint) {
        this.apiConfig = apiConfig;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("rawtypes")
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Load APIConfig Users into Spring security in Memory ...
        UserDetailsManagerConfigurer config = auth.inMemoryAuthentication();
        if (apiConfig.isAuthenticate()) {
            int count = apiConfig.getUsers().keySet().size();
            int idx = 0;
            for (String currentUser : apiConfig.getUsers().keySet()) {
                UserDetailsBuilder udb = config.withUser(currentUser)
                        .password(passwordEncoder().encode(apiConfig.getUsers().get(currentUser)))
                        .roles(apiConfig.getPermissions().get(currentUser).toArray(new String[0]));
                // There is another user to use
                if (idx++ < count) {
                    config = udb.and();
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        if (apiConfig.isAuthenticate()) {
            // ENFORCE AUTHENTICATION
            http
                    .httpBasic()
                    .authenticationEntryPoint(authenticationEntryPoint)
                    .and()
                    .exceptionHandling()
                    .and().csrf().disable()     // DISABLE CSRF
                    .authorizeRequests()
                    .antMatchers("/ff4j-web-console/**").hasRole("ADMIN")
                    .antMatchers("/api/**").hasRole("USER")
                    .antMatchers("/").permitAll()
                    .anyRequest().authenticated();

            http.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
/*
            http.httpBasic()
                    .and().csrf().disable()     // DISABLE CSRF
                    .authorizeRequests()
                    .antMatchers("/ff4j-web-console/**").hasRole("ADMIN")
                    .antMatchers("/api/**").hasRole("USER")
                    .antMatchers("/").permitAll()
                    .anyRequest().authenticated();
*/
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SimpleUrlAuthenticationFailureHandler getMyFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler();
    }

    public class CustomFilter extends GenericFilterBean {

        @Override
        public void doFilter(
                ServletRequest request,
                ServletResponse response,
                FilterChain chain) throws IOException, ServletException {
            chain.doFilter(request, response);
        }
    }
}
