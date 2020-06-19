package s6game.lobby.models;

import s6game.lobby.receiver.LobbyReceiver;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Lobby {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(targetEntity = User.class)
    List<User> users = new ArrayList<>();

    private long owner;

    @Column(unique = true)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public long getOwner() {
        return owner;
    }

    public void setOwner(long owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addUser(User user){
        users.add(user);
    }

    public Lobby(){ }

    public Lobby(LobbyReceiver lobbyReceiver) {
        this.addUser(lobbyReceiver.getOwner());
        this.owner = lobbyReceiver.getOwner().getId();
        this.name = lobbyReceiver.getName();
    }
}
