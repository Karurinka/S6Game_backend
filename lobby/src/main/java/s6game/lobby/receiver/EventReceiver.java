package s6game.lobby.receiver;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import s6game.lobby.models.User;
import s6game.lobby.repository.IUserRepository;

@Component
public class EventReceiver {

    @Autowired
    private IUserRepository userRepository;

    private Logger log = LoggerFactory.getLogger(EventReceiver.class);

    @RabbitListener(queues = "${s6game.rabbitmq.queue}")
    public void receive(String userJson) {
        Gson gson = new Gson();
        System.out.println("received the event!");
        log.info("Received event: {}", userJson);
        userRepository.save(gson.fromJson(userJson, User.class));
    }
}

