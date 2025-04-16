package fhv.projection.room;


import at.fhv.sys.hotel.commands.shared.dto.room.RoomResponseDTO;
import at.fhv.sys.hotel.commands.shared.events.RoomCreated;
import fhv.models.room.RoomQueryPanacheModel;
import fhv.service.room.RoomServicePanache;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

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

    public void processRoomCreatedEvent(RoomCreated roomCreatedEvent) {
        Logger.getAnonymousLogger().info("Processing event: " + roomCreatedEvent);

        RoomQueryPanacheModel room = new RoomQueryPanacheModel(
                roomCreatedEvent.getRoomId(),
                roomCreatedEvent.getNumberOfPerson(),
                roomCreatedEvent.getRoomNumber(),
                roomCreatedEvent.getRoomPrice(),
                roomCreatedEvent.getRoomType());

        roomServicePanache.createRoom(room);
    }
}
