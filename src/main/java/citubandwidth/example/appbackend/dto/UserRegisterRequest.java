package citubandwidth.example.appbackend.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

@Data
public class UserRegisterRequest {
    @NotBlank(message = "School ID is required")
    private String schoolId;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;
}
