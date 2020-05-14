package s6game.auth.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String password;
    private int stamina;
    private int level;
    private int exp;
    private int rupies;
    private int crystals;
    private Set<Role> roles = new HashSet<>();

    //getters
    public int getId() { return id; }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getStamina() {
        return stamina;
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public int getRupies() {
        return rupies;
    }

    public int getCrystals() {
        return crystals;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    //setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setRupies(int rupies) {
        this.rupies = rupies;
    }

    public void setCrystals(int crystals) {
        this.crystals = crystals;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    //constructor
    public User() {
    }


    public User(String username, String password) {

    }


    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", username='" + username + '\'' +
               ", password='" + password + '\'' +
               ", stamina=" + stamina +
               ", level=" + level +
               ", exp=" + exp +
               ", rupies=" + rupies +
               ", crystals=" + crystals +
               ", roles=" + roles +
               '}';
    }
}
