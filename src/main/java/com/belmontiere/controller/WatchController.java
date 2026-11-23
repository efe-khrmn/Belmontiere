package com.belmontiere.controller;

import com.belmontiere.dto.request.CreateWatchRequest;
import com.belmontiere.dto.request.WatchFilterRequest;
import com.belmontiere.dto.response.WatchResponse;
import com.belmontiere.dto.response.WatchSummaryResponse;
import com.belmontiere.service.WatchService;
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
@RequestMapping("/api/watches")
@RequiredArgsConstructor
public class WatchController {

    private final WatchService watchService;

    @PostMapping
    public ResponseEntity<WatchResponse> create(@Valid @RequestBody CreateWatchRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(watchService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WatchResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(watchService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<WatchSummaryResponse>> list(
            @PageableDefault(size = 20, sort = "brand", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(watchService.list(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<WatchSummaryResponse>> search(
            WatchFilterRequest filterRequest,
            @PageableDefault(size = 20, sort = "brand", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(watchService.search(filterRequest, pageable));
    }
}