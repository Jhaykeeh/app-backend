package citubandwidth.example.appbackend.repository;

import citubandwidth.example.appbackend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findBySchoolId(String schoolId);
    Optional<UserEntity> findByEmail(String email);
    boolean existsBySchoolId(String schoolId);
    boolean existsByEmail(String email);
}
