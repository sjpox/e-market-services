package app.e_market_services.jwt.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.e_market_services.jwt.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@RestController
@RequestMapping("/api/v1/jwt")
public class JwtController {

    private final JwtService jwtService;

    public JwtController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/generate")
    public String generateToken(@RequestParam String user) {
        return jwtService.generateToken(user, Map.of("role", "USER"));
    }

    @PostMapping("/validate")
    public Jws<Claims> validateToken(@RequestBody Map<String, String> body) {
        String token = body.get("token");
        Jws<Claims> jws = jwtService.validateToken(token);
        return jws;
    }
}
