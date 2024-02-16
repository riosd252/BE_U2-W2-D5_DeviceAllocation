package DavidRios.DeviceAllocation.DTOs;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public record DevicePayload(
        @Min(value = 1)
        @Max(value = 3)
        int deviceType,
        @Min(value = 1)
        @Max(value = 4)
        int deviceStatus
) {
}
