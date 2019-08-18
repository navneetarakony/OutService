package com.out.app.service;

import java.time.Instant;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.out.app.entity.StreamRecordEntity;
import com.out.app.model.StreamData;
import com.out.app.repository.StreamRecordsRepository;

/**
 * Service for persisting/processing the client stream data.
 */
@Service
public class StreamRecordService {

    @Autowired
    private StreamRecordsRepository streamRecordsRepository;
    private Logger logger = LoggerFactory.getLogger(StreamRecordService.class);

    /**
     * Records the incoming stream data into the DB. Note: This currently only updates the timestamp for already
     * existing records so as to reduce the number of duplicate entries.
     * @param streamData Stream data object.
     */
    public void recordStreamData(StreamData streamData) {
        StreamRecordEntity entity = convertToEntity(streamData);
        Page<StreamRecordEntity> page = findEntryForRecord(streamData);
        if (page.isEmpty()) {
            streamRecordsRepository.save(entity);
            logger.info("Created entry for streamData {}.", streamData);
        } else {
            StreamRecordEntity toUpdate = page.getContent().get(0);
            toUpdate.setLastUpdateTime(Instant.now());
            streamRecordsRepository.save(toUpdate);
            logger.info("Updated entry for streamData {}", streamData);
        }
    }

    private StreamRecordEntity convertToEntity(StreamData streamData) {
        return StreamRecordEntity.builder().id(UUID.randomUUID()).userId(streamData.getUserId())
                .deviceId(streamData.getDeviceId()).streamId(streamData.getStreamId()).zipCode(streamData.getState())
                .lastUpdateTime(Instant.now()).createdAt(Instant.now()).build();

    }

    private Page<StreamRecordEntity> findEntryForRecord(StreamData streamData) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "createdAt"));
        Pageable pageable = PageRequest.of(0, 1, sort);
        return streamRecordsRepository
                .findByUserIdAndDeviceIdAndStreamId(streamData.getUserId(), streamData.getDeviceId(),
                                                    streamData.getStreamId(), pageable);
    }
}
