package com.belmontiere.dto.response;

import com.belmontiere.entity.enums.Condition;
import com.belmontiere.entity.enums.ListingStatus;

import java.math.BigDecimal;
import java.time.Instant;

public record ListingSummaryResponse(
        Long id,
        String title,
        BigDecimal price,
        String currency,
        Condition condition,
        String locationCity,
        ListingStatus status,
        String primaryImageUrl,
        WatchSummaryResponse watch,
        Instant createdAt
) {
}
