package S6Game.backend.controller;

import S6Game.backend.model.User;
import S6Game.backend.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController
{
	@Autowired
	private LoginRepository loginRepository;
	private Gson gson;

	@PostMapping(path ="/register")
	public User register(@RequestBody User user)
	{
		loginRepository.save(user);
		return user;
	}
}
