package app.e_market_services.products.dto;

import app.e_market_services.categories.dto.Category;
import app.e_market_services.categories.model.Categories;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductsResponse {
    private String productId;
    private String productName;
    private String description;
    private BigDecimal price;
    private String merchantName;
    private Set<Category> categories;
}