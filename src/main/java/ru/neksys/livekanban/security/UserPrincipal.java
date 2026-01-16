package ru.neksys.livekanban.security;


import java.security.Principal;
import java.util.List;
import java.util.UUID;

public record UserPrincipal(
        UUID userId,
        String email,
        String displayName,
        List<String> roles
) implements Principal {
    @Override
    public String getName() {
        return email;
    }
}
