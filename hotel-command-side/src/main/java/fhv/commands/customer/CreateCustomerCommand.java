package fhv.commands.customer;


import java.time.LocalDate;

public record CreateCustomerCommand(String firstName, String lastName, String email, String address, LocalDate birthDate) {
}

