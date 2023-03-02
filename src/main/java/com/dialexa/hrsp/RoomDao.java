package com.dialexa.hrsp;

import java.util.List;

public class RoomDao {
    
    private final List<Room> Rooms;

    public RoomDao(List<Room> Rooms) {
        this.Rooms = Rooms;
    }

    public Room getRoom(String id) {
        return Rooms.stream().filter(room -> room.getRoomNumber().equals(id)).findFirst().orElse(null);
    }
}   
