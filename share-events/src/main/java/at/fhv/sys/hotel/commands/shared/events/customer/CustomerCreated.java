package at.fhv.sys.hotel.commands.shared.events.customer;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter

public class CustomerCreated {

    private UUID customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private LocalDate birthDate;

    public CustomerCreated() {
    }

    public CustomerCreated(UUID customerId, String firstName, String lastName, String email, String address, LocalDate birthDate) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.birthDate = birthDate;
    }
}
