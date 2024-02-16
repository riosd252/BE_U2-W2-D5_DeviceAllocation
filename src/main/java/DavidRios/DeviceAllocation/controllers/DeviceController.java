package DavidRios.DeviceAllocation.controllers;

import DavidRios.DeviceAllocation.DTOs.DevicePayload;
import DavidRios.DeviceAllocation.entities.Device;
import DavidRios.DeviceAllocation.exceptions.BadRequestException;
import DavidRios.DeviceAllocation.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @GetMapping
    public Page<Device> getDevices(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String orderBy
    ) {
        return this.deviceService.getDevices(page, size, orderBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Device save(@RequestBody @Validated DevicePayload newDevice, BindingResult validation) {

        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return this.deviceService.save(newDevice);
    }

    @GetMapping("/{uuid}")
    public Device find(@PathVariable UUID id) {
        return this.deviceService.findById(id);
    }

    @PutMapping("/{uuid}")
    public Device updateStatus(@PathVariable UUID id, @RequestBody DevicePayload updatedDevice) {

        return this.deviceService.updateStatus(id, updatedDevice);
    }

    @PutMapping("/{device_uuid}&{employee_uuid}")
    public Device setEmployee(@PathVariable UUID deviceUuid, @PathVariable UUID employeeUuid) {
       return deviceService.setEmployee(deviceUuid, employeeUuid);
    }




}
