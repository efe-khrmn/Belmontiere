package com.belmontiere.repository.specification;

import com.belmontiere.dto.request.WatchFilterRequest;
import com.belmontiere.entity.Watch;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class WatchSpecification {

    private WatchSpecification() {
        // Utility class
    }

    public static Specification<Watch> withFilter(WatchFilterRequest filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.brand() != null && !filter.brand().isBlank()) {
                predicates.add(cb.equal(cb.lower(root.get("brand")), filter.brand().toLowerCase()));
            }

            if (filter.model() != null && !filter.model().isBlank()) {
                // LIKE araması: "Date" yazarsa "Datejust" veya "Day-Date" bulsun
                predicates.add(cb.like(cb.lower(root.get("model")), "%" + filter.model().toLowerCase() + "%"));
            }

            if (filter.movements() != null && !filter.movements().isEmpty()) {
                predicates.add(root.get("movement").in(filter.movements()));
            }

            if (filter.crystalTypes() != null && !filter.crystalTypes().isEmpty()) {
                predicates.add(root.get("crystalType").in(filter.crystalTypes()));
            }

            if (filter.minCaseDiameter() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("caseDiameterMm"), filter.minCaseDiameter()));
            }
            if (filter.maxCaseDiameter() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("caseDiameterMm"), filter.maxCaseDiameter()));
            }

            if (filter.minLugToLug() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("lugToLugMm"), filter.minLugToLug()));
            }
            if (filter.maxLugToLug() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("lugToLugMm"), filter.maxLugToLug()));
            }

            if (filter.minPrice() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("msrp"), filter.minPrice()));
            }
            if (filter.maxPrice() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("msrp"), filter.maxPrice()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}