package app.e_market_services.mechants.model;

import app.e_market_services.products.model.Products;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "merchants")
public class Merchants {
    @Id
    @UuidGenerator
    @Column(name = "merchant_id")
    private String merchantId;

    private String merchantName;
    private String email;
    private String contactNumber;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "merchants", fetch = FetchType.LAZY)
    private Set<Products> products = new HashSet<>();

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
