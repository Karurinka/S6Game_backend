package S6Game.backend.repository;


import S6Game.backend.model.User;
import org.springframework.data.repository.CrudRepository;

public interface LoginRepository extends CrudRepository<User, Long>
{
	User findByUsernameAndPassword(String username, String password);

	User findByUsernameNotLike(String username);
}
