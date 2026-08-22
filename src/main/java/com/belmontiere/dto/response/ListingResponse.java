package com.belmontiere.dto.response;

import com.belmontiere.entity.enums.Condition;
import com.belmontiere.entity.enums.ListingStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;


public record ListingResponse(
        Long id,
        UserSummaryResponse seller,
        WatchResponse watch,
        String title,
        String description,
        BigDecimal price,
        String currency,
        Condition condition,
        Integer productionYear,
        boolean boxIncluded,
        boolean papersIncluded,
        Integer warrantyRemainingMonths,
        String locationCity,
        String locationCountry,
        ListingStatus status,
        long viewCount,
        List<ListingImageResponse> images,
        Instant createdAt,
        Instant updatedAt
) {
}
