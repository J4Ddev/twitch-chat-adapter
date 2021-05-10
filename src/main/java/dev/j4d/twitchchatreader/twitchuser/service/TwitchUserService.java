package dev.j4d.twitchchatreader.twitchuser.service;

import dev.j4d.twitchchatreader.event.domain.ChatMessageEventV1;
import dev.j4d.twitchchatreader.twitchuser.domain.assembler.TwitchUserAssembler;
import dev.j4d.twitchchatreader.twitchuser.repository.TwitchUserRepository;
import org.springframework.stereotype.Service;

@Service
public class TwitchUserService {

    private final TwitchUserRepository repository;
    private final TwitchUserAssembler assembler;

    public TwitchUserService(TwitchUserRepository repository, TwitchUserAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    public void handleChatMessage(ChatMessageEventV1 event) {
        repository.get(event.getUserId()).ifPresentOrElse(
                twitchUser -> {
                    final var newUsername = event.getUsername();
                    final var newDisplayName = event.getDisplayName();
                    final var username = twitchUser.getUsername();
                    final var displayName = twitchUser.getDisplayName();
                    final var userId = twitchUser.getUserId();
                    if (!username.equals(newUsername)) {
                        repository.updateNames(userId, newUsername, newDisplayName);
                    } else if (!displayName.equals(newDisplayName)) {
                        repository.updateDisplayName(userId, newDisplayName);
                    }
                },
                () -> repository.save(assembler.assemble(event)));
    }
}
