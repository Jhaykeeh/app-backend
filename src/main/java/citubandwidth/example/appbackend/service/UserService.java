package citubandwidth.example.appbackend.service;

import citubandwidth.example.appbackend.entity.UserEntity;
import citubandwidth.example.appbackend.repository.DeviceRepository;
import citubandwidth.example.appbackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<UserEntity> getUserBySchoolId(String schoolId) {
        return userRepository.findBySchoolId(schoolId);
    }

    public UserEntity updateUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        deviceRepository.deleteByUserId(id);
        userRepository.deleteById(id);
    }

    public UserEntity promoteToAdmin(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(UserEntity.Role.ADMIN);
        return userRepository.save(user);
    }
}
