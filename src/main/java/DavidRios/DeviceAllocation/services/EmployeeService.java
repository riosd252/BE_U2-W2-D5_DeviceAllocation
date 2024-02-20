package DavidRios.DeviceAllocation.services;

import DavidRios.DeviceAllocation.DTOs.EmployeePayload;
import DavidRios.DeviceAllocation.entities.Employee;
import DavidRios.DeviceAllocation.exceptions.BadRequestException;
import DavidRios.DeviceAllocation.exceptions.NotFoundException;
import DavidRios.DeviceAllocation.repositories.EmployeeRepo;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private Cloudinary cloudinaryUploader;

    public Page<Employee> getEmployees(int pageNumber, int size, String orderBy) {
        if(size > 10) size = 10;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return employeeRepo.findAll(pageable);
    }

    public Employee save(EmployeePayload newEmployee) {
        employeeRepo.findByEmail(newEmployee.email()).ifPresent(employee -> {
            throw  new BadRequestException("Email address " + employee.getEmail() + " already exists.");
        });
        return employeeRepo.save(new Employee(newEmployee.username(), newEmployee.name(), newEmployee.surname(), newEmployee.email(), newEmployee.password(), "https://i.pravatar.cc/300"));
    }

    public Employee findById(UUID uuid) {
        return employeeRepo.findById(uuid).orElseThrow(() -> new NotFoundException("Employee identified as " + uuid + " was not found."));
    }

    public Employee findByUsername(String username) {
        return employeeRepo.findByUsername(username).orElseThrow(() -> new NotFoundException("Employee identified as " + username + " was not found."));
    }

    public Employee update(UUID uuid, EmployeePayload modifiedEmployee) {
        Employee toUpdate = findById(uuid);
        toUpdate.setUsername(modifiedEmployee.username());
        toUpdate.setName(modifiedEmployee.name());
        toUpdate.setSurname(modifiedEmployee.surname());
        toUpdate.setEmail(modifiedEmployee.email());

        return employeeRepo.save(toUpdate);
    }

    public String uploadAvatar(MultipartFile image) throws IOException {
        return (String) cloudinaryUploader.uploader().upload(image.getBytes(),
                ObjectUtils.emptyMap()).get("url");
    }

    public void delete(UUID uuid) {
        Employee toDelete = findById(uuid);
        employeeRepo.delete(toDelete);
    }
}
