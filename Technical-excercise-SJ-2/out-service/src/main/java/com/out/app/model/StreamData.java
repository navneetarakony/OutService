package com.out.app.model;

import java.util.UUID;

import lombok.Data;
import lombok.ToString;

/**
 * Data object containing the stream related information sent from each user device.
 */
@Data
@ToString
public class StreamData {

    /*
    User id which will be unique for each user.
     */
    UUID userId;

    /*
    device id of device in which the user is watching a stream.
     */
    UUID deviceId;

    /*
    Stream id which maps to a particular channel.
     */
    UUID streamId;

    /*
    Location information. Can be made more granular in the future.
     */
    String state;

    /*
    Country.
     */
    String country;
}

