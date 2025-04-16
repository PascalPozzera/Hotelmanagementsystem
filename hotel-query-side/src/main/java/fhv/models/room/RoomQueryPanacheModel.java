package fhv.models.room;

import at.fhv.sys.hotel.commands.shared.dto.room.RoomResponseDTO;
import at.fhv.sys.hotel.commands.shared.enums.RoomType;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class RoomQueryPanacheModel extends PanacheEntity {

    // Panache provides Auto-CRUD for everything
    private String roomId;
    private int numberOfPerson;
    private int roomNumber;
    private double roomPrice;
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    public RoomQueryPanacheModel() {
    }

    public RoomQueryPanacheModel(String roomId, int numberOfPerson, int roomNumber, double roomPrice, RoomType roomType) {
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

    @Override
    public String toString() {
        return "RoomQueryPanacheModel{" +
                "roomId='" + roomId + '\'' +
                ", numberOfPerson=" + numberOfPerson +
                ", roomNumber=" + roomNumber +
                ", roomPrice=" + roomPrice +
                ", roomType='" + roomType.toString() + '\'' +
                '}';
    }
}
