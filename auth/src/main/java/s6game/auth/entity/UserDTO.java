package s6game.auth.entity;

import org.codehaus.jackson.annotate.JsonProperty;

public class UserDTO {
    private Long id;
    private String username;
    private String password;
    @JsonProperty
    private String access_token;

    public UserDTO() {
    }

    public UserDTO(UsersEntity usersEntity) {
        this.id = usersEntity.getId();
        this.username = usersEntity.getUsername();
        this.password = usersEntity.getPassword();
    }

    public UserDTO(UsersEntity usersEntity, String access_token) {
        this.id = usersEntity.getId();
        this.username = usersEntity.getUsername();
        this.password = usersEntity.getPassword();
        this.access_token = access_token;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
