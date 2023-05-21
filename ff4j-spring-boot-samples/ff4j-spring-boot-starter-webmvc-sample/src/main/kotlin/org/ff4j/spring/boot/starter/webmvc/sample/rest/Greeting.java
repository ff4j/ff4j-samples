package org.ff4j.spring.boot.starter.webmvc.sample.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Tag(name = "Greetings", description = "operations pertaining to greeting")
public class Greeting {

  @GetMapping()
  @Operation(summary = "Greeting")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "500", description = "Internal Server Error")})
  public String greeting() {
    return "<ul>" +
            "<li><a href=\"/swagger-ui/index.html\">Swagger</a>" +
            "<li><a href=\"/api/v1/ff4j\">Web Api</a>" +
            "<li><a href=\"/ff4j-web\">Web Console (admin/admin)</a>";
  }
}
