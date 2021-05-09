package dev.j4d.twitchchatreader.event.assembler;

import dev.j4d.twitchchatreader.event.domain.RawEvent;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class RawEventAssembler {

    private final MetadataAssembler metadataAssembler;

    public RawEventAssembler(MetadataAssembler metadataAssembler) {
        this.metadataAssembler = metadataAssembler;
    }

    public RawEvent assemble(String event) {
        final var eventJson = new JSONObject(event);
        final var metadataJson = eventJson.getJSONObject("metadata");
        final var dataJson = eventJson.getJSONObject("data");
        final var metadata = metadataAssembler.assemble(metadataJson);
        final var rawData = new String(Base64.getDecoder().decode(dataJson.getString("base64EncodedRawData")));
        return new RawEvent(metadata, rawData);
    }
}
