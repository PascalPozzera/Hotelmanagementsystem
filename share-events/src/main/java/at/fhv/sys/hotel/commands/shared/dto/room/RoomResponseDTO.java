package at.fhv.sys.hotel.commands.shared.dto.room;

import at.fhv.sys.hotel.commands.shared.enums.RoomType;

public record RoomResponseDTO(
        String roomId,
        int numberOfPerson,
        int roomNumber,
        double roomPrice,
        RoomType roomType
) {
}