package fhv.commands.customer;


import java.time.LocalDate;

public record UpdateCustomerCommand(String firstName, String lastName, String email, String address, LocalDate birthDate) {
}

