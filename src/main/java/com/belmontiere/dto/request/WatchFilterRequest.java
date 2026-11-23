package com.belmontiere.dto.request;

import com.belmontiere.entity.enums.CrystalType;
import com.belmontiere.entity.enums.Movement;

import java.math.BigDecimal;
import java.util.List;

public record WatchFilterRequest(
        String brand,
        String model,
        List<Movement> movements,
        List<CrystalType> crystalTypes,
        Double minCaseDiameter,
        Double maxCaseDiameter,
        Double minLugToLug,
        Double maxLugToLug,
        BigDecimal minPrice,
        BigDecimal maxPrice
) {
}