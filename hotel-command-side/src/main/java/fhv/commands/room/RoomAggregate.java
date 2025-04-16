package fhv.commands.room;

import at.fhv.sys.hotel.commands.shared.events.RoomCreated;
import fhv.client.EventBusClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
public class RoomAggregate {

    @Inject
    @RestClient
    EventBusClient eventClient;

    public UUID handle(CreateRoomCommand command) {
        RoomCreated event = new RoomCreated(
                command.roomId(),
                command.numberOfPersons(),
                command.roomNumber(),
                command.roomPrice(),
                command.roomType(),
                command.hasBalcony(),
                command.description());

        Logger.getAnonymousLogger().info(eventClient.processRoomCreatedEvent(event).toString());

        return command.roomId();
    }

}
