package at.fhv.sys.hotel.commands.shared.events;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter

public class CustomerCreated {

    private UUID customerId;
    private String firstName;
    private String lastName;
    private String email;

    public CustomerCreated() {
    }

    public CustomerCreated(UUID customerId, String firstName, String lastName, String email) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
