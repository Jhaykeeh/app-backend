package citubandwidth.example.appbackend.repository;

//package com.citu.bandwisth.repository;

//import com.citu.bandwisth.model.Device;
import citubandwidth.example.appbackend.entity.DeviceEntity;
import citubandwidth.example.appbackend.entity.DeviceEntity.DeviceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {
    List<DeviceEntity> findByUserId(Long userId);
    List<DeviceEntity> findByUserIdAndIsActive(Long userId, boolean isActive);
    List<DeviceEntity> findByIsActive(boolean isActive);
    long countByUserId(Long userId);
    List<DeviceEntity> findByApprovalStatus(DeviceStatus status);
    List<DeviceEntity> findByUserIdAndApprovalStatus(Long userId, DeviceStatus status);
    void deleteByUserId(Long userId);
}