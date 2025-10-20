package app.e_market_services.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserTokenModel {
    public String accessToken;
    public String refreshToken;
}
