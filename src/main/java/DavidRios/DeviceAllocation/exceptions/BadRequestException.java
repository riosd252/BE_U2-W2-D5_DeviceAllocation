package DavidRios.DeviceAllocation.exceptions;

import org.springframework.web.client.HttpClientErrorException;

public class BadRequestException extends RuntimeException {
    public BadRequestException (String message){
        super(message);
    }
}
