package app.e_market_services.categories.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private String categoryId;
    private String categoryName;
}