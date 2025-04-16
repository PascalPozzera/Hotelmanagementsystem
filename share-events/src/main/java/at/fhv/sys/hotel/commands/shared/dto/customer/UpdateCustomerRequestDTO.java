package at.fhv.sys.hotel.commands.shared.dto.customer;

import jakarta.ws.rs.QueryParam;

public class UpdateCustomerRequestDTO {

    @QueryParam("firstName")
    private String firstName;

    @QueryParam("lastname")
    private String lastname;

    @QueryParam("email")
    private String email;

    @QueryParam("phone")
    private String phoneNumber;

}

