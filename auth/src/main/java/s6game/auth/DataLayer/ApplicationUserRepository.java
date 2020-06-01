package s6game.auth.DataLayer;

import s6game.auth.Models.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser,Long> {
    ApplicationUser findByUsername(String username);
    ApplicationUser findByMainUserId(long MainUserId);
}
