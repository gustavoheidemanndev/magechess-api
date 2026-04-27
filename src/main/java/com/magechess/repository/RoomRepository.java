package com.magechess.repository;

import com.magechess.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, String> {
    List<Room> findByStatusOrderByCreatedAtDesc(Room.RoomStatus status);
    List<Room> findAllByOrderByCreatedAtDesc();
}
