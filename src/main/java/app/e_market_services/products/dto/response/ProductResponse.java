package app.e_market_services.products.dto.response;

import app.e_market_services.base.Model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductResponse extends BaseEntity {
    private String productId;
    private String productName;
    private String description;
    private BigDecimal price;
    private int stockQuantity;
}
