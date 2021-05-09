package dev.j4d.twitchchatreader.event.assembler;

import dev.j4d.twitchchatreader.event.domain.ClearChatEventV1;
import dev.j4d.twitchchatreader.event.domain.RawEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Component
public class ClearChatEventV1Assembler {

    private final TagsAssembler tagsAssembler;

    public ClearChatEventV1Assembler(TagsAssembler tagsAssembler) {
        this.tagsAssembler = tagsAssembler;
    }

    public ClearChatEventV1 assemble(RawEvent rawEvent) {
        final var inputParts = rawEvent.getRawData().split(" ");
        final var tags = tagsAssembler.assemble(inputParts[0]);

        return new ClearChatEventV1(
                UUID.fromString(rawEvent.getMetadata().getEventId()),
                inputParts[4].substring(1),
                inputParts[3].substring(1),
                Long.parseLong(tags.get("target-user-id")),
                Long.parseLong(tags.get("room-id")),
                Optional.ofNullable(tags.get("ban-duration")).map(Long::parseLong).orElse(null),
                Instant.parse(rawEvent.getMetadata().getCreated()).truncatedTo(ChronoUnit.SECONDS).toString());
    }
}
