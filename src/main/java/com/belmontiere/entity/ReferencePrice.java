package com.belmontiere.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;


@Entity
@Table(name = "reference_prices", indexes = {
        @Index(name = "idx_reference_prices_watch", columnList = "watch_id"),
        @Index(name = "idx_reference_prices_active", columnList = "watch_id, source_name, active")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReferencePrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "watch_id", nullable = false)
    private Watch watch;

    @Column(name = "source_name", nullable = false, length = 150)
    private String sourceName;

    @Column(name = "source_url", nullable = false, length = 500)
    private String sourceUrl;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Column(nullable = false, length = 3)
    private String currency;

    @Column(name = "captured_at", nullable = false)
    private Instant capturedAt;

    @Column(nullable = false)
    @Builder.Default
    private boolean active = true;

    /* for manually entered data */
    @Column(name = "entry_method", nullable = false, length = 20)
    @Builder.Default
    private String entryMethod = "MANUAL";

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
}
