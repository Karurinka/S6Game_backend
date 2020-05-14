package s6game.auth.repository;


import s6game.auth.model.User;
import org.springframework.data.repository.CrudRepository;

public interface LoginRepository extends CrudRepository<User, Long>
{
	User findByUsernameAndPassword(String username, String password);

	User findByUsernameNotLike(String username);
}
