package com.dialexa.hrsp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class GraphqlConfiguration {

    @Bean
    public RoomDao roomDao() {
        
         final List<Room> rooms = Arrays.asList(
            new Room("Presidents' Suite", "01", false),
            new Room("Ocean View Room 1", "02", false),
            new Room("Ocean View Room 2", "03", false),
            new Room("Harborside 2 Double", "04", false),
            new Room("Harborside 1 King", "05", true),
            new Room("Harborside 2 Queen", "06", true)
        );

        return new RoomDao(rooms);
    }

}
