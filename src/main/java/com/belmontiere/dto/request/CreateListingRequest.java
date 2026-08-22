package com.belmontiere.dto.request;

import com.belmontiere.entity.enums.Condition;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public record CreateListingRequest(
        Long watchId,
        @NotBlank @Size(max = 150) String title,
        @NotBlank String description,
        @NotNull @Positive BigDecimal price,
        @NotBlank String currency,
        @NotNull Condition condition,
        Integer productionYear,
        boolean boxIncluded,
        boolean papersIncluded,
        Integer warrantyRemainingMonths,
        String locationCity,
        String locationCountry,
        List<String> imageUrls
) {
}
