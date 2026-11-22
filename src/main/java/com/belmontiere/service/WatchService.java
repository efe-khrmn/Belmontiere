package com.belmontiere.service;

import com.belmontiere.dto.mapper.WatchMapper;
import com.belmontiere.dto.request.CreateWatchRequest;
import com.belmontiere.dto.response.WatchResponse;
import com.belmontiere.dto.response.WatchSummaryResponse;
import com.belmontiere.entity.Watch;
import com.belmontiere.exception.ResourceNotFoundException;
import com.belmontiere.repository.WatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WatchService {

    private final WatchRepository watchRepository;

    @Transactional
    public WatchResponse create(CreateWatchRequest request) {
        Watch watch = Watch.builder()
                .brand(request.brand())
                .model(request.model())
                .referenceNumber(request.referenceNumber())
                .movement(request.movement())
                .caliber(request.caliber())
                .caseDiameterMm(request.caseDiameterMm())
                .caseThicknessMm(request.caseThicknessMm())
                .lugToLugMm(request.lugToLugMm())
                .lugWidthMm(request.lugWidthMm())
                .caseMaterial(request.caseMaterial())
                .crystalType(request.crystalType())
                .strapType(request.strapType())
                .strapMaterial(request.strapMaterial())
                .waterResistanceM(request.waterResistanceM())
                .powerReserveHours(request.powerReserveHours())
                .dialColor(request.dialColor())
                .dialFeatures(request.dialFeatures())
                .complications(request.complications())
                .msrp(request.msrp())
                .currency(request.currency())
                .build();

        Watch saved = watchRepository.save(watch);
        return WatchMapper.toResponse(saved);
    }

    @Transactional(readOnly = true)
    public WatchResponse getById(Long id) {
        Watch watch = watchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Watch not found: " + id));
        return WatchMapper.toResponse(watch);
    }

    @Transactional(readOnly = true)
    public Page<WatchSummaryResponse> list(Pageable pageable) {
        return watchRepository.findAll(pageable).map(WatchMapper::toSummary);
    }
}