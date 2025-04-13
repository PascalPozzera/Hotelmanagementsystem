package fhv.commands.customer;


public record CreateCustomerCommand(String customerId, String firstName, String lastName, String email) {
}

