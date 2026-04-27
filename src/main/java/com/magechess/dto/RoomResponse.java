package com.magechess.dto;

import com.magechess.model.Room;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomResponse {
    private String id;
    private String name;
    private String host;
    private int players;
    private int maxPlayers;
    private String status;
    private String createdAt;

    public static RoomResponse from(Room room) {
        return new RoomResponse(
            room.getId(),
            room.getName(),
            room.getHost().getUsername(),
            room.getPlayers(),
            room.getMaxPlayers(),
            room.getStatus().name(),
            room.getCreatedAt().toString()
        );
    }
}
