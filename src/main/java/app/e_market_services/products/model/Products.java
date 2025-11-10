package app.e_market_services.products.model;

import app.e_market_services.base.Model.BaseEntity;
import app.e_market_services.categories.model.Category;
import app.e_market_services.mechants.model.Merchants;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.*;

@Entity
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "products")
public class Products extends BaseEntity {
    @Id
    @UuidGenerator
    @Column(name = "product_id")
    private String productId;
    private String productName;
    private String description;
    private BigDecimal price;
    private int stockQuantity;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private List<Category> categories = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant_id")
    private Merchants merchants;
}
