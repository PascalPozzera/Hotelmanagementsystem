package at.fhv.sys.hotel.commands.shared.dto.customer;

import jakarta.ws.rs.QueryParam;

import java.time.LocalDate;

public class UpdateCustomerRequestDTO {

    @QueryParam("firstName")
    private String firstName;

    @QueryParam("lastname")
    private String lastname;

    @QueryParam("email")
    private String email;

    @QueryParam("phone")
    private String phoneNumber;

    @QueryParam("address")
    private String address;

    @QueryParam("birthDate")
    private LocalDate birthDate;

}

