package com.belmontiere.controller;

import com.belmontiere.dto.request.CreateListingRequest;
import com.belmontiere.dto.response.ListingResponse;
import com.belmontiere.dto.response.ListingSummaryResponse;
import com.belmontiere.service.ListingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/listings")
@RequiredArgsConstructor
public class ListingController {

    private final ListingService listingService;

    @PostMapping
    public ResponseEntity<ListingResponse> create(
            @Valid @RequestBody CreateListingRequest request,
            @RequestParam Long sellerId) {
        ListingResponse created = listingService.create(request, sellerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListingResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(listingService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ListingSummaryResponse>> browse(
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(listingService.browseActive(pageable));
    }
}