package s6game.auth.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import s6game.auth.domain.Greeting;
import s6game.auth.domain.LobbyEvent;

@RestController
public class HelloAppController {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${s6game.rabbit.exchange}")
    private String exchange;
    @Value("${s6game.rabbit.routingkey}")
    private String routingkey;

    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Greeting hello() {
        Greeting greeting = new Greeting();
        greeting.setMessage("Hello");
        greeting.setApp("authentication-service");
        return greeting;
    }

    @RequestMapping(value = "/lobby", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity lobby() {
        LobbyEvent lobbyEvent = new LobbyEvent();
        lobbyEvent.setCustomerName("John Doe");
        rabbitTemplate.convertAndSend(exchange, routingkey, lobbyEvent);
        return ResponseEntity.ok().build();
    }
}


