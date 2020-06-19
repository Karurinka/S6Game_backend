package s6game.lobby.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import s6game.lobby.models.Lobby;

public interface ILobbyRepository extends JpaRepository<Lobby, Long> {
    Lobby findByName(String Name);
    Lobby findByUsers_Id(Long id);
}
