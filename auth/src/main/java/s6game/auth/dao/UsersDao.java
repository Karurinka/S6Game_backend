package s6game.auth.dao;

import s6game.auth.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersDao extends JpaRepository<UsersEntity,Long> {

    UsersEntity findByUsername(String username);
}
