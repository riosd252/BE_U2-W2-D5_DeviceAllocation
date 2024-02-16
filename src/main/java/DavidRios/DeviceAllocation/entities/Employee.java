package DavidRios.DeviceAllocation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "employees", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Getter
@Setter
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String avatar;
    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<Device> devices;

    public Employee (String username, String name, String surname, String email, String avatar) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.avatar = avatar;
    }
}
