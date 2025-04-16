package fhv.commands.room;


import at.fhv.sys.hotel.commands.shared.enums.RoomType;

import java.util.UUID;

public record CreateRoomCommand(UUID roomId,
                                int numberOfPersons,
                                int roomNumber,
                                double roomPrice,
                                RoomType roomType,
                                boolean hasBalcony,
                                String description) {
}

