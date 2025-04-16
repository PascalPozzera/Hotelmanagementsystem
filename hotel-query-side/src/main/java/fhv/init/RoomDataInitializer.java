package fhv.init;

import at.fhv.sys.hotel.commands.shared.enums.RoomType;
import fhv.models.room.RoomQueryPanacheModel;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class RoomDataInitializer {

    //Generate a set of rooms to initialize the database.

    @Transactional
    public void onStartup(@Observes StartupEvent event) {
        System.out.println("🚀 RoomDataInitializer wurde ausgeführt");

        if (RoomQueryPanacheModel.count() == 0) {
            new RoomQueryPanacheModel("R101", 2, 101, 89.90, RoomType.Studio).persist();
            new RoomQueryPanacheModel("R102", 1, 102, 59.90, RoomType.SingleRoom).persist();
            new RoomQueryPanacheModel("R103", 4, 103, 149.00, RoomType.JuniorSuite).persist();
            new RoomQueryPanacheModel("R104", 3, 104, 119.50, RoomType.Penthouse).persist();
            new RoomQueryPanacheModel("R105", 2, 105, 99.00, RoomType.TwinRoom).persist();
        }
    }
}

