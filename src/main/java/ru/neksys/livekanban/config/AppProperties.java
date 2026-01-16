package ru.neksys.livekanban.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.List;

@ConfigurationProperties(prefix = "app")
public record AppProperties(
        Security security,
        Cors cors
) {
    public record Security(Jwt jwt) {
        public record Jwt(
                String issuer,
                String secret,
                Duration accessTokenTtl
        ) {
        }
    }

    public record Cors(List<String> allowedOrigins) {
    }
}
