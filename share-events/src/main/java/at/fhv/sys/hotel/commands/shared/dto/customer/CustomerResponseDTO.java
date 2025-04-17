package at.fhv.sys.hotel.commands.shared.dto.customer;

import java.util.UUID;

public record CustomerResponseDTO(UUID customerId,
                                  String firstName,
                                  String lastName,
                                  String email) {
}
