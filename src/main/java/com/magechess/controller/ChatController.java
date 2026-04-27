package com.magechess.controller;

import com.magechess.dto.ChatMessageRequest;
import com.magechess.dto.ChatMessageResponse;
import com.magechess.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    @GetMapping("/api/chat/{roomId}")
    public ResponseEntity<List<ChatMessageResponse>> getMessages(@PathVariable String roomId) {
        return ResponseEntity.ok(chatService.getMessages(roomId));
    }

    @MessageMapping("/chat.send")
    public void sendMessage(@Payload ChatMessageRequest request, Principal principal) {
        String sender = principal != null ? principal.getName() : "anonymous";
        ChatMessageResponse response = chatService.saveMessage(
                request.getRoomId(), sender, request.getText());
        messagingTemplate.convertAndSend("/topic/chat/" + request.getRoomId(), response);
    }
}
