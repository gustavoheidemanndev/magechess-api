package com.magechess.controller;

import com.magechess.dto.MoveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class GameController {

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/game.move")
    public void handleMove(@Payload MoveRequest move, Principal principal) {
        String player = principal != null ? principal.getName() : "anonymous";
        messagingTemplate.convertAndSend("/topic/game/" + move.getRoomId(),
                Map.of("from", move.getFrom(), "to", move.getTo(), "player", player));
    }

    @MessageMapping("/game.resign")
    public void handleResign(@Payload Map<String, String> payload, Principal principal) {
        String player = principal != null ? principal.getName() : "anonymous";
        String roomId = payload.get("roomId");
        messagingTemplate.convertAndSend("/topic/game/" + roomId,
                Map.of("type", "resign", "player", player));
    }

    @MessageMapping("/game.draw-offer")
    public void handleDrawOffer(@Payload Map<String, String> payload, Principal principal) {
        String player = principal != null ? principal.getName() : "anonymous";
        String roomId = payload.get("roomId");
        messagingTemplate.convertAndSend("/topic/game/" + roomId,
                Map.of("type", "draw-offer", "player", player));
    }
}
