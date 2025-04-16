package fhv.models.room;

import at.fhv.sys.hotel.commands.shared.dto.room.RoomResponseDTO;
import at.fhv.sys.hotel.commands.shared.enums.RoomType;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class RoomQueryPanacheModel extends PanacheEntity {

    private UUID roomId;

    private int numberOfPerson;

    private int roomNumber;

    private double roomPrice;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    private boolean withBalcony;

    private String description;

    public RoomQueryPanacheModel() {
    }

    public RoomQueryPanacheModel(UUID roomId, int numberOfPerson, int roomNumber, double roomPrice, RoomType roomType, boolean withBalcony, String description) {
        this.roomId = roomId;
        this.numberOfPerson = numberOfPerson;
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.roomType = roomType;
        this.withBalcony = withBalcony;
        this.description = description;
    }

    public RoomResponseDTO toDTO() {
        return new RoomResponseDTO(
                this.roomId,
                this.numberOfPerson,
                this.roomNumber,
                this.roomPrice,
                this.roomType,
                this.withBalcony,
                this.description
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
