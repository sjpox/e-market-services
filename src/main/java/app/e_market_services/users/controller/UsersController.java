package app.e_market_services.users.controller;

import java.util.List;
import java.util.Map;

import app.e_market_services.users.dto.TokenResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.e_market_services.common.ApiException;
import app.e_market_services.common.constant.HttpStatusDesc;
import app.e_market_services.common.response.ApiResponse;
import app.e_market_services.jwt.service.JwtService;
import app.e_market_services.users.dto.UserRequestDto;
import app.e_market_services.users.dto.UserResponseDto;
import app.e_market_services.users.service.UserService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);
    private final UserService userService;
    private final JwtService jwtService;

    public UsersController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> getAllUsers() {
        return ResponseEntity
                .ok()
                .body(ApiResponse.<List<UserResponseDto>>builder()
                        .status(HttpStatusDesc.SUCCESS)
                        .result(userService.findAll()).build());

    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponseDto>> createUser(@RequestBody UserRequestDto user) {
        return ResponseEntity.ok()
                .body(ApiResponse.<UserResponseDto>builder().status(HttpStatusDesc.SUCCESS)
                        .result(userService.createUser(user)).build());
    }

    // TODO: UserTokenModel should be replace with DTO
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponse>> loginUser(@RequestBody UserRequestDto user,
            HttpServletResponse response) throws JsonProcessingException {
        log.info("User {}", new ObjectMapper().writeValueAsString(user));
        Boolean isValid = userService.verifyUser(user);
        if (!isValid) {
            throw new ApiException(HttpStatus.BAD_REQUEST.value(), "Invalid input data");
        }

        Map<String, Object> claims = Map.of("roles", List.of("ROLE_ADMIN"));

    
        String accessToken = jwtService.generateToken(user.email, claims);
        String refreshToken = jwtService.generateRefreshToken(user.email, claims);

        // Create secure, HTTP-only cookies
        ResponseCookie accessCookie = ResponseCookie.from("access_token", accessToken)
                .httpOnly(true)
                .secure(true)
                .sameSite("Strict")
                .path("/")
                .maxAge(15 * 60) // 15 minutes
                .build();

        ResponseCookie refreshCookie = ResponseCookie.from("refresh_token", refreshToken)
                .httpOnly(true)
                .secure(true)
                .sameSite("Strict")
                .path("/")
                .maxAge(7 * 24 * 60 * 60) // 7 days
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, accessCookie.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());

        return ResponseEntity.ok()
                .body(ApiResponse.<TokenResponse>builder().status(HttpStatusDesc.SUCCESS)
                        .result(
                                TokenResponse.builder()
                                        .accessToken(accessToken)
                                        .refreshToken(refreshToken).build())
                        .build());
    }

}
