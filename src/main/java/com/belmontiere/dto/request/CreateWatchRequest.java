package com.belmontiere.dto.request;

import com.belmontiere.entity.enums.CaseMaterial;
import com.belmontiere.entity.enums.Movement;
import com.belmontiere.entity.enums.StrapType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateWatchRequest(
        @NotBlank String brand,
        @NotBlank String model,
        String referenceNumber,
        Movement movement,
        @Positive Double caseDiameterMm,
        @Positive Double caseThicknessMm,
        CaseMaterial caseMaterial,
        StrapType strapType,
        String strapMaterial,
        @Positive Integer waterResistanceM,
        String dialColor,
        String complications,
        @Positive BigDecimal msrp,
        String currency
) {
}
