package com.belmontiere.dto.request;

import com.belmontiere.entity.enums.CaseMaterial;
import com.belmontiere.entity.enums.CrystalType;
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
        String caliber,
        @Positive Double caseDiameterMm,
        @Positive Double caseThicknessMm,
        @Positive Double lugToLugMm,
        @Positive Integer lugWidthMm,
        CaseMaterial caseMaterial,
        CrystalType crystalType,
        StrapType strapType,
        String strapMaterial,
        @Positive Integer waterResistanceM,
        @Positive Integer powerReserveHours,
        String dialColor,
        String dialFeatures,
        String complications,
        @Positive BigDecimal msrp,
        String currency
) {
}
