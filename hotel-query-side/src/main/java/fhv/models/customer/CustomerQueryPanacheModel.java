package fhv.models.customer;


import at.fhv.sys.hotel.commands.shared.dto.customer.CustomerResponseDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class CustomerQueryPanacheModel extends PanacheEntityBase {

    @Id
    public UUID customerId;
    public String firstName;
    public String lastName;
    public String email;

    public CustomerQueryPanacheModel() {
    }

    public CustomerQueryPanacheModel(UUID customerId, String firstName, String lastName, String email) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public CustomerQueryPanacheModel(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public CustomerResponseDTO toDTO() {
        return new CustomerResponseDTO(
                this.customerId,
                this.firstName,
                this.lastName,
                this.email);
    }

    @Override
    public String toString() {
        return "CustomerQueryPanacheModel{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
