package app.e_market_services.municipality.model;

import app.e_market_services.base.Model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;

@Builder
@Entity
@Table(name = "municipality")
public class Municipality extends BaseEntity {


    private String municipalityId;
}
