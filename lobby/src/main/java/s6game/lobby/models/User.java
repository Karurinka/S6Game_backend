package s6game.lobby.models;

import javax.persistence.*;

@Entity
public class User {
    @Id
    private Long id;
    @Column(unique = true)
    private String username;
    private int level;
    private String classType;

    @ManyToOne
    private Lobby lobby;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    public User() {
    }

    public User(String username) {
        this.username = username;
    }
}
