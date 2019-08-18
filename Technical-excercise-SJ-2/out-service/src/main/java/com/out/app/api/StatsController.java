package com.out.app.api;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.out.app.service.StatsService;

/**
 * Controller for exposing apis for all the stream data statistics.
 */
@RestController
@RequestMapping(path = "stats/viewercount")
public class StatsController {

    @Autowired
    private StatsService statsService;

    /**
     * Returns the count of the number of unique devices watching a particular stream.
     * @return integer value.
     */
    @GetMapping("/streams/")
    public Integer getViewerCountForStream(@RequestParam(value = "streamId") UUID streamId) {
        // Validate UUID.
        return statsService.getViewerCountForStream(streamId);
    }

    @GetMapping("/location/")
    public Integer getStreamCountByLocation(@RequestParam(value = "state") String state,
                                            @RequestParam(value = "country") String country) {
        return statsService.getViewerCountByLocation(state, country);
    }

    @GetMapping()
    public Integer getAllStreamCount() {
        return statsService.getViewersAcrossAllStreamsCount();
    }

    @GetMapping("/users/")
    public Integer getStreamCountForUser(@RequestParam(value = "userId") UUID userId) {
        return statsService.getViewersAcrossAllStreamsCount();
    }
}
