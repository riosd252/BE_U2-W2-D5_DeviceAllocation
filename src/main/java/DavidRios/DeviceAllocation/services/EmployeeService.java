package DavidRios.DeviceAllocation.services;

import DavidRios.DeviceAllocation.DTOs.EmployeePayload;
import DavidRios.DeviceAllocation.entities.Employee;
import DavidRios.DeviceAllocation.exceptions.BadRequestException;
import DavidRios.DeviceAllocation.exceptions.NotFoundException;
import DavidRios.DeviceAllocation.repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    public Page<Employee> getEmployees(int pageNumber, int size, String orderBy) {
        if(size > 10) size = 10;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return employeeRepo.findAll(pageable);
    }

    public Employee saveEmployee(EmployeePayload newEmployee) {
        employeeRepo.findByEmail(newEmployee.email()).ifPresent(employee -> {
            throw  new BadRequestException("Email address " + employee.getEmail() + " is already in use.");
        });
        return employeeRepo.save(new Employee(newEmployee.username(), newEmployee.name(), newEmployee.surname(), newEmployee.email(), "https://i.pravatar.cc/300"));
    }

    public Employee findById(UUID uuid) {
        return employeeRepo.findById(uuid).orElseThrow(() -> new NotFoundException("Employee identified as " + uuid + " was not found."));
    }

    public Employee update(UUID uuid, EmployeePayload modifiedEmployee) {
        Employee toUpdate = findById(uuid);
        toUpdate.setUsername(modifiedEmployee.username());
        toUpdate.setName(modifiedEmployee.name());
        toUpdate.setSurname(modifiedEmployee.surname());
        toUpdate.setEmail(modifiedEmployee.email());

        return employeeRepo.save(toUpdate);
    }

    public void delete(UUID uuid) {
        Employee toDelete = findById(uuid);
        employeeRepo.delete(toDelete);
    }
}
