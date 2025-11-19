package app.e_market_services.users.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponseDto {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
}
