package com.out.app.entity;

import java.time.Instant;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity object for persisting stream data.
 */
@Entity(name="StreamRecordEntity")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StreamRecordEntity {


    /*
    Id to uniquely identify an entity.
     */
    @Id
    UUID id;

    /*
    User Id.
     */
    UUID userId;

    /*
    Id to uniquely identify every device.
     */
    UUID deviceId;

    /*
    Stream id corresponding to every channel/stream.
     */
    UUID streamId;

    /*
    zipcode of the user accessing the stream.
     */
    int zipCode;

    /*
    last usage time for this stream by the user.
     */
    Instant lastUpdateTime;

    /*
     * Timestamp when the record was created/updated.
     */
    Instant createdAt;
}
