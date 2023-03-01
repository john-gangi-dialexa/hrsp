package com.dialexa.hrsp;

import java.util.List;
import java.util.Arrays;

public class Room {

    private String id;
    private String roomNumber;
   // private String nightOfAvailability;
   // private boolean availability;
    private boolean petFriendly;
   // private List<Bed> beds;
   // private String localeId;

    public Room(String id, String roomNumber, boolean petFriendly){
        this.id=id;
        this.roomNumber=roomNumber;
        this.petFriendly=petFriendly;
    }

    public static List<Room> rooms = Arrays.asList(
        new Room("Presidents' Suite", "01", false),
        new Room("Ocean View Room 1", "02", false),
        new Room("Ocean View Room 2", "03", false),
        new Room("Harborside 2 Double", "04", false),
        new Room("Harborside 1 King", "05", true),
        new Room("Harborside 2 Queen", "06", true)
    );

    public static Room getByRoomNumber(String k) {
        return rooms.stream().filter(room -> room.getRoomNumber().equals(k)).findFirst().orElse(null);
    }

    public Object getRoomNumber() {
        return this.roomNumber;
    }

    public String getId() {
        return this.id;
    }

}