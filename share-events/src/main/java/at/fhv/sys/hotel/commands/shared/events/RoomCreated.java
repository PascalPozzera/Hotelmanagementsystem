package at.fhv.sys.hotel.commands.shared.events;

import at.fhv.sys.hotel.commands.shared.enums.RoomType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter

public class RoomCreated {

    private UUID roomId;
    private int numberOfPerson;
    private int roomNumber;
    private double roomPrice;
    private RoomType roomType;
    private boolean withBalcony;
    private String description;

    public RoomCreated() {
    }

    public RoomCreated(UUID roomId, int numberOfPerson, int roomNumber, double roomPrice, RoomType roomType, boolean withBalcony, String description) {
        this.roomId = roomId;
        this.numberOfPerson = numberOfPerson;
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.roomType = roomType;
        this.withBalcony = withBalcony;
        this.description = description;
    }

    @Override
    public String toString() {
        return "RoomCreated{" +
                "roomId='" + roomId + '\'' +
                ", numberOfPerson=" + numberOfPerson +
                ", roomNumber=" + roomNumber +
                ", roomPrice=" + roomPrice +
                ", roomType='" + roomType.toString() + '\'' +
                '}';
    }
}
