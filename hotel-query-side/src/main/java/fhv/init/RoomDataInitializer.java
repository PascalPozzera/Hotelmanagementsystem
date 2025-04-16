package fhv.init;

import at.fhv.sys.hotel.commands.shared.enums.RoomType;
import fhv.models.room.RoomQueryPanacheModel;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.transaction.Transactional;

import java.util.UUID;

@ApplicationScoped
public class RoomDataInitializer {

    //Generate a set of rooms to initialize the database.

    @Transactional
    public void onStartup(@Observes StartupEvent event) {
        System.out.println("🚀 RoomDataInitializer wurde ausgeführt");

        if (RoomQueryPanacheModel.count() == 0) {
            new RoomQueryPanacheModel(UUID.randomUUID(), 2, 101, 89.90, RoomType.Studio, false, "Cozy studio with a view of the courtyard.").persist();
            new RoomQueryPanacheModel(UUID.randomUUID(), 4, 102, 149.50, RoomType.Suite, true, "Luxurious suite with private balcony and lounge area.").persist();
            new RoomQueryPanacheModel(UUID.randomUUID(), 2, 103, 119.00, RoomType.JuniorSuite, true, "Modern junior suite perfect for couples.").persist();
            new RoomQueryPanacheModel(UUID.randomUUID(), 1, 104, 79.00, RoomType.SingleRoom, false, "Compact single room ideal for solo travelers.").persist();
            new RoomQueryPanacheModel(UUID.randomUUID(), 2, 105, 99.99, RoomType.TwinRoom, false, "Comfortable twin room with separate beds.").persist();
            new RoomQueryPanacheModel(UUID.randomUUID(), 2, 106, 89.00, RoomType.Studio, true, "Bright studio with balcony and workspace.").persist();
            new RoomQueryPanacheModel(UUID.randomUUID(), 3, 107, 159.00, RoomType.Suite, true, "Spacious suite with a panoramic mountain view.").persist();
            new RoomQueryPanacheModel(UUID.randomUUID(), 2, 108, 129.00, RoomType.JuniorSuite, false, "Stylish junior suite with modern interior.").persist();
            new RoomQueryPanacheModel(UUID.randomUUID(), 1, 109, 69.00, RoomType.SingleRoom, false, "Budget-friendly room near the entrance.").persist();
            new RoomQueryPanacheModel(UUID.randomUUID(), 2, 110, 109.00, RoomType.TwinRoom, true, "Twin room with balcony and garden view.").persist();
            new RoomQueryPanacheModel(UUID.randomUUID(), 4, 111, 189.00, RoomType.Penthouse, true, "Exclusive penthouse with rooftop terrace.").persist();
            new RoomQueryPanacheModel(UUID.randomUUID(), 2, 112, 92.00, RoomType.Studio, false, "Studio apartment with kitchenette and sofa bed.").persist();
            new RoomQueryPanacheModel(UUID.randomUUID(), 4, 113, 149.99, RoomType.Suite, false, "Classic suite with elegant furniture.").persist();
            new RoomQueryPanacheModel(UUID.randomUUID(), 2, 114, 124.00, RoomType.JuniorSuite, true, "Junior suite with smart TV and minibar.").persist();
            new RoomQueryPanacheModel(UUID.randomUUID(), 1, 115, 74.50, RoomType.SingleRoom, true, "Single room with balcony and workspace.").persist();
            new RoomQueryPanacheModel(UUID.randomUUID(), 2, 116, 104.00, RoomType.TwinRoom, false, "Spacious twin room with large windows.").persist();
            new RoomQueryPanacheModel(UUID.randomUUID(), 3, 117, 199.00, RoomType.Penthouse, true, "Top-floor penthouse with private jacuzzi.").persist();
            new RoomQueryPanacheModel(UUID.randomUUID(), 2, 118, 95.00, RoomType.Studio, false, "Quiet studio near the elevator.").persist();
            new RoomQueryPanacheModel(UUID.randomUUID(), 4, 119, 139.00, RoomType.Suite, true, "Elegant suite with dining area and balcony.").persist();
            new RoomQueryPanacheModel(UUID.randomUUID(), 2, 120, 121.00, RoomType.JuniorSuite, false, "Comfortable junior suite with stylish decor.").persist();


        }
    }
}

