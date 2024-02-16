package DavidRios.DeviceAllocation.DTOs;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DevicePayload(
        @NotEmpty(message = "Field is mandatory.")
        @Min(value = 1)
        @Max(value = 3)
        int deviceType,
        @NotEmpty(message = "Field is mandatory.")
        @Min(value = 1)
        @Max(value = 4)
        int deviceStatus
) {
}
