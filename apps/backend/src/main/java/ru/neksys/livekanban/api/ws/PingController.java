package ru.neksys.livekanban.api.ws;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.time.Instant;
import java.util.Map;

@Controller
public class PingController {

    @MessageMapping("/ping")
    @SendTo("/topic/ping")
    public Map<String, Object> ping(@Payload Map<String, Object> payload, Principal principal) {
        return Map.of(
                "ts", Instant.now().toString(),
                "from", principal != null ? principal.getName() : "anonymous",
                "payload", payload
        );
    }
}
