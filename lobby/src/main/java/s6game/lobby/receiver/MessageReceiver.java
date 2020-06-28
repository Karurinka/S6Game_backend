package s6game.lobby.receiver;

import com.fasterxml.jackson.annotation.JsonProperty;
import s6game.lobby.models.Lobby;
import s6game.lobby.models.User;
import s6game.lobby.models.HelloMessage;

public class MessageReceiver {
    @JsonProperty
    private HelloMessage message;
    @JsonProperty
    private User messageOwner;
    @JsonProperty
    private Lobby lobby;
    @JsonProperty
    private String lobbyName;

    public HelloMessage getMessage() {
        return message;
    }

    public void setMessage(HelloMessage message) {
        this.message = message;
    }

    public User getMessageOwner() {
        return messageOwner;
    }

    public void setMessageOwner(User messageOwner) {
        this.messageOwner = messageOwner;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public void setLobby(Lobby lobbyId) {
        this.lobby = lobby;
    }

    public String getLobbyName() {
        return lobbyName;
    }

    public void setLobbyName(String lobbyName) {
        this.lobbyName = lobbyName;
    }
}
