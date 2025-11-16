package app.e_market_services.products.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductRequest {
    private String description;
    private BigDecimal price;
    private String productName;
    private int stockQuantity;
}
