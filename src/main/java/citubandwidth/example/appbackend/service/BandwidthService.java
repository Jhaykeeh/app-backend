package citubandwidth.example.appbackend.service;

//package com.citu.bandwisth.service;

import citubandwidth.example.appbackend.entity.BandwidthUsageEntity;
import citubandwidth.example.appbackend.repository.BandwidthUsageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BandwidthService {

    private final BandwidthUsageRepository bandwidthUsageRepository;

    public BandwidthUsageEntity logUsage(Long userId, Long deviceId, Double usageAmount,
                                         BandwidthUsageEntity.Unit unit) {
        BandwidthUsageEntity usage = new BandwidthUsageEntity();
        usage.setUserId(userId);
        usage.setDeviceId(deviceId);
        usage.setUsageAmount(usageAmount);
        usage.setUnit(unit);
        usage.setTimestamp(LocalDateTime.now());
        usage.setConnectionStatus(BandwidthUsageEntity.ConnectionStatus.CONNECTED);
        return bandwidthUsageRepository.save(usage);
    }

    public List<BandwidthUsageEntity> getUserUsage(Long userId) {
        return bandwidthUsageRepository.findByUserId(userId);
    }

    public Double getTotalUsage(Long userId) {
        Double total = bandwidthUsageRepository.findTotalUsageInMB(userId);
        return total != null ? total : 0.0;
    }

    public Double getUsageBetween(Long userId, LocalDateTime start, LocalDateTime end) {
        Double usage = bandwidthUsageRepository.findUsageBetween(userId, start, end);
        return usage != null ? usage : 0.0;
    }
}