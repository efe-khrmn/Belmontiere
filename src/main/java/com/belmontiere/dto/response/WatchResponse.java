package com.belmontiere.dto.response;

import com.belmontiere.entity.enums.CaseMaterial;
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
        Double caseDiameterMm,
        Double caseThicknessMm,
        CaseMaterial caseMaterial,
        StrapType strapType,
        String strapMaterial,
        Integer waterResistanceM,
        String dialColor,
        String complications,
        BigDecimal msrp,
        String currency,
        List<ReferencePriceResponse> referencePrices
) {
}
