package com.magechess.service;

import com.magechess.dto.ChatMessageResponse;
import com.magechess.model.ChatMessage;
import com.magechess.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;

    public List<ChatMessageResponse> getMessages(String roomId) {
        return chatMessageRepository.findByRoomIdOrderByTimeAsc(roomId)
                .stream()
                .map(ChatMessageResponse::from)
                .toList();
    }

    public ChatMessageResponse saveMessage(String roomId, String sender, String text) {
        ChatMessage message = ChatMessage.builder()
                .roomId(roomId)
                .sender(sender)
                .text(text)
                .build();
        chatMessageRepository.save(message);
        return ChatMessageResponse.from(message);
    }
}
