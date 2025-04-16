package at.fhv.sys.hotel.commands.shared.dto.booking;

import jakarta.ws.rs.QueryParam;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingRequestDTO {

    @QueryParam("customerId")
    private long customerId;

    @QueryParam("roomNumber")
    private int roomNumber;

    @QueryParam("startDate")
    private LocalDate startDate;

    @QueryParam("endDate")
    private LocalDate endDate;

    @QueryParam("numberOfGuests")
    private int numberOfGuests;
}