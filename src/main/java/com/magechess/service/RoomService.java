package com.magechess.service;

import com.magechess.dto.CreateRoomRequest;
import com.magechess.dto.RoomResponse;
import com.magechess.model.Room;
import com.magechess.model.User;
import com.magechess.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public List<RoomResponse> getAllRooms() {
        return roomRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(RoomResponse::from)
                .toList();
    }

    public RoomResponse createRoom(CreateRoomRequest request, User host) {
        Room room = Room.builder()
                .name(request.getName())
                .host(host)
                .build();
        roomRepository.save(room);
        return RoomResponse.from(room);
    }

    public RoomResponse joinRoom(String roomId, User guest) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Sala não encontrada"));

        if (room.getGuest() != null) {
            throw new RuntimeException("Sala cheia");
        }

        if (room.getHost().getId().equals(guest.getId())) {
            throw new RuntimeException("Você já está nesta sala");
        }

        room.setGuest(guest);
        room.setStatus(Room.RoomStatus.IN_PROGRESS);
        roomRepository.save(room);
        return RoomResponse.from(room);
    }

    public RoomResponse getRoom(String roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Sala não encontrada"));
        return RoomResponse.from(room);
    }
}
