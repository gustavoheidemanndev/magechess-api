package com.magechess.dto;

import com.magechess.model.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatMessageResponse {
    private String id;
    private String roomId;
    private String sender;
    private String text;
    private String time;

    public static ChatMessageResponse from(ChatMessage msg) {
        return new ChatMessageResponse(
            msg.getId(),
            msg.getRoomId(),
            msg.getSender(),
            msg.getText(),
            msg.getTime().toLocalTime().toString().substring(0, 5)
        );
    }
}
