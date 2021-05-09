package dev.j4d.twitchchatreader.event.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageListener {

    private static final Logger logger = LoggerFactory.getLogger(ChatMessageListener.class);

    private final EventConsumer eventConsumer;

    public ChatMessageListener(EventConsumer eventConsumer) {
        this.eventConsumer = eventConsumer;
    }

    @KafkaListener(topics = "${kafka.topic.chat-message.name}")
    public void listen(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        final var key = record.key();
        final var partition = record.partition();
        final var offset = record.offset();
        try {
            eventConsumer.consumeChatMessageEvent(record.value());
            // TODO: acknowledgment.acknowledge();
            logger.debug("Processed record with key: {} and offset: {} from partition: {}", key, offset, partition);
        } catch (Throwable throwable) {
            logger.error("Failed to process record with key: {} and offset: {} from partition: {}", key, offset, partition, throwable);
            acknowledgment.nack(5_000);
        }
    }
}
