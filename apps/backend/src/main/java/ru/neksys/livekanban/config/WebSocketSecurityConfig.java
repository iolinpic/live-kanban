package ru.neksys.livekanban.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import ru.neksys.livekanban.security.StompAuthChannelInterceptor;

@Configuration
public class WebSocketSecurityConfig implements WebSocketMessageBrokerConfigurer {

    private final StompAuthChannelInterceptor interceptor;

    public WebSocketSecurityConfig(StompAuthChannelInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(interceptor);
    }
}
