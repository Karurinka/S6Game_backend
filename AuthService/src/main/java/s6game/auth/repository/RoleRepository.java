package s6game.auth.repository;

import s6game.auth.model.Role;
import s6game.auth.model.Roles;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(Roles name);
}
