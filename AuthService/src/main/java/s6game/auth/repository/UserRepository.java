package s6game.auth.repository;

import s6game.auth.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
	User findById(long userId);

	User findByUsername(String username);

	Boolean existsByUsername(String username);
}
