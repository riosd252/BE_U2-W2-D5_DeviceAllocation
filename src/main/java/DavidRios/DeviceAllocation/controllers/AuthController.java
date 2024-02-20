package DavidRios.DeviceAllocation.controllers;

import DavidRios.DeviceAllocation.DTOs.EmployeeLoginPayload;
import DavidRios.DeviceAllocation.DTOs.EmployeePayload;
import DavidRios.DeviceAllocation.DTOs.LoginResponsePayload;
import DavidRios.DeviceAllocation.entities.Employee;
import DavidRios.DeviceAllocation.security.AuthService;
import DavidRios.DeviceAllocation.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public LoginResponsePayload login(@RequestBody EmployeeLoginPayload employeeLoginPayload ) {
        return new LoginResponsePayload(authService.authenticateEmployeeAndGenerateToken(employeeLoginPayload));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee saveEmployee (@RequestBody EmployeePayload newEmployee) {
        return this.employeeService.save(newEmployee);
    }
}
