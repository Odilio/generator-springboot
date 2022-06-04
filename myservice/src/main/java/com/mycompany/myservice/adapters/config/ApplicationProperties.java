package com.mycompany.myservice.adapters.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("application")
public class ApplicationProperties {
    private Cors cors = new Cors();

    @Data
    public static class Cors {
        private String pathPattern = "/api/**";
        private String allowedMethods = "*";
        private String allowedHeaders = "*";
        private String allowedOriginPatterns = "*";
        private boolean allowCredentials = true;
    }
}
