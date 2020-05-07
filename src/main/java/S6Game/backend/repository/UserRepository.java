package S6Game.backend.repository;

import S6Game.backend.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long>
{
	User findById(long userId);

	User findByUsername(String username);

	Boolean existsByUsername(String username);
}
