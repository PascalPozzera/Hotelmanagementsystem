package at.fhv.sys.hotel.commands.shared.enums;

public enum RoomType {
    Studio("Studio"),
    Suite("Suite"),
    JuniorSuite("Junior Suite"),
    Penthouse("Penthouse"),
    SingleRoom("Single Room"),
    TwinRoom("Twin Room");

    private final String displayName;

    RoomType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

