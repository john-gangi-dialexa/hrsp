package com.dialexa.hrsp.dao;

import java.util.List;

import com.dialexa.hrsp.model.Room;

public class RoomDao {
    
    private final List<Room> Rooms;

    public RoomDao(List<Room> Rooms) {
        this.Rooms = Rooms;
    }

    public Room getRoom(String id) {
        return Rooms.stream().filter(room -> room.getRoomNumber().equals(id)).findFirst().orElse(null);
    }
}   
