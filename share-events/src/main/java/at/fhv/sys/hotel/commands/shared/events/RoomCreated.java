package at.fhv.sys.hotel.commands.shared.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RoomCreated {

    private String roomId;
    private int numberOfPerson;
    private int roomNumber;
    private double roomPrice;
    private String roomType;

    public RoomCreated() {
    }

    public RoomCreated(String roomId, int numberOfPerson, int roomNumber, double roomPrice, String roomType) {
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
                ", roomType='" + roomType + '\'' +
                '}';
    }
}
