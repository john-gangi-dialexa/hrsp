package com.dialexa.hrsp;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class RoomController {

    @QueryMapping
    public Room roomByRoomNumber(@Argument String roomNumber) {
        return Room.getByRoomNumber(roomNumber);
    }

}