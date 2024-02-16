package DavidRios.DeviceAllocation.entities;

import DavidRios.DeviceAllocation.enums.DeviceStatus;
import DavidRios.DeviceAllocation.enums.DeviceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "devices")
@Getter
@Setter
@NoArgsConstructor
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Enumerated(EnumType.STRING)
    private DeviceType type;
    private DeviceStatus status;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Device(DeviceType type, DeviceStatus status) {
        this.type = type;
        this.status = status;
    }

}
