package com.belmontiere.dto.mapper;

import com.belmontiere.dto.response.ListingImageResponse;
import com.belmontiere.dto.response.ListingResponse;
import com.belmontiere.dto.response.ListingSummaryResponse;
import com.belmontiere.entity.Listing;
import com.belmontiere.entity.ListingImage;

import java.util.Comparator;
import java.util.List;

public final class ListingMapper {

    private ListingMapper() {
    }

    public static ListingSummaryResponse toSummary(Listing listing) {
        if (listing == null) {
            return null;
        }
        String primaryImageUrl = listing.getImages() == null || listing.getImages().isEmpty()
                ? null
                : listing.getImages().stream()
                        .min(Comparator.comparingInt(ListingImage::getSortOrder))
                        .map(ListingImage::getImageUrl)
                        .orElse(null);

        return new ListingSummaryResponse(
                listing.getId(),
                listing.getTitle(),
                listing.getPrice(),
                listing.getCurrency(),
                listing.getCondition(),
                listing.getLocationCity(),
                listing.getStatus(),
                primaryImageUrl,
                WatchMapper.toSummary(listing.getWatch()),
                listing.getCreatedAt()
        );
    }

    public static ListingResponse toResponse(Listing listing) {
        if (listing == null) {
            return null;
        }
        List<ListingImageResponse> images = listing.getImages() == null
                ? List.of()
                : listing.getImages().stream()
                        .sorted(Comparator.comparingInt(ListingImage::getSortOrder))
                        .map(img -> new ListingImageResponse(img.getId(), img.getImageUrl(), img.getSortOrder()))
                        .toList();

        return new ListingResponse(
                listing.getId(),
                UserMapper.toSummary(listing.getSeller()),
                WatchMapper.toResponse(listing.getWatch()),
                listing.getTitle(),
                listing.getDescription(),
                listing.getPrice(),
                listing.getCurrency(),
                listing.getCondition(),
                listing.getProductionYear(),
                listing.isBoxIncluded(),
                listing.isPapersIncluded(),
                listing.getWarrantyRemainingMonths(),
                listing.getLocationCity(),
                listing.getLocationCountry(),
                listing.getStatus(),
                listing.getViewCount(),
                images,
                listing.getCreatedAt(),
                listing.getUpdatedAt()
        );
    }
}
