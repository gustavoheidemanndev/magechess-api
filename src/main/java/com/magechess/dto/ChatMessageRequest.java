package com.magechess.dto;

import lombok.Data;

@Data
public class ChatMessageRequest {
    private String roomId;
    private String text;
}
