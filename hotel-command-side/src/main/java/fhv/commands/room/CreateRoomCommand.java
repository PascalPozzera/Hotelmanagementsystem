package fhv.commands.room;


import at.fhv.sys.hotel.commands.shared.enums.RoomType;

public record CreateRoomCommand(String roomId,
                                int numberOfPersons,
                                int roomNumber,
                                double roomPrice,
                                RoomType roomType) {
}

