package s6game.lobby.receiver;

import com.fasterxml.jackson.annotation.JsonProperty;
import s6game.lobby.models.User;
import s6game.lobby.models.HelloMessage;

public class MessageReceiver {
    @JsonProperty
    private HelloMessage message;
    @JsonProperty
    private User messageOwner;
    @JsonProperty
    private Long lobbyId;
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

    public Long getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(Long lobbyId) {
        this.lobbyId = lobbyId;
    }

    public String getLobbyName() {
        return lobbyName;
    }

    public void setLobbyName(String lobbyName) {
        this.lobbyName = lobbyName;
    }
}
