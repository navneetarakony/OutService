package com.out.app.repository;

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

    Page<StreamRecordEntity> findByUserIdAndDeviceIdAndStreamId(UUID userId, UUID deviceId, UUID streamId,
                                                                Pageable pageable);
}
