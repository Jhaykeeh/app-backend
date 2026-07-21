package citubandwidth.example.appbackend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    // ── Identity ───────────────────────────────────────────

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "school_id", unique = true, nullable = false, length = 20)
    private String schoolId;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    // ── Authentication & Authorization ─────────────────────

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.USER;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.ACTIVE;

    // ── Personal Information ───────────────────────────────

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    // ── Academic (optional, updated via profile) ───────────

    @Column(length = 100)
    private String course;

    @Column(name = "year_level", length = 20)
    @JsonProperty("year")
    private String yearLevel;

    // ── Contact (optional, updated via profile) ────────────

    @Column(name = "contact_number", length = 20)
    private String contactNumber;

    // ── Timestamps ─────────────────────────────────────────

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // ── Enums ──────────────────────────────────────────────

    public enum Role {
        USER, ADMIN
    }

    public enum Status {
        ACTIVE, DISABLED
    }
}
