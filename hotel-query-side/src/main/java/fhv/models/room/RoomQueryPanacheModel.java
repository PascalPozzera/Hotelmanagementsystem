package fhv.models.room;

import at.fhv.sys.hotel.commands.shared.dto.RoomResponseDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class RoomQueryPanacheModel extends PanacheEntity {


    // Panache provides Auto-CRUD for everything
    private String roomId;
    private int numberOfPerson;
    private int roomNumber;
    private double roomPrice;
    private String roomType;

    public RoomQueryPanacheModel() {
    }

    public RoomQueryPanacheModel(String roomId, int numberOfPerson, int roomNumber, double roomPrice, String roomType) {
        this.roomId = roomId;
        this.numberOfPerson = numberOfPerson;
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.roomType = roomType;
    }

    public RoomResponseDTO toDTO() {
        return new RoomResponseDTO(
                this.roomId,
                this.numberOfPerson,
                this.roomNumber,
                this.roomPrice,
                this.roomType
        );
    }
}
