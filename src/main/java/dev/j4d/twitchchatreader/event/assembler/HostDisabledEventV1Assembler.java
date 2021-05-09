package dev.j4d.twitchchatreader.event.assembler;

import dev.j4d.twitchchatreader.event.domain.HostDisabledEventV1;
import dev.j4d.twitchchatreader.event.domain.RawEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Component
public class HostDisabledEventV1Assembler {

    public HostDisabledEventV1 assemble(RawEvent rawEvent) {
        final var inputParts = rawEvent.getRawData().split(" ");
        return new HostDisabledEventV1(
                UUID.fromString(rawEvent.getMetadata().getEventId()),
                inputParts[2].substring(1),
                Instant.parse(rawEvent.getMetadata().getCreated()).truncatedTo(ChronoUnit.SECONDS).toString());
    }
}
