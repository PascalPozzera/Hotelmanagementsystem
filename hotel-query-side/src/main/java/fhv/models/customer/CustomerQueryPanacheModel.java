package fhv.models.customer;


import at.fhv.sys.hotel.commands.shared.dto.customer.CustomerResponseDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class CustomerQueryPanacheModel extends PanacheEntity {

    //No id as attribute, because PanacheEntity will create by default one
    public String firstName;
    public String lastName;
    public String email;

    public CustomerQueryPanacheModel() {
    }

    public CustomerQueryPanacheModel(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public CustomerResponseDTO toDTO() {
        return new CustomerResponseDTO(
                this.firstName,
                this.lastName,
                this.email);
    }

    @Override
    public String toString() {
        return "CustomerQueryPanacheModel{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
