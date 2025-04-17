package at.fhv.sys.hotel.commands.shared.events.customer;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter

public class CustomerUpdated {

    private String firstName;
    private String lastName;
    private String email;

    public CustomerUpdated() {
    }

    public CustomerUpdated(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
