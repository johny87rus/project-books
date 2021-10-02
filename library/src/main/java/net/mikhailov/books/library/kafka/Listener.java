package net.mikhailov.books.library.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Mikhailov Evgenii
 */
@Component
public class Listener {
    @KafkaListener(topics = "baeldung", groupId = "foo2")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group foo: " + message);
    }
}
