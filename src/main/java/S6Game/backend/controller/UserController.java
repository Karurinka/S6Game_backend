package S6Game.backend.controller;

import S6Game.backend.model.User;
import S6Game.backend.repository.UserRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserRepository userRepository;
    private Gson gson = new Gson();

    @GetMapping()
    public ResponseEntity<String> GetUserById(@RequestParam("userId") int userId)
    {
        User currentUser = new User();
        currentUser = userRepository.findById(userId);
        String userJson = gson.toJson(currentUser);
        return ResponseEntity.ok().body(userJson);
    }
}

