package com.out.app.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.out.app.model.StreamData;
import com.out.app.service.StreamRecordService;

/**
 * REST API controller for clients to send stream related info to the service.
 */
@RestController
@RequestMapping(path = "stream")
public class StreamRecordController {

    StreamRecordService streamRecordService;

    @Autowired
    public StreamRecordController(StreamRecordService streamRecordService) {
        this.streamRecordService = streamRecordService;
    }

    @PostMapping
    public void recordStreamData(@RequestBody StreamData streamData) {
        streamRecordService.recordStreamData(streamData);
    }

}
