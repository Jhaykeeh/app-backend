package citubandwidth.example.appbackend.service;

//package com.citu.bandwisth.service;

import citubandwidth.example.appbackend.dto.DeviceRequest;
import citubandwidth.example.appbackend.entity.DeviceEntity;
import citubandwidth.example.appbackend.entity.DeviceEntity.DeviceStatus;
import citubandwidth.example.appbackend.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    private static final int MAX_DEVICES_PER_USER = 2;

    public DeviceEntity registerDevice(Long userId, DeviceRequest request) {
        long deviceCount = deviceRepository.countByUserId(userId);

        if (deviceCount >= MAX_DEVICES_PER_USER) {
            throw new RuntimeException("Maximum of " + MAX_DEVICES_PER_USER + " devices per user reached");
        }

        DeviceEntity device = new DeviceEntity();
        device.setUserId(userId);
        device.setBrand(request.getBrand());
        device.setModel(request.getModel());

        // First device: auto-approved, second device: pending
        if (deviceCount == 0) {
            device.setApprovalStatus(DeviceStatus.APPROVED);
            device.setActive(true);
        } else {
            device.setApprovalStatus(DeviceStatus.PENDING);
            device.setActive(false);
        }

        return deviceRepository.save(device);
    }

    public List<DeviceEntity> getUserDevices(Long userId) {
        return deviceRepository.findByUserId(userId);
    }

    public DeviceEntity updateDevice(Long id, DeviceRequest request) {
        DeviceEntity device = deviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Device not found"));
        device.setBrand(request.getBrand());
        device.setModel(request.getModel());
        return deviceRepository.save(device);
    }

    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }

    public List<DeviceEntity> getAllDevices() {
        return deviceRepository.findAll();
    }

    public DeviceEntity approveDevice(Long id) {
        DeviceEntity device = deviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Device not found"));
        device.setApprovalStatus(DeviceStatus.APPROVED);
        device.setActive(true);
        return deviceRepository.save(device);
    }

    public DeviceEntity rejectDevice(Long id) {
        DeviceEntity device = deviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Device not found"));
        device.setApprovalStatus(DeviceStatus.REJECTED);
        device.setActive(false);
        return deviceRepository.save(device);
    }
}
