package com.belmontiere.dto.response;

import java.math.BigDecimal;
import java.time.Instant;

public record ReferencePriceResponse(
        Long id,
        String sourceName,
        String sourceUrl,
        BigDecimal price,
        String currency,
        Instant capturedAt
) {
}
