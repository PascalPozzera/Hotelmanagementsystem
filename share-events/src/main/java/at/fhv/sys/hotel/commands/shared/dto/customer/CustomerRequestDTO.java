package at.fhv.sys.hotel.commands.shared.dto.customer;

import jakarta.ws.rs.QueryParam;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CustomerRequestDTO {

    @QueryParam("firstName")
    private String firstName;

    @QueryParam("lastname")
    private String lastname;

    @QueryParam("email")
    private String email;

    @QueryParam("address")
    private String address;

    @QueryParam("birthDate")
    private LocalDate birthDate;
}
