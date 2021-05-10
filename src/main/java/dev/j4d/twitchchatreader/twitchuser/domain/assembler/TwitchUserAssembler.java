package dev.j4d.twitchchatreader.twitchuser.domain.assembler;

import dev.j4d.twitchchatreader.event.domain.ChatMessageEventV1;
import dev.j4d.twitchchatreader.twitchuser.domain.TwitchUser;
import org.springframework.stereotype.Component;

@Component
public class TwitchUserAssembler {

    public TwitchUser assemble(ChatMessageEventV1 event) {
        return new TwitchUser(event.getUserId(), event.getUsername(), event.getDisplayName(), event.getTime(), event.getRoomId());
    }
}
