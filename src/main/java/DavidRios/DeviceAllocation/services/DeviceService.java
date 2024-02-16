package DavidRios.DeviceAllocation.services;

import DavidRios.DeviceAllocation.DTOs.DevicePayload;
import DavidRios.DeviceAllocation.entities.Device;
import DavidRios.DeviceAllocation.enums.DeviceStatus;
import DavidRios.DeviceAllocation.enums.DeviceType;
import DavidRios.DeviceAllocation.exceptions.BadRequestException;
import DavidRios.DeviceAllocation.exceptions.NotFoundException;
import DavidRios.DeviceAllocation.repositories.DeviceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

import static java.util.Map.entry;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepo deviceRepo;

    @Autowired
    private EmployeeService employeeService;


  private final Map<Integer,DeviceType> deviceTypeMap = Map.ofEntries(entry(1, DeviceType.SMARTPHONE),
            entry(2, DeviceType.TABLET),
            entry(3, DeviceType.LAPTOP));

    private final Map<Integer, DeviceStatus> deviceStatusMap = Map.ofEntries(entry(1, DeviceStatus.AVAILABLE),
            entry(2, DeviceStatus.ASSIGNED),
            entry(3, DeviceStatus.MAINTENANCE),
            entry(4, DeviceStatus.UNAVAILABLE));


    public Page<Device> getDevices(int pageNumber, int size, String orderBy) {
        if(size > 10) size = 10;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return deviceRepo.findAll(pageable);
    }

    public Device save(DevicePayload newDevice) {
        return deviceRepo.save(new Device(deviceTypeMap.get(newDevice.deviceType()), deviceStatusMap.get(newDevice.deviceStatus())));
    }

    public Device findById(UUID uuid) {
        return deviceRepo.findById(uuid).orElseThrow(() -> new NotFoundException("Device identified as " + uuid + " was not found."));
    }

    public Device updateStatus(UUID uuid, DevicePayload modifiedDevice) {
        Device toUpdate = findById(uuid);
        toUpdate.setStatus(deviceStatusMap.get(modifiedDevice.deviceStatus()));
        return deviceRepo.save(toUpdate);
    }

    public Device setEmployee(UUID deviceUuid, UUID employeeUuid) {
        Device device = findById(deviceUuid);
        if (device.getStatus() != DeviceStatus.AVAILABLE) throw new BadRequestException("Device identified as " + deviceUuid + " is not available.");
        device.setEmployee(employeeService.findById(employeeUuid));
        return  deviceRepo.save(device);
    }

    public void delete(UUID uuid) {
        Device toDelete = findById(uuid);
        deviceRepo.delete(toDelete);
    }
}
