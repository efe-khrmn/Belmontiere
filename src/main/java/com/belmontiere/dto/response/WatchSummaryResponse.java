package com.belmontiere.dto.response;

import com.belmontiere.entity.enums.Movement;

public record WatchSummaryResponse(
        Long id,
        String brand,
        String model,
        String referenceNumber,
        Movement movement,
        Double caseDiameterMm
) {
}
