package com.belmontiere.repository;

import com.belmontiere.entity.ReferencePrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReferencePriceRepository extends JpaRepository<ReferencePrice, Long> {

    List<ReferencePrice> findByWatchIdAndActiveTrue(Long watchId);

    Optional<ReferencePrice> findByWatchIdAndSourceNameAndActiveTrue(Long watchId, String sourceName);
}
