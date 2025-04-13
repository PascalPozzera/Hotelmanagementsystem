package at.fhv.sys.hotel.commands.shared.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CustomerCreated {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;


    public CustomerCreated() {}

    public CustomerCreated(String userId, String firstName, String lastName, String email) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return "CustomerCreated{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
