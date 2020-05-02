package org.ff4j.sample.config;

import org.ff4j.spring.boot.web.api.config.EnableFF4jSwagger;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFF4jSwagger
public class SwaggerConfig {
    // if you wish to have your own configurations
    /*@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "FF4J spring boot starter (ff4j.org) Sample",
                "Administer and operate all tasks on your features through this api",
                "1.8",
                "Terms of service",
                new Contact("Paul WILLIAMS", "https://github.com/paul58914080", "paul58914080@gmail.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0.html",
                Collections.emptyList());
    }*/
}
