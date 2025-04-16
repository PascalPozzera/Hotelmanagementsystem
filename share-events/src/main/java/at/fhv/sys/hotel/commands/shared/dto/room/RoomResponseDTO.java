package at.fhv.sys.hotel.commands.shared.dto.room;

import at.fhv.sys.hotel.commands.shared.enums.RoomType;

import java.util.UUID;

public record RoomResponseDTO(
        UUID roomId,
        int numberOfPerson,
        int roomNumber,
        double roomPrice,
        RoomType roomType,
        boolean withBalcony,
        String description
) {
}