package com.belmontiere.service;

import com.belmontiere.dto.mapper.ReferencePriceMapper;
import com.belmontiere.dto.request.CreateReferencePriceRequest;
import com.belmontiere.dto.response.ReferencePriceResponse;
import com.belmontiere.entity.ReferencePrice;
import com.belmontiere.entity.Watch;
import com.belmontiere.exception.ResourceNotFoundException;
import com.belmontiere.repository.ReferencePriceRepository;
import com.belmontiere.repository.WatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReferencePriceService {

    private final ReferencePriceRepository referencePriceRepository;
    private final WatchRepository watchRepository;

    @Transactional
    public ReferencePriceResponse addManualPrice(CreateReferencePriceRequest request) {
        Watch watch = watchRepository.findById(request.watchId())
                .orElseThrow(() -> new ResourceNotFoundException("Watch not found: " + request.watchId()));

        Optional<ReferencePrice> currentActive = referencePriceRepository
                .findByWatchIdAndSourceNameAndActiveTrue(request.watchId(), request.sourceName());
        currentActive.ifPresent(old -> {
            old.setActive(false);
            referencePriceRepository.save(old);
        });

        ReferencePrice newPrice = ReferencePrice.builder()
                .watch(watch)
                .sourceName(request.sourceName())
                .sourceUrl(request.sourceUrl())
                .price(request.price())
                .currency(request.currency())
                .capturedAt(request.capturedAt())
                .active(true)
                .entryMethod("MANUAL")
                .build();

        ReferencePrice saved = referencePriceRepository.save(newPrice);
        return ReferencePriceMapper.toResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<ReferencePriceResponse> getActiveForWatch(Long watchId) {
        if (!watchRepository.existsById(watchId)) {
            throw new ResourceNotFoundException("Watch not found: " + watchId);
        }
        return referencePriceRepository.findByWatchIdAndActiveTrue(watchId).stream()
                .map(ReferencePriceMapper::toResponse)
                .toList();
    }
}