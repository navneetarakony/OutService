package com.out.app.entity;

import java.time.Instant;
import java.util.UUID;

import javax.persistence.Column;
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
    @Column(nullable = false)
    UUID userId;

    /*
    Id to uniquely identify every device.
     */
    @Column(nullable = false)
    UUID deviceId;

    /*
    Stream id corresponding to every channel/stream.
     */
    @Column(nullable = false)
    UUID streamId;

    /*
    State code of the user accessing the stream.
     */
    @Column(nullable = false)
    String state;

    /*
    Country name of the user accessing the stream.
     */
    @Column(nullable = false)
    String country;

    /*
    last usage time for this stream by the user.
     */
    @Column(nullable = false)
    Instant lastUpdatedAt;

    /*
     * Timestamp when the record was created/updated.
     */
    @Column(nullable = false, updatable = false)
    Instant createdAt;
}
