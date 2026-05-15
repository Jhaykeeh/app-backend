package citubandwidth.example.appbackend.service;

import citubandwidth.example.appbackend.dto.AuthRequest;
import citubandwidth.example.appbackend.dto.AuthResponse;
import citubandwidth.example.appbackend.dto.UserRegisterRequest;
import citubandwidth.example.appbackend.entity.UserEntity;
import citubandwidth.example.appbackend.repository.UserRepository;
import citubandwidth.example.appbackend.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponse register(UserRegisterRequest request) {

        if (userRepository.existsBySchoolId(request.getSchoolId())) {
            throw new RuntimeException("School ID already registered");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        UserEntity user = new UserEntity();
        user.setSchoolId(request.getSchoolId());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setDeviceBrand(request.getDeviceBrand());
        user.setDeviceModel(request.getDeviceModel());
        user.setRole(UserEntity.Role.USER);
        user.setStatus(UserEntity.Status.ACTIVE);

        UserEntity savedUser = userRepository.save(user);

        String token = jwtUtil.generateToken(savedUser.getSchoolId());

        return new AuthResponse(
                token,
                savedUser.getSchoolId(),
                savedUser.getEmail(),
                savedUser.getRole().toString(),
                savedUser.getId()
        );
    }

    public AuthResponse login(AuthRequest request) {

        UserEntity user = userRepository.findBySchoolId(request.getSchoolId())
                .orElseThrow(() -> new RuntimeException("Invalid school ID or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid school ID or password");
        }

        if (user.getStatus() == UserEntity.Status.DISABLED) {
            throw new RuntimeException("Account is disabled. Contact admin.");
        }

        String token = jwtUtil.generateToken(user.getSchoolId());

        return new AuthResponse(
                token,
                user.getSchoolId(),
                user.getEmail(),
                user.getRole().toString(),
                user.getId()
        );
    }
}