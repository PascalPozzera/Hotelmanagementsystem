package at.fhv.sys.hotel.commands.shared.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CustomerCreated {

    private String firstName;
    private String lastName;
    private String email;


    public CustomerCreated() {}

    public CustomerCreated(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return "CustomerCreated{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
