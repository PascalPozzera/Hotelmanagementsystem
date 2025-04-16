//package fhv.controller;
//
//import at.fhv.sys.hotel.commands.shared.dto.room.RoomRequestDTO;
//import fhv.commands.room.CreateRoomCommand;
//import fhv.commands.room.RoomAggregate;
//import jakarta.inject.Inject;
//import jakarta.ws.rs.*;
//import jakarta.ws.rs.core.MediaType;
//
//import java.util.UUID;
//
//@Path("/api")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
//public class RoomCommandController {
//
//    @Inject
//    RoomAggregate roomAggregate;
//
//    @POST
//    @Path("/createRoom")
//    public UUID createRoom(@BeanParam RoomRequestDTO requestDTO) {
//        UUID generatedId = UUID.randomUUID();
//        return roomAggregate.handle(new CreateRoomCommand(
//                generatedId,
//                requestDTO.getNumberOfPerson(),
//                requestDTO.getRoomNumber(),
//                requestDTO.getRoomPrice(),
//                requestDTO.getRoomType(),
//                requestDTO.isWithBalcony(),
//                requestDTO.getDescription()
//        ));
//    }
//
//    @POST
//    @Path("/{roomId}/deleteRoom")
//    public String deleteRoom(@PathParam("roomId") String roomId) {
//        // TBD: delete room
//        return "Room deleted";
//    }
//
//    @POST
//    @Path("/{roomId}/updateRoom")
//    public String payBooking(@PathParam("roomId") String roomId) {
//        // TBD: update Room
//        return "Room updated";
//    }
//}
