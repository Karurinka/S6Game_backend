package s6game.lobby.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import s6game.lobby.models.User;

public interface IUserRepository extends JpaRepository<User, Long> {
}
