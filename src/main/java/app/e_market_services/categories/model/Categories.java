package app.e_market_services.categories.model;

import app.e_market_services.products.model.Products;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Categories {

    @Id
    @UuidGenerator
    @Column(name = "category_id")
    public String categoryId;
    public String categoryName;
    public String description;

    @ManyToMany(mappedBy = "categories")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Products> products = new HashSet<>();
}
