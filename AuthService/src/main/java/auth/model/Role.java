package auth.model;

import org.springframework.data.annotation.Id;

public class Role {

    @Id
    private String id;

    private Roles name;

    public Role() {

    }

    public Role(Roles name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Roles getName() {
        return name;
    }

    public void setName(Roles name) {
        this.name = name;
    }
}
