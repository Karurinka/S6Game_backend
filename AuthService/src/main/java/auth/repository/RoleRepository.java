package auth.repository;

import auth.model.Role;
import auth.model.Roles;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, String> {
    Optional<Role> findByName(Roles name);
}
