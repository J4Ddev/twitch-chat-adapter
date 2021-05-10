package dev.j4d.twitchchatreader.event.consumer;

import dev.j4d.twitchchatreader.event.assembler.*;
import dev.j4d.twitchchatreader.twitchuser.service.TwitchUserService;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {

    private final RawEventAssembler rawEventAssembler;
    private final ChatMessageEventV1Assembler chatMessageEventV1Assembler;
    private final HostEnabledEventV1Assembler hostEnabledEventV1Assembler;
    private final HostDisabledEventV1Assembler hostDisabledEventV1Assembler;
    private final ClearChatEventV1Assembler clearChatEventV1Assembler;
    private final ClearMessageEventV1Assembler clearMessageEventV1Assembler;
    private final TwitchUserService twitchUserService;

    public EventConsumer(RawEventAssembler rawEventAssembler,
                         ChatMessageEventV1Assembler chatMessageEventV1Assembler,
                         HostEnabledEventV1Assembler hostEnabledEventV1Assembler,
                         HostDisabledEventV1Assembler hostDisabledEventV1Assembler,
                         ClearChatEventV1Assembler clearChatEventV1Assembler,
                         ClearMessageEventV1Assembler clearMessageEventV1Assembler,
                         TwitchUserService twitchUserService) {
        this.rawEventAssembler = rawEventAssembler;
        this.chatMessageEventV1Assembler = chatMessageEventV1Assembler;
        this.hostEnabledEventV1Assembler = hostEnabledEventV1Assembler;
        this.hostDisabledEventV1Assembler = hostDisabledEventV1Assembler;
        this.clearChatEventV1Assembler = clearChatEventV1Assembler;
        this.clearMessageEventV1Assembler = clearMessageEventV1Assembler;
        this.twitchUserService = twitchUserService;
    }

    public void consumeChatMessageEvent(String eventJson) {
        final var rawEvent = rawEventAssembler.assemble(eventJson);
        final var metadata = rawEvent.getMetadata();
        final var eventType = metadata.getEventType();
        final var version = metadata.getVersion();
        if ("CHAT_MESSAGE".equals(eventType) && "V1".equals(version)) {
            final var event = chatMessageEventV1Assembler.assemble(rawEvent);
            twitchUserService.handleChatMessage(event);
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
            final var event = hostEnabledEventV1Assembler.assemble(rawEvent);
            System.out.printf("%s HOST: %s hosted %s with %s users%n", event.getTime(), event.getChannel(), event.getTargetChannel(), event.getUserCount());
        } else if ("HOST_DISABLED".equals(eventType) && "V1".equals(version)) {
            final var event = hostDisabledEventV1Assembler.assemble(rawEvent);
            System.out.printf("%s HOST: %s exited host mode%n", event.getTime(), event.getChannel());
        }
    }

    public void consumePunishmentEvent(String eventJson) {
        final var rawEvent = rawEventAssembler.assemble(eventJson);
        final var metadata = rawEvent.getMetadata();
        final var eventType = metadata.getEventType();
        final var version = metadata.getVersion();
        if ("CLEAR_CHAT".equals(eventType) && "V1".equals(version)) {
            final var event = clearChatEventV1Assembler.assemble(rawEvent);
            final var banDuration = event.getBanDuration().map(b -> "for " + b + " seconds").orElse("permanently");
            System.out.printf("%s CLEAR_CHAT: %s in channel %s " + banDuration + "%n", event.getTime(), event.getUsername(), event.getChannel());
        } else if ("GLOBAL_CLEAR_CHAT".equals(eventType) && "V1".equals(version)) {

        } else if ("CLEAR_MESSAGE".equals(eventType) && "V1".equals(version)) {
            final var event = clearMessageEventV1Assembler.assemble(rawEvent);
            System.out.printf("%s CLEAR_MESSAGE [%s] %s: %s%n", event.getTime(), event.getChannel(), event.getUsername(), event.getMessage());
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
