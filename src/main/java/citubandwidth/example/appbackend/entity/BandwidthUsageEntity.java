package citubandwidth.example.appbackend.entity;

//package com.citu.bandwisth.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "bandwidth_usage")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BandwidthUsageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "device_id", nullable = false)
    private Long deviceId;

    @Column(name = "usage_amount", nullable = false)
    private Double usageAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Unit unit = Unit.MB;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private ConnectionStatus connectionStatus = ConnectionStatus.CONNECTED;

    public enum Unit {
        MB, GB
    }

    public enum ConnectionStatus {
        CONNECTED, DISCONNECTED
    }
}