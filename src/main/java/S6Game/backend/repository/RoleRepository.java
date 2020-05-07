package S6Game.backend.repository;

import S6Game.backend.model.Role;
import S6Game.backend.model.Roles;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, String> {
    Optional<Role> findByName(Roles name);
}
