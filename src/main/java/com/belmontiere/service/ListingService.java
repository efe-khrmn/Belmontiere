package com.belmontiere.service;

import com.belmontiere.dto.mapper.ListingMapper;
import com.belmontiere.dto.request.CreateListingRequest;
import com.belmontiere.dto.response.ListingResponse;
import com.belmontiere.dto.response.ListingSummaryResponse;
import com.belmontiere.entity.Listing;
import com.belmontiere.entity.ListingImage;
import com.belmontiere.entity.User;
import com.belmontiere.entity.Watch;
import com.belmontiere.entity.enums.ListingStatus;
import com.belmontiere.exception.ResourceNotFoundException;
import com.belmontiere.repository.ListingRepository;
import com.belmontiere.repository.UserRepository;
import com.belmontiere.repository.WatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListingService {

    private final ListingRepository listingRepository;
    private final UserRepository userRepository;
    private final WatchRepository watchRepository;

    @Transactional
    public ListingResponse create(CreateListingRequest request, Long sellerId) {
        User seller = userRepository.findById(sellerId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + sellerId));

        Watch watch = null;
        if (request.watchId() != null) {
            watch = watchRepository.findById(request.watchId())
                    .orElseThrow(() -> new ResourceNotFoundException("Watch not found: " + request.watchId()));
        }

        Listing listing = Listing.builder()
                .seller(seller)
                .watch(watch)
                .title(request.title())
                .description(request.description())
                .price(request.price())
                .currency(request.currency())
                .condition(request.condition())
                .productionYear(request.productionYear())
                .boxIncluded(request.boxIncluded())
                .papersIncluded(request.papersIncluded())
                .warrantyRemainingMonths(request.warrantyRemainingMonths())
                .locationCity(request.locationCity())
                .locationCountry(request.locationCountry())
                .status(ListingStatus.ACTIVE)
                .build();

        List<ListingImage> images = new ArrayList<>();
        if (request.imageUrls() != null) {
            for (int i = 0; i < request.imageUrls().size(); i++) {
                images.add(ListingImage.builder()
                        .listing(listing)
                        .imageUrl(request.imageUrls().get(i))
                        .sortOrder(i)
                        .build());
            }
        }
        listing.setImages(images);

        Listing saved = listingRepository.save(listing);
        return ListingMapper.toResponse(saved);
    }

    @Transactional(readOnly = true)
    public ListingResponse getById(Long id) {
        Listing listing = listingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Listing not found: " + id));
        return ListingMapper.toResponse(listing);
    }

    @Transactional(readOnly = true)
    public Page<ListingSummaryResponse> browseActive(Pageable pageable) {
        return listingRepository.findByStatus(ListingStatus.ACTIVE, pageable)
                .map(ListingMapper::toSummary);
    }
}