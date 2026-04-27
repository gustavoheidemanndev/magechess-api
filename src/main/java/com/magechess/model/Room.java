package com.magechess.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rooms")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "host_id", nullable = false)
    private User host;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "guest_id")
    private User guest;

    @Builder.Default
    private int maxPlayers = 2;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private RoomStatus status = RoomStatus.WAITING;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    public int getPlayers() {
        return 1 + (guest != null ? 1 : 0);
    }

    public enum RoomStatus {
        WAITING, IN_PROGRESS, FINISHED
    }
}
