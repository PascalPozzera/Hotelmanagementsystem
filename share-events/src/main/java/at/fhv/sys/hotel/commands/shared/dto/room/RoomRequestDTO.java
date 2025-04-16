package at.fhv.sys.hotel.commands.shared.dto.room;

import at.fhv.sys.hotel.commands.shared.enums.RoomType;
import jakarta.ws.rs.QueryParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomRequestDTO {

    @QueryParam("roomId")
    private String roomId;

    @QueryParam("name")
    private String name;

    @QueryParam("roomType")
    private RoomType roomType;

    @QueryParam("floor")
    private int floor;

    @QueryParam("numberOfBeds")
    private int numberOfBeds;

    @QueryParam("roomNumber")
    private int roomNumber;

    @QueryParam("numberOfPerson")
    private int numberOfPerson;

    @QueryParam("roomPrice")
    private double roomPrice;

    @QueryParam("hasBalcony")
    private boolean withBalcony;

    @QueryParam("hasBalcony")
    private String description;
}
