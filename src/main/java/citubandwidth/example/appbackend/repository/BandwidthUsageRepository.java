package citubandwidth.example.appbackend.repository;

//package com.citu.bandwisth.repository;

import citubandwidth.example.appbackend.entity.BandwidthUsageEntity;
//import com.citu.bandwisth.model.BandwidthUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BandwidthUsageRepository extends JpaRepository<BandwidthUsageEntity, Long> {
    List<BandwidthUsageEntity> findByUserId(Long userId);
    List<BandwidthUsageEntity> findByDeviceId(Long deviceId);
    List<BandwidthUsageEntity> findByUserIdAndTimestampBetween(Long userId, LocalDateTime start, LocalDateTime end);

    @Query("SELECT SUM(b.usageAmount) FROM BandwidthUsageEntity b WHERE b.userId = :userId AND b.unit = 'MB'")
    Double findTotalUsageInMB(@Param("userId") Long userId);

    @Query("SELECT SUM(b.usageAmount) FROM BandwidthUsageEntity b WHERE b.userId = :userId AND b.unit = 'MB' " +
            "AND b.timestamp >= :start AND b.timestamp <= :end")
    Double findUsageBetween(@Param("userId") Long userId, @Param("start") LocalDateTime start,
                            @Param("end") LocalDateTime end);
}