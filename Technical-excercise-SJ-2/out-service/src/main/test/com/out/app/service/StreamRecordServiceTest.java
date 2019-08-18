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
 * Unit test for {@link StreamRecordService}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class StreamRecordServiceTest {

    @Autowired
    StreamRecordService streamRecordService;

    @Autowired
    private StreamRecordsRepository streamRecordsRepository;

    @Test
    public void testRecordStreamData() {
        streamRecordService.recordStreamData(generateStreamData());
        Assert.assertEquals(1, streamRecordsRepository.count());
    }

    @Test
    public void testRecordStreamDataContinuousStream() {
        StreamData streamData = generateStreamData();
        streamRecordService.recordStreamData(streamData);
        streamRecordService.recordStreamData(streamData);
        Assert.assertEquals(1, streamRecordsRepository.count());
    }

    @Test
    public void testRecordStreamDataMultipleStreams() {
        StreamData streamData1 = generateStreamData();
        StreamData streamData2 = generateStreamData();
        streamRecordService.recordStreamData(streamData1);
        streamRecordService.recordStreamData(streamData2);
        Assert.assertEquals(2, streamRecordsRepository.count());
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
