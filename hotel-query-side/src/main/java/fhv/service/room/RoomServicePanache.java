package fhv.service.room;

import fhv.models.room.RoomQueryPanacheModel;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@ApplicationScoped
public class RoomServicePanache {

    public List<RoomQueryPanacheModel> getAllRooms() {
        return RoomQueryPanacheModel.listAll();
    }

    public List<RoomQueryPanacheModel> findAvailable(LocalDate startDate, LocalDate endDate) {
        return RoomQueryPanacheModel.find("""
        FROM RoomQueryPanacheModel r
        WHERE r.roomNumber NOT IN (
            SELECT b.roomNumber FROM BookingQueryPanacheModel b
            WHERE b.startDate <= ?1 AND b.endDate >= ?2
        )
        """, endDate, startDate).list();
    }


    @Transactional
    public void createRoom(RoomQueryPanacheModel room) {
        room.persist();
    }

    public RoomQueryPanacheModel getRoomByRoomNumber(int roomNumber) {
        return RoomQueryPanacheModel.find("roomNumber = ?1", roomNumber).firstResult();
    }
}
