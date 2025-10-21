package app.e_market_services.mechants.model;

import app.e_market_services.products.model.Products;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "merchants")
public class Merchants {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID merchant_id;

    private String merchant_name;
    private String email;
    private String contact_number;
    private String address;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    @OneToMany(mappedBy = "merchant_id", cascade = CascadeType.ALL)
    private Set<Products> products = new HashSet<>();

    @PrePersist
    public void prePersist() {
        created_at = LocalDateTime.now();
        updated_at = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updated_at = LocalDateTime.now();
    }
}
