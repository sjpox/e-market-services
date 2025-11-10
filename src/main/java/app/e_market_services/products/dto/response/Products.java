package app.e_market_services.products.dto.response;

import app.e_market_services.categories.dto.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Products {
    private String productId;
    private String productName;
    private String description;
    private BigDecimal price;
    private String merchantName;
    private Set<Category> categories;
}