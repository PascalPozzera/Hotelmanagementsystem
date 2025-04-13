package fhv.dto.requestDTO.customer;

import jakarta.ws.rs.QueryParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequestDTO {

    @QueryParam("firstName")
    private String firstName;

    @QueryParam("lastname")
    private String lastname;

    @QueryParam("email")
    private String email;
}
