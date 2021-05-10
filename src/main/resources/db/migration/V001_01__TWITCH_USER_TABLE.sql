CREATE TABLE twitch_user
(
    user_id            BIGINT  NOT NULL PRIMARY KEY,
    username           VARCHAR NOT NULL,
    display_name       VARCHAR NOT NULL,
    first_seen         VARCHAR NOT NULL,
    first_seen_room_id BIGINT  NOT NULL
);
