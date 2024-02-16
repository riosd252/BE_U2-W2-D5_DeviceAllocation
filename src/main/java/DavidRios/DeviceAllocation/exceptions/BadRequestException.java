package DavidRios.DeviceAllocation.exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Getter
public class BadRequestException extends RuntimeException {

    private List<ObjectError> errorList;

    public BadRequestException (String message){
        super(message);
    }

    public BadRequestException(List<ObjectError> errorList) {
        super("The following errors were encountered:");
        this.errorList = errorList;
    }
}
