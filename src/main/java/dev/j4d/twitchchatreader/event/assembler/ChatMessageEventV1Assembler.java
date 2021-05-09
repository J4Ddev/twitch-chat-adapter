package dev.j4d.twitchchatreader.event.assembler;

import dev.j4d.twitchchatreader.event.domain.ChatMessageEventV1;
import dev.j4d.twitchchatreader.event.domain.RawEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Component
public class ChatMessageEventV1Assembler {

    private final TagsAssembler tagsAssembler;

    public ChatMessageEventV1Assembler(TagsAssembler tagsAssembler) {
        this.tagsAssembler = tagsAssembler;
    }

    public ChatMessageEventV1 assemble(RawEvent rawEvent) {
        final var inputParts = rawEvent.getRawData().split(" ");
        final var tags = tagsAssembler.assemble(inputParts[0]);
        final var messageBuilder = new StringBuilder();
        for (int i = 4; i < inputParts.length; i++) {
            messageBuilder.append(inputParts[i]).append(" ");
        }
        return new ChatMessageEventV1(
                UUID.fromString(tags.get("id")),
                inputParts[1].split("!")[0].substring(1),
                tags.get("display-name"),
                messageBuilder.substring(1).trim(),
                inputParts[3].substring(1),
                Long.parseLong(tags.get("user-id")),
                Long.parseLong(tags.get("room-id")),
                tags.get("mod").equals("1"),
                tags.get("subscriber").equals("1"),
                tags.get("turbo").equals("1"),
                Instant.parse(rawEvent.getMetadata().getCreated()).truncatedTo(ChronoUnit.SECONDS).toString());
    }
}
