package com.belmontiere.dto.mapper;

import com.belmontiere.dto.response.WatchResponse;
import com.belmontiere.dto.response.WatchSummaryResponse;
import com.belmontiere.entity.Watch;

import java.util.List;

public final class WatchMapper {

    private WatchMapper() {
    }

    public static WatchSummaryResponse toSummary(Watch watch) {
        if (watch == null) {
            return null;
        }
        return new WatchSummaryResponse(
                watch.getId(),
                watch.getBrand(),
                watch.getModel(),
                watch.getReferenceNumber(),
                watch.getMovement(),
                watch.getCaseDiameterMm()
        );
    }

    public static WatchResponse toResponse(Watch watch) {
        if (watch == null) {
            return null;
        }
        List<com.belmontiere.dto.response.ReferencePriceResponse> prices = watch.getReferencePrices() == null
                ? List.of()
                : watch.getReferencePrices().stream()
                        .filter(com.belmontiere.entity.ReferencePrice::isActive)
                        .map(ReferencePriceMapper::toResponse)
                        .toList();

        return new WatchResponse(
                watch.getId(),
                watch.getBrand(),
                watch.getModel(),
                watch.getReferenceNumber(),
                watch.getMovement(),
                watch.getCaseDiameterMm(),
                watch.getCaseThicknessMm(),
                watch.getCaseMaterial(),
                watch.getStrapType(),
                watch.getStrapMaterial(),
                watch.getWaterResistanceM(),
                watch.getDialColor(),
                watch.getComplications(),
                watch.getMsrp(),
                watch.getCurrency(),
                prices
        );
    }
}
