package s6game.lobby.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import s6game.lobby.models.Lobby;
import s6game.lobby.models.User;
import s6game.lobby.receiver.LobbyReceiver;
import s6game.lobby.repository.ILobbyRepository;

@RestController
@CrossOrigin
@RequestMapping("/lobby")
public class LobbyController {
	@Autowired
    private ILobbyRepository lobbyRepository;

    @PostMapping
    public Lobby CreateLobby(@RequestBody LobbyReceiver lobby){
        return lobbyRepository.save(new Lobby(lobby));
    }

    @GetMapping("/{lobbyName}")
    public Lobby GetLobby(@PathVariable String lobbyName){
        return lobbyRepository.findByName(lobbyName);
    }

    @PostMapping("/{lobbyName}")
    public Lobby JoinLobby(@RequestBody User user, @PathVariable String lobbyName){
        Lobby lobby = lobbyRepository.findByName(lobbyName);
        lobby.addUser(user);
        return lobbyRepository.save(lobby);
    }

    @PostMapping("/user")
    public Lobby GetLobbyUser(@RequestBody User user){
        return lobbyRepository.findByUsers_Id(user.getId());
    }

}
