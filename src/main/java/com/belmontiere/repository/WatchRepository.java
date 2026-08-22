package com.belmontiere.repository;

import com.belmontiere.entity.Watch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface WatchRepository extends JpaRepository<Watch, Long>, JpaSpecificationExecutor<Watch> {

    List<Watch> findByBrandIgnoreCaseAndModelIgnoreCase(String brand, String model);

    Optional<Watch> findByReferenceNumberIgnoreCase(String referenceNumber);
}
