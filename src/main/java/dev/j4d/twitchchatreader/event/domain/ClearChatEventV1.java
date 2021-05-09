package dev.j4d.twitchchatreader.event.domain;

import java.util.Optional;
import java.util.UUID;

public class ClearChatEventV1 {

    private final UUID id;
    private final String username;
    private final String channel;
    private final long userId;
    private final long roomId;
    private final Long banDuration;
    private final String time;

    public ClearChatEventV1(UUID id, String username, String channel, long userId, long roomId, Long banDuration, String time) {
        this.id = id;
        this.username = username;
        this.channel = channel;
        this.userId = userId;
        this.roomId = roomId;
        this.banDuration = banDuration;
        this.time = time;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getChannel() {
        return channel;
    }

    public long getUserId() {
        return userId;
    }

    public long getRoomId() {
        return roomId;
    }

    public Optional<Long> getBanDuration() {
        return Optional.ofNullable(banDuration);
    }

    public String getTime() {
        return time;
    }
}
