package com.belmontiere.repository;

import com.belmontiere.entity.ListingImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListingImageRepository extends JpaRepository<ListingImage, Long> {

    List<ListingImage> findByListingIdOrderBySortOrderAsc(Long listingId);
}
