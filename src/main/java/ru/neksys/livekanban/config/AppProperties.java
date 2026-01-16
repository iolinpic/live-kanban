package ru.neksys.livekanban.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.List;

@ConfigurationProperties(prefix = "app")
public record AppProperties(
        Security security,
        Websocket websocket
) {
    public record Security(Jwt jwt) {
        public record Jwt(
                String issuer,
                String secret,
                Duration accessTokenTtl
        ) {
        }
    }

    public record Websocket(List<String> allowedOrigins) {
    }
}
