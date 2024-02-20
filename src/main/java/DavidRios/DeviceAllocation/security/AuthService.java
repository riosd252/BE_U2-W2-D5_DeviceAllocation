package DavidRios.DeviceAllocation.security;

import DavidRios.DeviceAllocation.DTOs.EmployeeLoginPayload;
import DavidRios.DeviceAllocation.entities.Employee;
import DavidRios.DeviceAllocation.exceptions.UnauthorizedException;
import DavidRios.DeviceAllocation.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateEmployeeAndGenerateToken(EmployeeLoginPayload employeeLoginPayload) {

        Employee employee = employeeService.findByUsername(employeeLoginPayload.username());
        if (employee.getPassword().equals(employeeLoginPayload.password())) {
            return jwtTools.createToken(employee);
        } else {
            throw new UnauthorizedException("Wrong credentials!");
        }
    }
}
