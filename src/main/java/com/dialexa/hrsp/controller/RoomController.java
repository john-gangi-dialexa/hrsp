package com.dialexa.hrsp.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.dialexa.hrsp.dao.RoomDao;
import com.dialexa.hrsp.model.Room;

@Controller
public class RoomController {

    private final RoomDao roomDao;

    public RoomController(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @QueryMapping
    public Room roomByRoomNumber(@Argument String roomNumber) {
        return roomDao.getRoom(roomNumber);
    }

}