package citubandwidth.example.appbackend.repository;

//package com.citu.bandwisth.repository;

//import com.citu.bandwisth.model.Device;
import citubandwidth.example.appbackend.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {
    List<DeviceEntity> findByUserId(Long userId);
    List<DeviceEntity> findByUserIdAndIsActive(Long userId, boolean isActive);
    List<DeviceEntity> findByIsActive(boolean isActive);
}