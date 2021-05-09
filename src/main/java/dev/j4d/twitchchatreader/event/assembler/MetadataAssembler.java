package dev.j4d.twitchchatreader.event.assembler;

import dev.j4d.twitchchatreader.event.domain.Metadata;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class MetadataAssembler {

    public Metadata assemble(JSONObject metadata) {
        return new Metadata(
                metadata.getString("eventId"),
                metadata.getString("correlationId"),
                metadata.getString("eventType"),
                metadata.getString("version"),
                metadata.getString("source"),
                metadata.getString("created"));
    }
}
