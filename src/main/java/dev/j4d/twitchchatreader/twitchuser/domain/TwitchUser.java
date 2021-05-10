package dev.j4d.twitchchatreader.twitchuser.domain;

public class TwitchUser {

    private final long userId;
    private final String username;
    private final String displayName;
    private final String firstSeen;
    private final long firstSeenRoomId;

    public TwitchUser(long userId, String username, String displayName, String firstSeen, long firstSeenRoomId) {
        this.userId = userId;
        this.username = username;
        this.displayName = displayName;
        this.firstSeen = firstSeen;
        this.firstSeenRoomId = firstSeenRoomId;
    }

    public long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getFirstSeen() {
        return firstSeen;
    }

    public long getFirstSeenRoomId() {
        return firstSeenRoomId;
    }
}
