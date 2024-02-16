package DavidRios.DeviceAllocation.controllers;

import DavidRios.DeviceAllocation.DTOs.EmployeePayload;
import DavidRios.DeviceAllocation.entities.Employee;
import DavidRios.DeviceAllocation.exceptions.BadRequestException;
import DavidRios.DeviceAllocation.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public Page<Employee> getEmployees(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "surname") String orderBy){
        return employeeService.getEmployees(page, size, orderBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee save(@RequestBody @Validated EmployeePayload newEmployee, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return employeeService.save(newEmployee);
    }

    @GetMapping("/{uuid}")
    public Employee find(@PathVariable UUID uuid) {
        return employeeService.findById(uuid);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Employee update(@PathVariable UUID uuid, @RequestBody EmployeePayload updatedEmployee) {
        return employeeService.update(uuid, updatedEmployee);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID uuid) {
        employeeService.delete(uuid);
    }

}
