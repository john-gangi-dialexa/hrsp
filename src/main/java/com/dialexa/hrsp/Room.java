package com.dialexa.hrsp;

import java.util.List;
import java.util.Arrays;

public class Room {

    private String id;
    private String roomNumber;
   // private String nightOfAvailability;
   // private boolean availability;
   // private boolean petFriendly;
   // private List<Bed> beds;
   // private String localeId;

    public Room(String id, String roomNumber){
        this.id=id;
        this.roomNumber=roomNumber;
    }

    public static List<Room> rooms = Arrays.asList(
        new Room("Presidents' Suite", "01"),
        new Room("Ocean View Room 1", "02"),
        new Room("Ocean View Room 2", "03"),
        new Room("Harborside 2 Double", "04"),
        new Room("Harborside 1 King", "05"),
        new Room("Harborside 2 Queen", "06")
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