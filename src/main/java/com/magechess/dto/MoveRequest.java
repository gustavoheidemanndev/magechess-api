package com.magechess.dto;

import lombok.Data;

@Data
public class MoveRequest {
    private String roomId;
    private String from;
    private String to;
}
