package dev.j4d.twitchchatreader.event.assembler;

import dev.j4d.twitchchatreader.event.domain.ClearMessageEventV1;
import dev.j4d.twitchchatreader.event.domain.RawEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Component
public class ClearMessageEventV1Assembler {

    private final TagsAssembler tagsAssembler;

    public ClearMessageEventV1Assembler(TagsAssembler tagsAssembler) {
        this.tagsAssembler = tagsAssembler;
    }

    public ClearMessageEventV1 assemble(RawEvent rawEvent) {
        final var inputParts = rawEvent.getRawData().split(" ");
        final var tags = tagsAssembler.assemble(inputParts[0]);
        final var messageBuilder = new StringBuilder();
        for (int i = 4; i < inputParts.length; i++) {
            messageBuilder.append(inputParts[i]).append(" ");
        }
        return new ClearMessageEventV1(
                UUID.fromString(rawEvent.getMetadata().getEventId()),
                UUID.fromString(tags.get("target-msg-id")),
                tags.get("login"),
                messageBuilder.substring(1),
                inputParts[3].substring(1),
                Instant.parse(rawEvent.getMetadata().getCreated()).truncatedTo(ChronoUnit.SECONDS).toString());
    }
}
