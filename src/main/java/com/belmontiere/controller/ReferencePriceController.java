package com.belmontiere.controller;

import com.belmontiere.dto.request.CreateReferencePriceRequest;
import com.belmontiere.dto.response.ReferencePriceResponse;
import com.belmontiere.service.ReferencePriceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/watches/{watchId}/reference-prices")
@RequiredArgsConstructor
public class ReferencePriceController {

    private final ReferencePriceService referencePriceService;

    @PostMapping
    public ResponseEntity<ReferencePriceResponse> add(
            @PathVariable Long watchId,
            @Valid @RequestBody CreateReferencePriceRequest request) {
        if (!watchId.equals(request.watchId())) {
            return ResponseEntity.badRequest().build();
        }
        ReferencePriceResponse created = referencePriceService.addManualPrice(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<ReferencePriceResponse>> listActive(@PathVariable Long watchId) {
        return ResponseEntity.ok(referencePriceService.getActiveForWatch(watchId));
    }
}