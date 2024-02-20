package DavidRios.DeviceAllocation.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record EmployeePayload(@NotEmpty(message = "Username is mandatory.")
                              @NotBlank
                              @Size(min = 3, max = 10, message = "Username length must go from 3 up to 10 characters.")
                              String username,
                              @NotEmpty(message = "Name is mandatory.")
                              @NotBlank
                              @Size(min = 2, max = 15, message = "Name length must go from 2 up to 15 characters.")
                              String name,
                              @NotEmpty(message = "Surname is mandatory.")
                              @NotBlank
                              @Size(min = 2, max = 15, message = "Surname length must go from 2 up to 15 characters.")
                              String surname,
                              @NotEmpty(message = "Email is mandatory.")
                              @NotBlank
                              @Email
                              String email,
                              @NotEmpty(message = "Password is mandatory")
                              @NotBlank
                              String password
                              ) {
}
