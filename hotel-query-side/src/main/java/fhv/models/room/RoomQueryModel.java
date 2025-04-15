package fhv.models.room;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RoomQueryModel {

    @Id
    private String roomId;
    private String name;
    private RoomType roomType;
    private int floor;
    private int numberOfBeds;
    private int roomNumber;
    private int numberOfPerson;
    private double roomPrice;

    @Override
    public String toString() {
        return "RoomQueryModel{" +
                "roomId='" + roomId + '\'' +
                ", numberOfPerson=" + numberOfPerson +
                ", roomNumber=" + roomNumber +
                ", roomPrice=" + roomPrice +
                ", roomType='" + roomType.toString() + '\'' +
                '}';
    }
}

