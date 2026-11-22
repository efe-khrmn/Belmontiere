package com.belmontiere.dto.response;

import com.belmontiere.entity.enums.CaseMaterial;
import com.belmontiere.entity.enums.CrystalType;
import com.belmontiere.entity.enums.Movement;
import com.belmontiere.entity.enums.StrapType;

import java.math.BigDecimal;
import java.util.List;

public record WatchResponse(
        Long id,
        String brand,
        String model,
        String referenceNumber,
        Movement movement,
        String caliber,
        Double caseDiameterMm,
        Double caseThicknessMm,
        Double lugToLugMm,
        Integer lugWidthMm,
        CaseMaterial caseMaterial,
        CrystalType crystalType,
        StrapType strapType,
        String strapMaterial,
        Integer waterResistanceM,
        Integer powerReserveHours,
        String dialColor,
        String dialFeatures,
        String complications,
        BigDecimal msrp,
        String currency,
        List<ReferencePriceResponse> referencePrices
) {
}