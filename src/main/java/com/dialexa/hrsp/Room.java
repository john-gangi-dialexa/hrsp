package com.dialexa.hrsp;

import java.util.List;
import java.util.Arrays;

public class Room {

    private String id;
    private int roomNumber;
   // private String nightOfAvailability;
   // private boolean availability;
   // private boolean petFriendly;
   // private List<Bed> beds;
   // private String localeId;

    public Room(String id, int roomNumber){
        this.id=id;
        this.roomNumber=roomNumber;
    }

    public static List<Room> rooms = Arrays.asList(
        new Room("Base Cabin A", 01),
        new Room("Base Cabin B", 02),
        new Room("Base Cabin C", 03),
        new Room("Base Cabin D", 04),
        new Room("Base Cabin E", 05),
        new Room("Base Cabin F", 06)
    );

    public static Room getByRoomNumber(int k) {
        return rooms.stream().filter(room -> room.getRoomNumber().equals(k)).findFirst().orElse(null);
    }

    public Object getRoomNumber() {
        Object k = (Integer) roomNumber;
        return k;
    }

    public String getId() {
        return this.id;
    }

}