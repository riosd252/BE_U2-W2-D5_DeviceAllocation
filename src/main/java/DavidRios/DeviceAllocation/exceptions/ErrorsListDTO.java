package DavidRios.DeviceAllocation.exceptions;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ErrorsListDTO extends ErrorsDTO {
    private List<String> errorList;

    public ErrorsListDTO(String message, LocalDateTime timestamp, List<String> errorList) {
        super(message, timestamp);
        this.errorList = errorList;
    }
}
