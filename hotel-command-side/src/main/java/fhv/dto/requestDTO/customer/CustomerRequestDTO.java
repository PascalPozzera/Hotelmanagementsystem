package fhv.dto.requestDTO.customer;

import jakarta.ws.rs.QueryParam;

public class CustomerRequestDTO {

    @QueryParam("firstName")
    private String firstName;

    @QueryParam("lastname")
    private String lastname;

    @QueryParam("email")
    private String email;

    @QueryParam("phone")
    private String phoneNumber;

}
