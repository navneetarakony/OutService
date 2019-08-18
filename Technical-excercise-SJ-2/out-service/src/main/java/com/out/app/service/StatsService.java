package com.out.app.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.out.app.repository.StreamRecordsRepository;

/**
 * Processes Statistics for the stream data.
 */
@Service
public class StatsService {

    @Autowired
    private StreamRecordsRepository streamRecordsRepository;

    public int getViewerCountForStream(UUID streamId) {
        return streamRecordsRepository
                .countByStreamIdAndLastUpdatedAtAfter(streamId, Instant.now().minus(5, ChronoUnit.MINUTES));
    }

    public int getViewerCountByLocation(String state, String country) {
        // TODO validate state and country .
        return streamRecordsRepository
                .countByStateAndCountryAndLastUpdatedAtAfter(state, country,
                                                             Instant.now().minus(5, ChronoUnit.MINUTES));
    }

    public int getViewersAcrossAllStreamsCount() {
        return  streamRecordsRepository.countByLastUpdatedAtAfter(Instant.now().minus(5, ChronoUnit.MINUTES));
    }

    public int getStreamCountForUser(UUID userId) {
        return streamRecordsRepository
                .countByUserIdAndLastUpdatedAtAfter(userId, Instant.now().minus(5, ChronoUnit.MINUTES));
    }
}
