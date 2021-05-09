package dev.j4d.twitchchatreader.event.domain;

import java.util.UUID;

public class HostEnabledEventV1 {

    private final UUID id;
    private final String channel;
    private final String targetChannel;
    private final long userCount;
    private final String time;

    public HostEnabledEventV1(UUID id, String channel, String targetChannel, long userCount, String time) {
        this.id = id;
        this.channel = channel;
        this.targetChannel = targetChannel;
        this.userCount = userCount;
        this.time = time;
    }

    public UUID getId() {
        return id;
    }

    public String getChannel() {
        return channel;
    }

    public String getTargetChannel() {
        return targetChannel;
    }

    public long getUserCount() {
        return userCount;
    }

    public String getTime() {
        return time;
    }
}
