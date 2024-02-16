package DavidRios.DeviceAllocation.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorsDTO {
    private String message;
    private LocalDateTime timestamp;
}
