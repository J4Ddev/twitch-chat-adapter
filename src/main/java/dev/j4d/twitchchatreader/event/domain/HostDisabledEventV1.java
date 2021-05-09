package dev.j4d.twitchchatreader.event.domain;

import java.util.UUID;

public class HostDisabledEventV1 {

    private final UUID id;
    private final String channel;
    private final String time;

    public HostDisabledEventV1(UUID id, String channel, String time) {
        this.id = id;
        this.channel = channel;
        this.time = time;
    }

    public UUID getId() {
        return id;
    }

    public String getChannel() {
        return channel;
    }

    public String getTime() {
        return time;
    }
}
