package app.e_market_services.products.dto.response;

import app.e_market_services.base.Model.Base;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDetails extends Base {
    private String productId;
    private String productName;
    private String description;
    private BigDecimal price;
    private int stockQuantity;
}
