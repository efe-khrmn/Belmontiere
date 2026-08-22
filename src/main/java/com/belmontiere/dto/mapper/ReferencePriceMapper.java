package com.belmontiere.dto.mapper;

import com.belmontiere.dto.response.ReferencePriceResponse;
import com.belmontiere.entity.ReferencePrice;

public final class ReferencePriceMapper {

    private ReferencePriceMapper() {
    }

    public static ReferencePriceResponse toResponse(ReferencePrice referencePrice) {
        if (referencePrice == null) {
            return null;
        }
        return new ReferencePriceResponse(
                referencePrice.getId(),
                referencePrice.getSourceName(),
                referencePrice.getSourceUrl(),
                referencePrice.getPrice(),
                referencePrice.getCurrency(),
                referencePrice.getCapturedAt()
        );
    }
}
