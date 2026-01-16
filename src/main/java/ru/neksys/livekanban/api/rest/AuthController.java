package ru.neksys.livekanban.api.rest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neksys.livekanban.security.UserPrincipal;

import java.util.Map;

@RestController
public class AuthController {
    @GetMapping("/api/me")
    public Map<String, Object> me(@AuthenticationPrincipal UserPrincipal me) {
        return Map.of(
                "userId", me.userId(),
                "email", me.email(),
                "name", me.displayName(),
                "roles", me.roles()
        );
    }
}
