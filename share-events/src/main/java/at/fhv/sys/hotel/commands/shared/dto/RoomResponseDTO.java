package at.fhv.sys.hotel.commands.shared.dto;

public record RoomResponseDTO(
        String roomId,
        int numberOfPerson,
        int roomNumber,
        double roomPrice,
        String roomType
) {
}