package app.e_market_services.categories.model;

import app.e_market_services.base.Model.BaseEntity;
import app.e_market_services.products.model.Products;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Table(name = "categories")
public class Category extends BaseEntity {

    @Id
    @UuidGenerator
    @Column(name = "category_id")
    public String categoryId;
    public String categoryName;
    public String description;

    @ManyToMany(mappedBy = "categories")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private List<Products> products = new ArrayList<>();
}
