package dev.j4d.twitchchatreader.event.domain;

import java.util.UUID;

public class ChatMessageEventV1 {

    private final UUID id;
    private final String username;
    private final String displayName;
    private final String message;
    private final String channel;
    private final long userId;
    private final long roomId;
    private final boolean mod;
    private final boolean subscriber;
    private final boolean turbo;
    private final String time;

    public ChatMessageEventV1(UUID id, String username, String displayName, String message, String channel, long userId, long roomId, boolean mod, boolean subscriber, boolean turbo, String time) {
        this.id = id;
        this.username = username;
        this.displayName = displayName;
        this.message = message;
        this.channel = channel;
        this.userId = userId;
        this.roomId = roomId;
        this.mod = mod;
        this.subscriber = subscriber;
        this.turbo = turbo;
        this.time = time;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getMessage() {
        return message;
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

    public boolean isMod() {
        return mod;
    }

    public boolean isSubscriber() {
        return subscriber;
    }

    public boolean isTurbo() {
        return turbo;
    }

    public String getTime() {
        return time;
    }
}
