package app.e_market_services.jwt.service;

import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

    @Value("${jwt.private-key-path}")
    private Resource privateKeyResource;

    @Value("${jwt.public-key-path}")
    private Resource publicKeyResource;

    @Value("${jwt.expiration-minutes}")
    private long expirationMinutes;

    @Value("${jwt.refresh.expiration-minutes}")
    private long refreshExpirationMinutes;

    private PrivateKey privateKey;
    private PublicKey publicKey;

    private PrivateKey getPrivateKey() throws Exception {
        if (privateKey == null) {
            byte[] keyBytes = Files.readAllBytes(privateKeyResource.getFile().toPath());
            String keyString = new String(keyBytes)
                    .replaceAll("-----BEGIN (.*)-----", "")
                    .replaceAll("-----END (.*)----", "")
                    .replaceAll("\\s", "");
            byte[] decoded = java.util.Base64.getDecoder().decode(keyString);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);
            privateKey = KeyFactory.getInstance("RSA").generatePrivate(keySpec);
        }
        return privateKey;
    }

    private PublicKey getPublicKey() throws Exception {
        if (publicKey == null) {
            byte[] keyBytes = Files.readAllBytes(publicKeyResource.getFile().toPath());
            String keyString = new String(keyBytes)
                    .replaceAll("-----BEGIN (.*)-----", "")
                    .replaceAll("-----END (.*)----", "")
                    .replaceAll("\\s", "");
            byte[] decoded = java.util.Base64.getDecoder().decode(keyString);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);
            publicKey = KeyFactory.getInstance("RSA").generatePublic(keySpec);
        }
        return publicKey;
    }

    public String generateToken(String subject, Map<String, Object> claims) {
        try {
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(subject)
                    .setIssuedAt(new Date())
                    .setExpiration(Date.from(Instant.now().plusSeconds(expirationMinutes * 60)))
                    .signWith(getPrivateKey(), SignatureAlgorithm.RS256)
                    .compact();
        } catch (Exception e) {
            throw new RuntimeException("Error generating JWT", e);
        }
    }

    public String generateRefreshToken(String subject, Map<String, Object> claims) {
        try {
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(subject)
                    .setIssuedAt(new Date())
                    .setExpiration(Date.from(Instant.now().plusSeconds(refreshExpirationMinutes * 60)))
                    .signWith(getPrivateKey(), SignatureAlgorithm.RS256)
                    .compact();
        } catch (Exception e) {
            throw new RuntimeException("Error generating JWT", e);
        }
    }

    public Jws<Claims> validateToken(String token) {
        try {
            System.out.println(token);
            return Jwts.parserBuilder()
                    .setSigningKey(getPublicKey())
                    .build()
                    .parseClaimsJws(token);
        } catch (JwtException e) {
            throw new RuntimeException("Invalid JWT", e);
        } catch (Exception e) {
            throw new RuntimeException("Error validating JWT", e);
        }
    }

    public boolean isTokenValid(String token) throws IllegalArgumentException, Exception {
        try {
            Jwts.parserBuilder().setSigningKey(getPublicKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public String extractUsername(String token) throws Exception {
        return Jwts.parserBuilder().setSigningKey(getPublicKey()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public List<String> extractRoles(String token) throws Exception {
        return Jwts.parserBuilder().setSigningKey(getPublicKey()).build()
                .parseClaimsJws(token).getBody().get("roles", List.class);
    }
}

