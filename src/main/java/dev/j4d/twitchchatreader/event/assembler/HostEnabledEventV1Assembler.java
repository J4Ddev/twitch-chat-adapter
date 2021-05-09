package dev.j4d.twitchchatreader.event.assembler;

import dev.j4d.twitchchatreader.event.domain.HostEnabledEventV1;
import dev.j4d.twitchchatreader.event.domain.RawEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Component
public class HostEnabledEventV1Assembler {

    public HostEnabledEventV1 assemble(RawEvent rawEvent) {
        final var inputParts = rawEvent.getRawData().split(" ");
        return new HostEnabledEventV1(
                UUID.fromString(rawEvent.getMetadata().getEventId()),
                inputParts[2].substring(1),
                inputParts[3].substring(1),
                Long.parseLong(Optional.of(inputParts[4]).filter(u -> !"-".equals(u)).orElse("0")),
                Instant.parse(rawEvent.getMetadata().getCreated()).truncatedTo(ChronoUnit.SECONDS).toString());
    }
}
