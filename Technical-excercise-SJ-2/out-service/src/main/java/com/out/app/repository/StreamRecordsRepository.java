package com.out.app.repository;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.out.app.entity.StreamRecordEntity;

/**
 * Interface for persisting stream records into the DB.
 */
@Repository
public interface StreamRecordsRepository extends PagingAndSortingRepository<StreamRecordEntity, UUID> {

    Page<StreamRecordEntity> findByUserIdAndDeviceIdAndStreamIdAndStateAndCountry(UUID userId, UUID deviceId,
                                                                                  UUID streamId, String state,
                                                                                  String country, Pageable pageable);

    int countByStreamIdAndLastUpdatedAtAfter(UUID streamId, Instant instant);

    int countByStateAndCountryAndLastUpdatedAtAfter(String state, String country, Instant instant);

    int countByUserIdAndLastUpdatedAtAfter(UUID userId, Instant instant);

    int countByLastUpdatedAtAfter(Instant instant);

    int deleteByLastUpdatedAtBefore(Instant instant);

}
