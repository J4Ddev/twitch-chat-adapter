package dev.j4d.twitchchatreader.event.consumer;

import dev.j4d.twitchchatreader.event.assembler.ChatMessageEventV1Assembler;
import dev.j4d.twitchchatreader.event.assembler.ClearChatEventV1Assembler;
import dev.j4d.twitchchatreader.event.assembler.RawEventAssembler;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {

    private final RawEventAssembler rawEventAssembler;
    private final ChatMessageEventV1Assembler chatMessageEventV1Assembler;
    private final ClearChatEventV1Assembler clearChatEventV1Assembler;

    public EventConsumer(RawEventAssembler rawEventAssembler,
                         ChatMessageEventV1Assembler chatMessageEventV1Assembler,
                         ClearChatEventV1Assembler clearChatEventV1Assembler) {
        this.rawEventAssembler = rawEventAssembler;
        this.chatMessageEventV1Assembler = chatMessageEventV1Assembler;
        this.clearChatEventV1Assembler = clearChatEventV1Assembler;
    }

    public void consumeChatMessageEvent(String eventJson) {
        final var rawEvent = rawEventAssembler.assemble(eventJson);
        final var metadata = rawEvent.getMetadata();
        final var eventType = metadata.getEventType();
        final var version = metadata.getVersion();
        if ("CHAT_MESSAGE".equals(eventType) && "V1".equals(version)) {
            final var event = chatMessageEventV1Assembler.assemble(rawEvent);
            System.out.printf("%s [%s] %s: %s%n", event.getTime(), event.getChannel(), event.getUsername(), event.getMessage());
        }
    }

    public void consumePresenceEvent(String eventJson) {
        final var rawEvent = rawEventAssembler.assemble(eventJson);
        final var metadata = rawEvent.getMetadata();
        final var eventType = metadata.getEventType();
        final var version = metadata.getVersion();
        if ("USER_JOIN".equals(eventType) && "V1".equals(version)) {

        } else if ("USER_LEAVE".equals(eventType) && "V1".equals(version)) {

        }
    }

    public void consumeHostEvent(String eventJson) {
        final var rawEvent = rawEventAssembler.assemble(eventJson);
        final var metadata = rawEvent.getMetadata();
        final var eventType = metadata.getEventType();
        final var version = metadata.getVersion();
        if ("HOST_ENABLED".equals(eventType) && "V1".equals(version)) {

        } else if ("HOST_DISABLED".equals(eventType) && "V1".equals(version)) {

        }
    }

    public void consumePunishmentEvent(String eventJson) {
        final var rawEvent = rawEventAssembler.assemble(eventJson);
        final var metadata = rawEvent.getMetadata();
        final var eventType = metadata.getEventType();
        final var version = metadata.getVersion();
        if ("CLEAR_CHAT".equals(eventType) && "V1".equals(version)) {
            final var event = clearChatEventV1Assembler.assemble(rawEvent);
            System.out.printf(
                    "%s CLEARCHAT: %s was banned from channel %s " + event.getBanDuration().map(banDuration -> "for " + banDuration + " seconds%n").orElse("permanently%n"),
                    event.getTime(), event.getUsername(), event.getChannel());
        } else if ("GLOBAL_CLEAR_CHAT".equals(eventType) && "V1".equals(version)) {

        } else if ("CLEAR_MESSAGE".equals(eventType) && "V1".equals(version)) {

        }
    }

    public void consumeUserNoticeEvent(String eventJson) {
        final var rawEvent = rawEventAssembler.assemble(eventJson);
        final var metadata = rawEvent.getMetadata();
        final var eventType = metadata.getEventType();
        final var version = metadata.getVersion();
        if ("USER_NOTICE".equals(eventType) && "V1".equals(version)) {

        }
    }
}
