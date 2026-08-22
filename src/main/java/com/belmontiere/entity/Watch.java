package com.belmontiere.entity;

import com.belmontiere.entity.enums.CaseMaterial;
import com.belmontiere.entity.enums.Movement;
import com.belmontiere.entity.enums.StrapType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "watches", indexes = {
        @Index(name = "idx_watches_brand_model", columnList = "brand, model"),
        @Index(name = "idx_watches_reference_number", columnList = "reference_number")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Watch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String brand;

    @Column(nullable = false, length = 150)
    private String model;

    @Column(name = "reference_number", length = 100)
    private String referenceNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private Movement movement;

    @Column(name = "case_diameter_mm")
    private Double caseDiameterMm;

    @Column(name = "case_thickness_mm")
    private Double caseThicknessMm;

    @Enumerated(EnumType.STRING)
    @Column(name = "case_material", length = 30)
    private CaseMaterial caseMaterial;

    @Enumerated(EnumType.STRING)
    @Column(name = "strap_type", length = 30)
    private StrapType strapType;

    @Column(name = "strap_material", length = 100)
    private String strapMaterial;

    @Column(name = "water_resistance_m")
    private Integer waterResistanceM;

    @Column(name = "dial_color", length = 50)
    private String dialColor;

    @Column(length = 255)
    private String complications;

    @Column(name = "msrp", precision = 12, scale = 2)
    private BigDecimal msrp;

    @Column(length = 3)
    private String currency;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @OneToMany(mappedBy = "watch", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ReferencePrice> referencePrices = new ArrayList<>();

    @OneToMany(mappedBy = "watch")
    @Builder.Default
    private List<Listing> listings = new ArrayList<>();
}
