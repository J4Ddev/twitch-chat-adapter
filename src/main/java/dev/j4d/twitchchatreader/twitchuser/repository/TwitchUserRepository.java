package dev.j4d.twitchchatreader.twitchuser.repository;

import dev.j4d.twitchchatreader.twitchuser.domain.TwitchUser;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class TwitchUserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public TwitchUserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(TwitchUser twitchUser) {
        jdbcTemplate.update("INSERT INTO twitch_user (user_id, username, display_name, first_seen, first_seen_room_id) " +
                        "VALUES (:user_id, :username, :display_name, :first_seen, :first_seen_room_id)",
                new MapSqlParameterSource()
                        .addValue("user_id", twitchUser.getUserId())
                        .addValue("username", twitchUser.getUsername())
                        .addValue("display_name", twitchUser.getDisplayName())
                        .addValue("first_seen", twitchUser.getFirstSeen())
                        .addValue("first_seen_room_id", twitchUser.getFirstSeenRoomId()));
    }

    public void updateDisplayName(long userId, String displayName) {
        jdbcTemplate.update("UPDATE twitch_user SET display_name = :display_name WHERE user_id = :user_id",
                new MapSqlParameterSource()
                        .addValue("display_name", displayName)
                        .addValue("user_id", userId));
    }

    public void updateNames(long userId, String username, String displayName) {
        jdbcTemplate.update("UPDATE twitch_user SET username = :username, display_name = :display_name WHERE user_id = :user_id",
                new MapSqlParameterSource()
                        .addValue("username", username)
                        .addValue("display_name", displayName)
                        .addValue("user_id", userId));
    }

    public Optional<TwitchUser> get(long userId) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(
                    "SELECT user_id, username, display_name, first_seen, first_seen_room_id FROM twitch_user WHERE user_id = :user_id",
                    new MapSqlParameterSource().addValue("user_id", userId),
                    this::mapRow));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }

    private TwitchUser mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new TwitchUser(
                resultSet.getLong("user_id"),
                resultSet.getString("username"),
                resultSet.getString("display_name"),
                resultSet.getString("first_seen"),
                resultSet.getLong("first_seen_room_id"));
    }
}
