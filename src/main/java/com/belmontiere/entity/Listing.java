package com.belmontiere.entity;

import com.belmontiere.entity.enums.Condition;
import com.belmontiere.entity.enums.ListingStatus;
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

/**
 * A single user-to-user for-sale post (sahibinden.com-style). The platform
 * never touches money for this: no payment/escrow fields exist here by
 * design. Buyer and seller arrange the deal via messaging or an external
 * platform.
 */
@Entity
@Table(name = "listings", indexes = {
        @Index(name = "idx_listings_status", columnList = "status"),
        @Index(name = "idx_listings_seller", columnList = "seller_id"),
        @Index(name = "idx_listings_watch", columnList = "watch_id"),
        @Index(name = "idx_listings_price", columnList = "price")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "watch_id")
    private Watch watch;

    @Column(nullable = false, length = 150)
    private String title;

    @Lob
    @Column(nullable = false)
    private String description;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Column(nullable = false, length = 3)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Condition condition;

    @Column(name = "production_year")
    private Integer productionYear;

    @Column(name = "box_included", nullable = false)
    @Builder.Default
    private boolean boxIncluded = false;

    @Column(name = "papers_included", nullable = false)
    @Builder.Default
    private boolean papersIncluded = false;

    @Column(name = "warranty_remaining_months")
    private Integer warrantyRemainingMonths;

    @Column(name = "location_city", length = 100)
    private String locationCity;

    @Column(name = "location_country", length = 100)
    private String locationCountry;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private ListingStatus status = ListingStatus.ACTIVE;

    @Column(name = "view_count", nullable = false)
    @Builder.Default
    private long viewCount = 0L;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ListingImage> images = new ArrayList<>();
}
