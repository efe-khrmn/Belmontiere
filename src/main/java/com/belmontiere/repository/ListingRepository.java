package com.belmontiere.repository;

import com.belmontiere.entity.Listing;
import com.belmontiere.entity.enums.ListingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ListingRepository extends JpaRepository<Listing, Long>, JpaSpecificationExecutor<Listing> {

    Page<Listing> findBySellerIdAndStatus(Long sellerId, ListingStatus status, Pageable pageable);

    Page<Listing> findByWatchIdAndStatus(Long watchId, ListingStatus status, Pageable pageable);
}
