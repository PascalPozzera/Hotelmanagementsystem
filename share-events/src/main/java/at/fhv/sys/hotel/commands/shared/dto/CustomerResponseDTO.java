package at.fhv.sys.hotel.commands.shared.dto;

public record CustomerResponseDTO(String userId,
                                  String firstName,
                                  String lastName,
                                  String email) {
}
