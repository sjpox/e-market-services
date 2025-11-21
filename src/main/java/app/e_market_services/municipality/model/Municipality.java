package app.e_market_services.municipality.model;

import app.e_market_services.base.Model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import org.hibernate.annotations.UuidGenerator;

@Builder
@Entity
@Table(name = "municipality")
public class Municipality extends BaseEntity {
    @Id
    @UuidGenerator
    @Column(name = "municipality_id")
    private String municipalityId;
    private String municipalityName;
}
