package com.belmontiere.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.Instant;

public record CreateReferencePriceRequest(
        @NotNull Long watchId,
        @NotBlank String sourceName,
        @NotBlank String sourceUrl,
        @NotNull @Positive BigDecimal price,
        @NotBlank String currency,
        @NotNull Instant capturedAt
) {
}
