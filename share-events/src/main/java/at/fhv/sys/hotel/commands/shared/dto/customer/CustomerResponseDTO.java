package at.fhv.sys.hotel.commands.shared.dto.customer;

import java.time.LocalDate;
import java.util.UUID;

public record CustomerResponseDTO(UUID customerId,
                                  String firstName,
                                  String lastName,
                                  String email,
                                  String address,
                                  LocalDate birthDate) {
}
