package com.out.app.service;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.out.app.model.StreamData;
import com.out.app.repository.StreamRecordsRepository;

/**
 * Unit tests for {@link StatsService}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class StatsServiceTest {

    @Autowired
    private StatsService statsService;

    @Autowired
    private StreamRecordsRepository streamRecordsRepository;

    @Autowired
    private StreamRecordService streamRecordService;

    @Test
    public void testGetViewerCountForStream() {
        StreamData streamData1 = generateStreamData();
        streamRecordService.recordStreamData(streamData1);
        // Modify stream data to update new user id.
        streamData1.setUserId(UUID.randomUUID());
        streamRecordService.recordStreamData(streamData1);
        Assert.assertEquals(2, statsService.getViewerCountForStream(streamData1.getStreamId()));
    }

    @Test
    public void testGetViewerCountByLocation() {
        // generate 2 stream data objects with same location (State and country).
        StreamData streamData1 = generateStreamData();
        StreamData streamData2 = generateStreamData();
        streamRecordService.recordStreamData(streamData1);
        streamRecordService.recordStreamData(streamData2);
        Assert.assertEquals(2, statsService.getViewerCountByLocation(streamData1.getState(), streamData1.getCountry()));
    }

    @Test
    public void testGetViewerCountAcrossAllStreams() {
        streamRecordService.recordStreamData(generateStreamData());
        streamRecordService.recordStreamData(generateStreamData());
        streamRecordService.recordStreamData(generateStreamData());
        Assert.assertEquals(3, statsService.getViewersAcrossAllStreamsCount());
    }

    @Test
    public void testGetStreamCountForUser() {
        StreamData streamData = generateStreamData();
        streamRecordService.recordStreamData(streamData);
        streamData.setDeviceId(UUID.randomUUID());
        streamRecordService.recordStreamData(streamData);
        streamData.setDeviceId(UUID.randomUUID());
        streamRecordService.recordStreamData(streamData);
        Assert.assertEquals(3, statsService.getStreamCountForUser(streamData.getUserId()));
    }


    private StreamData generateStreamData() {
        StreamData streamData = new StreamData();
        streamData.setUserId(UUID.randomUUID());
        streamData.setDeviceId(UUID.randomUUID());
        streamData.setStreamId(UUID.randomUUID());
        streamData.setState("CA");
        streamData.setCountry("USA");
        return streamData;
    }
}
