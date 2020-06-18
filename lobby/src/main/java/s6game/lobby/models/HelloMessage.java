package s6game.lobby.models;

import s6game.lobby.models.Lobby;
import s6game.lobby.models.User;

import javax.persistence.*;

@Entity
public class HelloMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String message;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User messageOwner;

    @ManyToOne
    @JoinColumn(name= "lobby_id")
    private Lobby lobby;


    public HelloMessage() {}

    public HelloMessage(String name) {
        this.message = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
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
        this.lobby = lobbyId;
    }
}
