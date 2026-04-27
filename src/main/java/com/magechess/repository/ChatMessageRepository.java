package com.magechess.repository;

import com.magechess.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {
    List<ChatMessage> findByRoomIdOrderByTimeAsc(String roomId);
}
