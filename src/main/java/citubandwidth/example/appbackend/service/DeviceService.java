package citubandwidth.example.appbackend.service;

//package com.citu.bandwisth.service;

import citubandwidth.example.appbackend.dto.DeviceRequest;
import citubandwidth.example.appbackend.entity.DeviceEntity;
import citubandwidth.example.appbackend.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceEntity registerDevice(Long userId, DeviceRequest request) {
        DeviceEntity device = new DeviceEntity();
        device.setUserId(userId);
        device.setBrand(request.getBrand());
        device.setModel(request.getModel());
        device.setMacAddress(request.getMacAddress());
        device.setActive(true);
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
        device.setMacAddress(request.getMacAddress());
        return deviceRepository.save(device);
    }

    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }

    public List<DeviceEntity> getAllDevices() {
        return deviceRepository.findAll();
    }
}
