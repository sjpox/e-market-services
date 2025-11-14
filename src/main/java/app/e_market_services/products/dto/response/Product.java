package app.e_market_services.products.dto.response;

import app.e_market_services.categories.dto.CategoryDto;
import app.e_market_services.categories.dto.response.CategoryResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    private String productId;
    private String productName;
    private String description;
    private BigDecimal price;
    private String merchantName;
    private Set<CategoryDto> categories;
}