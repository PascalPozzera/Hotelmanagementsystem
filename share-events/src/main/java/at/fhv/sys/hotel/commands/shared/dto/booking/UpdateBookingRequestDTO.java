package at.fhv.sys.hotel.commands.shared.dto.booking;

import jakarta.ws.rs.QueryParam;

import java.time.LocalDate;

public class UpdateBookingRequestDTO {

    @QueryParam("roomNumber")
    private String roomNumber;

    @QueryParam("startDate")
    private LocalDate startDate;

    @QueryParam("endDate")
    private LocalDate endDate;

    @QueryParam("numberOfGuests")
    private int numberOfGuests;
}
