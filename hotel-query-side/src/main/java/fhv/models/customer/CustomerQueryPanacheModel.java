package fhv.models.customer;


import at.fhv.sys.hotel.commands.shared.dto.customer.CustomerResponseDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class CustomerQueryPanacheModel extends PanacheEntity {

    // Panache provides Auto-CRUD for everything
    public String userId;
    public String firstName;
    public String lastName;
    public String email;

    public CustomerQueryPanacheModel() {
    }

    public CustomerQueryPanacheModel(String userId, String firstName, String lastName, String email) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public CustomerResponseDTO toDTO() {
        return new CustomerResponseDTO(
                this.userId,
                this.firstName,
                this.lastName,
                this.email);
    }

    @Override
    public String toString() {
        return "CustomerQueryPanacheModel{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
