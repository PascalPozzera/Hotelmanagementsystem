package at.fhv.sys.hotel.commands.shared.events;

import at.fhv.sys.hotel.commands.shared.enums.RoomType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RoomCreated {

    private String roomId;
    private int numberOfPerson;
    private int roomNumber;
    private double roomPrice;
    private RoomType roomType;

    public RoomCreated() {
    }

    public RoomCreated(String roomId, int numberOfPerson, int roomNumber, double roomPrice, RoomType roomType) {
        this.roomId = roomId;
        this.numberOfPerson = numberOfPerson;
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.roomType = roomType;
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
