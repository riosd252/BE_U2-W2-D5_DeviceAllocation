package DavidRios.DeviceAllocation;

import DavidRios.DeviceAllocation.enums.DeviceStatus;
import DavidRios.DeviceAllocation.enums.DeviceType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class DeviceAllocationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeviceAllocationApplication.class, args);

		Map<Integer, DeviceType> deviceTypeMap = new HashMap<>(3);
		deviceTypeMap.put(1, DeviceType.SMARTPHONE);
		deviceTypeMap.put(2, DeviceType.TABLET);
		deviceTypeMap.put(3, DeviceType.LAPTOP);

		Map<Integer, DeviceStatus> deviceStatusMap = new HashMap<>(4);
		deviceStatusMap.put(1, DeviceStatus.AVAILABLE);
		deviceStatusMap.put(2, DeviceStatus.ASSIGNED);
		deviceStatusMap.put(3, DeviceStatus.MAINTENANCE);
		deviceStatusMap.put(4, DeviceStatus.UNAVAILABLE);
	}

}
