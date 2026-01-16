package ru.neksys.livekanban.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.neksys.livekanban.config.AppProperties;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class JwtService {

    private final AppProperties props;
    private final SecretKey key;

    public JwtService(AppProperties props) {
        this.props = props;
        this.key = Keys.hmacShaKeyFor(props.security().jwt().secret().getBytes(StandardCharsets.UTF_8));
    }

    public String issueAccessToken(UUID userId, String email, String displayName, List<String> roles) {
        Instant now = Instant.now();
        Instant exp = now.plus(props.security().jwt().accessTokenTtl());

        return Jwts.builder().issuer(props.security().jwt().issuer()).subject(email).claim("uid", userId.toString()).claim("name", displayName).claim("roles", roles).issuedAt(Date.from(now)).expiration(Date.from(exp)).signWith(key).compact();
    }

    public Authentication parseAuthentication(String token) {
        Claims claims = Jwts.parser().verifyWith(key).requireIssuer(props.security().jwt().issuer()).build().parseSignedClaims(token).getPayload();

        String email = claims.getSubject();
        String uidStr = claims.get("uid", String.class);
        String name = claims.get("name", String.class);

        @SuppressWarnings("unchecked") List<String> roles = claims.get("roles", List.class);

        var authorities = roles == null ? List.<SimpleGrantedAuthority>of() : roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r)).toList();

        var principal = new UserPrincipal(
                uidStr != null ? UUID.fromString(uidStr) : null,
                email,
                name != null ? name : email,
                roles != null ? roles : List.of()
        );
        // principal можно сделать кастомным, но для старта норм:
        return new UsernamePasswordAuthenticationToken(principal, null, authorities);
    }
}
