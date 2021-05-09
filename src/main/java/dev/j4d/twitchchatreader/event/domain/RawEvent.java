package dev.j4d.twitchchatreader.event.domain;

public class RawEvent {

    private final Metadata metadata;
    private final String rawData;

    public RawEvent(Metadata metadata, String rawData) {
        this.metadata = metadata;
        this.rawData = rawData;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public String getRawData() {
        return rawData;
    }
}
