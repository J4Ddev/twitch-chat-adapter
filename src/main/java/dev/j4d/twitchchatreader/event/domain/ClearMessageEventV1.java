package dev.j4d.twitchchatreader.event.domain;

import java.util.UUID;

public class ClearMessageEventV1 {

    private final UUID id;
    private final UUID chatMessageId;
    private final String username;
    private final String message;
    private final String channel;
    private final String time;

    public ClearMessageEventV1(UUID id, UUID chatMessageId, String username, String message, String channel, String time) {
        this.id = id;
        this.chatMessageId = chatMessageId;
        this.username = username;
        this.message = message;
        this.channel = channel;
        this.time = time;
    }

    public UUID getId() {
        return id;
    }

    public UUID getChatMessageId() {
        return chatMessageId;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    public String getChannel() {
        return channel;
    }

    public String getTime() {
        return time;
    }
}
