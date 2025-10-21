package app.e_market_services.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {
    public String accessToken;
    public String refreshToken;
}
