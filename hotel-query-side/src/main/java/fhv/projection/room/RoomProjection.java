package fhv.projection.room;


import at.fhv.sys.hotel.commands.shared.dto.room.RoomResponseDTO;
import at.fhv.sys.hotel.commands.shared.events.RoomCreated;
import fhv.models.booking.BookingQueryPanacheModel;
import fhv.models.room.RoomQueryPanacheModel;
import fhv.service.room.RoomServicePanache;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class RoomProjection {

    @Inject
    RoomServicePanache roomServicePanache;

    public List<RoomResponseDTO> getAllRooms() {

        List<RoomQueryPanacheModel> rooms = roomServicePanache.getAllRooms();

        return rooms.stream()
                .map(RoomQueryPanacheModel::toDTO)
                .toList();
    }

    public RoomResponseDTO getRoomByRoomNumber(int roomNumber) {
        return roomServicePanache.getRoomByRoomNumber(roomNumber).toDTO();
    }

    public boolean isRoomAvailable(int roomNumber, LocalDate startDate, LocalDate endDate) {
        long count = BookingQueryPanacheModel.find(
                "roomNumber = ?1 AND startDate <= ?2 AND endDate >= ?3",
                roomNumber, endDate, startDate
        ).count();

        return count == 0;
    }

    public List<RoomResponseDTO> getAvailableRooms(LocalDate startDate, LocalDate endDate) {
        return roomServicePanache.findAvailable(startDate, endDate).stream().map(RoomQueryPanacheModel::toDTO).toList();
    }

    public void processRoomCreatedEvent(RoomCreated roomCreatedEvent) {
        Logger.getAnonymousLogger().info("Processing event: " + roomCreatedEvent);

        RoomQueryPanacheModel room = new RoomQueryPanacheModel(
                roomCreatedEvent.getRoomId(),
                roomCreatedEvent.getNumberOfPerson(),
                roomCreatedEvent.getRoomNumber(),
                roomCreatedEvent.getRoomPrice(),
                roomCreatedEvent.getRoomType(),
                roomCreatedEvent.isWithBalcony(),
                roomCreatedEvent.getDescription());

        roomServicePanache.createRoom(room);
    }
}
