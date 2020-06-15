package s6game.lobby.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class EventReceiver {

    private Logger log = LoggerFactory.getLogger(EventReceiver.class);

    @RabbitListener(queues = "${s6game.rabbit.queue}")
    public void receive(String event) {
        System.out.println("received the event!");
        log.info("Received event in service document generation: {}", event);
        // Convert to object.
        // Do with it whatever you please.
    }
}

