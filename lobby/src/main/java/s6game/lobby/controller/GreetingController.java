package s6game.lobby.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import s6game.lobby.models.Lobby;
import s6game.lobby.receiver.MessageReceiver;
import s6game.lobby.models.HelloMessage;

@Controller
public class GreetingController {

    @MessageMapping("/hello/{lobbyName}")
    @SendTo("/topic/greetings/{lobbyName}")
    public HelloMessage greeting(@DestinationVariable String lobbyName, MessageReceiver message) throws Exception {
        Lobby lobby = new Lobby();
        lobby.setId(message.getLobbyId());
        lobby.setName(message.getLobbyName());
        System.out.printf(message.toString());

        HelloMessage helloMessage = new HelloMessage();
        helloMessage.setMessageOwner(message.getMessageOwner());
        helloMessage.setMessage(message.getMessage().getMessage());
        helloMessage.setLobbyId(lobby);

        return helloMessage;
    }
}
